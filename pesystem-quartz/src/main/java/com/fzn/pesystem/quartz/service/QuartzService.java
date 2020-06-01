package com.fzn.pesystem.quartz.service;

import com.fzn.pesystem.common.entities.Perform;
import com.fzn.pesystem.common.entities.Subtasks;
import com.fzn.pesystem.common.entities.TaskStatus;
import com.fzn.pesystem.common.entities.User;
import com.fzn.pesystem.quartz.dao.QuartzDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuartzService {
    @Autowired
    private QuartzDao dao;
    //自动更新绩效
    public void autoUpdate(Date date){

        //获取开始时间
        Date startDate=getStartDate(date);

        //获取所有的用户
        List<User> userList=getAllUser();
        //查询startDate到date时间段内所有的已完成任务
        List<TaskStatus> taskStatusList=getAllFinishTask(startDate,date);

        //构造Map<uid,score>
        Map<Integer,Double> monScore=new HashMap<>();
        Map<Integer,Double> sumScore=new HashMap<>();
        for (User user:userList) {
            monScore.put(user.getUid(),0.0);
            sumScore.put(user.getUid(),user.getScore());
        }

        //遍历已完成任务
        for (int i=0;i<taskStatusList.size();i++){
            TaskStatus ts=taskStatusList.get(i);
            //获取团队id
            int teamId=ts.getTeamId();
            //获取任务总分
            double taskScore=ts.getTaskScore();
            // 在子任务表中通过团队id查询所有子任务；
            List<Subtasks> subtasksList=getAllSubtasksByTeamId(teamId);
            //​	DAO计算总权重
            Double sumWeights=0.0;
            for (Subtasks sb:subtasksList) {
                sumWeights+=sb.getWeights();
            }

            //遍历所有子任务,计算本次任务参与人得分
            for(int j=0;j<subtasksList.size();j++){
                Subtasks subtask=subtasksList.get(j);
                //本次任务记录获得分数
                double mark=0;
                if(subtask.getIsCompleted()==0){
                    break;
                }
                Double weight=subtask.getWeights();
                mark= taskScore*weight/sumWeights;
                monScore.put(subtask.getUid(),monScore.get(subtask.getUid())+mark);
            }
        }
        //计算总分
        //​	在个人绩效中插入数据	insert
        for (User user:userList) {
            Perform pf=new Perform();
            pf.setUid(user.getUid())
                    .setDepartId(user.getDepartId())
                    .setPerformScore(monScore.get(user.getUid()))
                    .setUpdateTime(date);
            //增加个人绩效
            insertPerform(pf);
            //在user表中更新mark
            upDateUserScore(user.getUid(),user.getScore()+monScore.get(user.getUid()));
        }
    }

    /**
     * 获取date前一个月日期
     * @param date
     * @return startDate
     */
    public Date getStartDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取date中的年月日
        String year=String.format("%tY", date);
        String mon=String .format("%tm", date);
        String day=String .format("%td", date);
        int y=Integer.valueOf(year);
        int m=Integer.valueOf(mon);
        int d=Integer.valueOf(day);
        //判断是否为一月
        if(m==1){
            y=y-1;
            m=12;
        }else{
            m=m-1;
        }
        //构建开始时间
        String strDate = y+"-"+m+"-"+d;
        try {
            return sdf.parse(strDate);
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> getAllUser() {
        return dao.getAllUser();
    }

    public List<TaskStatus> getAllFinishTask(Date startDate, Date date) {
        return dao.getAllFinishTask(startDate,date);
    }

    public List<Subtasks> getAllSubtasksByTeamId(int teamId) {
        return dao.getAllSubtasksByTeamId(teamId);
    }

    public Boolean insertPerform(Perform pf) {
        return dao.insertPerform(pf);
    }

    public Boolean upDateUserScore(Integer uid, double v) {
        return  dao.upDateUserScore(uid,v);
    }
}
