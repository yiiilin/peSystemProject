package com.fzn.pesystem.quartz.dao;

import com.fzn.pesystem.common.entities.Perform;
import com.fzn.pesystem.common.entities.Subtasks;
import com.fzn.pesystem.common.entities.TaskStatus;
import com.fzn.pesystem.common.entities.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface QuartzDao {
    //定时统计功能DAO
    List<User> getAllUser();

    List<TaskStatus> getAllFinishTask(Date startDate, Date date);

    List<Subtasks> getAllSubtasksByTeamId(int teamId);

    Boolean insertPerform(Perform pf);

    Boolean upDateUserScore(Integer uid, double v);
}
