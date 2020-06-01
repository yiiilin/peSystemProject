package com.fzn.pesystem.provider.service;

import com.fzn.pesystem.common.entities.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface ICaseService {
    public boolean addCase(Case acase);

    public List<CaseType> getCaseType();

    public List<Case> getCase();

    public boolean updateCaseStatus(Integer caseId, String caseStatus, Date reviewTime);

    public String getCaseStatus(Integer caseId);

    public boolean createTeam(Integer uid,String userType);

    public boolean createTaskStatus(TaskStatus status);

    public String getUserType(Integer teamId, Integer uid);

    public List<TaskStatus> getOpenTask();

    public Integer getIsRecruiting(Integer taskStatusId);

    public Integer getTeamId(Integer taskStatusId);

    public boolean updateIsRecruiting(Integer taskStatusId, Integer isRecruiting);

    public boolean addToTeam(Team team);

    public boolean addSubtasks(Subtasks subtasks);

    public List<Subtasks> getSelfUnDoneSubtasks(Integer uid,Integer isCompleted);

    public List<Subtasks> getTeamUnDoneSubtasks(Integer teamId, Integer isCompleted);

    public boolean submitTackResult(Completion completion);

    public boolean updateIsSubmit(Integer subtasksId, Integer isSubmit);

    public boolean updateIsCompleted(Integer subtasksId, Integer isCompleted);

    public boolean completeTackResult(Completion completion);

    public Integer getMaxCaseId();

    public Integer getMaxTaskStatusId();

    public Integer getMaxTeamId();

    public Integer getMaxSubtasksId();

    public Integer getMaxCompletionId();

    public List<TaskStatus> getTaskStatus(Integer caseId);

    public String getTaskStatusStatus(Integer taskStatusId);

    public boolean updateTaskStatus(TaskStatus taskStatus);

    public boolean updateTaskStatusStatus(Integer taskStatusId, String taskStatus);

    public boolean updateSubTasks(Subtasks subtasks);

    public Integer getSubTeamId(Integer subtasksId);

    public boolean deleteSubTask(Integer subtasksId);

    public List<Subtasks> getTeamUnDoneSubtasksById(Integer teamId, Integer uid,Integer isCompleted);

    public Integer getHasUidInTeam(Integer teamId, Integer uid);
}
