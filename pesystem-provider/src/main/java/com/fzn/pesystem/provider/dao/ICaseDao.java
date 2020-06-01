package com.fzn.pesystem.provider.dao;

import com.fzn.pesystem.common.entities.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Mapper
public interface ICaseDao {

    /**
     * 上传案件
     * @param aCase
     * @return
     */
    public boolean addCase(Case aCase);

    /**
     * 获取案件类型
     * @return
     */
    public List<CaseType> getCaseType();

    /**
     * 获取案件
     * @return
     */
    public List<Case> getCase();

    /**
     * 更新案件
     * @param acase
     * @return
     */
    public boolean updateCaseStatue(Integer caseId, String caseStatus, Date reviewTime);

    /**
     * 获取案件审核状态
     * @param caseId
     * @return
     */
    public String getCaseStatus(Integer caseId);

    /**
     * 创建团队
     * @param uid
     * @param userType
     * @return
     */
    public boolean createTeam(Integer uid,String userType);

    /**
     * 创建任务进行情况
     * @param taskStatus
     * @return
     */
    public boolean createTaskStatus(TaskStatus taskStatus);

    /**
     * 获取团队成员类型
     * @param teamId
     * @param uid
     * @return
     */
    public String getUserType(Integer teamId, Integer uid);

    /**
     * 获取公开招募的任务
     * @return
     */
    public List<TaskStatus> getOpenTask();

    /**
     * 获取招募状态
     * @param taskStatusId
     * @return
     */
    public Integer getIsRecruiting(Integer taskStatusId);

    /**
     * 查找团队id
     * @param taskStatusId
     * @return
     */
    public Integer getTeamId(Integer taskStatusId);

    /**
     * 更新任务招募状态
     * @param taskStatusId
     * @param isRecruiting
     * @return
     */
    public boolean updateIsRecruiting(Integer taskStatusId, Integer isRecruiting);

    /**
     * 添加队员到团队
     * @param team
     * @return
     */
    public boolean addToTeam(Team team);

    /**
     * 添加子任务
     * @param subtasks
     * @return
     */
    public boolean addSubtasks(Subtasks subtasks);

    /**
     * 获取个人未完成子任务
     * @param uid
     * @param isCompleted
     * @return
     */
    public List<Subtasks> getSelfUnDoneSubtasks(Integer uid,Integer isCompleted);

    /**
     * 获取团队未完成所有子任务
     * @param teamId
     * @param isCompleted
     * @return
     */
    public List<Subtasks> getTeamUnDoneSubtasks(Integer teamId, Integer isCompleted);

    /**
     * 保存提交任务完成情况结果
     * @param completion
     * @return
     */
    public boolean submitTackResult(Completion completion);

    /**
     * 更新子任务是否有提交结果字段
     * @param subtasksId
     * @param isSubmit
     * @return
     */
    public boolean updateIsSubmit(Integer subtasksId, Integer isSubmit);

    /**
     * 更新子任务是否已完成字段
     * @param subtasksId
     * @param isCompleted
     * @return
     */
    public boolean updateIsCompleted(Integer subtasksId, Integer isCompleted);

    /**
     * 完成提交任务情况结果
     * @param completion
     * @return
     */
    public boolean completeTackResult(Completion completion);

    /**
     * 获取数据库中最大的caseId
     * @return
     */
    public Integer getMaxCaseId();

    /**
     * 获取数据库中最大的taskStatusId
     * @return
     */
    public Integer getMaxTaskStatusId();

    /**
     * 获取数据库中最大的teamId
     * @return
     */
    public Integer getMaxTeamId();

    /**
     * 获取数据库中最大的SubtasksId
     * @return
     */
    public Integer getMaxSubtasksId();

    /**
     * 获取数据库中最大的CompletionId
     * @return
     */
    public Integer getMaxCompletionId();

    /**
     * 获取任务进行情况
     * @param caseId
     * @return
     */
    public List<TaskStatus> getTaskStatus(Integer caseId);

    /**
     * 获取任务进行情况是否已完成
     * @param taskStatusId
     * @return
     */
    public String getTaskStatusStatus(Integer taskStatusId);

    /**
     * 修改任务进行情况
     * @param taskStatus
     * @return
     */
    public boolean updateTaskStatus(TaskStatus taskStatus);

    /**
     * 修改任务进行情况完成状态
     * @param taskStatusId
     * @param taskStatus
     * @return
     */
    public boolean updateTaskStatusStatus(Integer taskStatusId, String taskStatus);

    /**
     * 修改子任务
     * @param subtasks
     * @return
     */
    public boolean updateSubTasks(Subtasks subtasks);

    /**
     * 获取团队id（subtask中）
     * @param subtasksId
     * @return
     */
    public Integer getSubTeamId(Integer subtasksId);

    /**
     * 删除子任务
     * @param subtasksId
     * @return
     */
    public boolean deleteSubTask(Integer subtasksId);

    /**
     * 获取团队单个成员未完成子任务
     * @param teamId
     * @param uid
     * @return
     */
    public List<Subtasks> getTeamUnDoneSubtasksById(Integer teamId, Integer uid,Integer isCompleted);

    /**
     * 查询团队中是否有该成员
     * @param teamId
     * @param uid
     * @return
     */
    public Integer getHasUidInTeam(Integer teamId, Integer uid);
}
