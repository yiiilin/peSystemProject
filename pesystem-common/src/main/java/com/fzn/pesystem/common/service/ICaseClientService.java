package com.fzn.pesystem.common.service;

import com.fzn.pesystem.common.entities.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RequestMapping(value = "/case")
public interface ICaseClientService {

    @RequestMapping(value = "/addCase",method = RequestMethod.POST)
    public boolean addCase(@RequestBody Case acase);

    @RequestMapping(value = "/getCaseType",method = RequestMethod.GET)
    public List<CaseType> getCaseType();

    @RequestMapping(value = "/getCase",method = RequestMethod.GET)
    public List<Case> getCase();

    @RequestMapping(value = "/updateCaseStatus",method = RequestMethod.POST)
    public boolean updateCaseStatus(@RequestParam Integer caseId,@RequestParam String caseStatus,@RequestParam Date reviewTime);

    @RequestMapping(value = "/getCaseStatus",method = RequestMethod.POST)
    public String getCaseStatus(@RequestParam Integer caseId);

    @RequestMapping(value = "/createTeam",method = RequestMethod.POST)
    public boolean createTeam(@RequestParam Integer uid,@RequestParam String userType);

    @RequestMapping(value = "/createTaskStatus",method = RequestMethod.POST)
    public boolean createTaskStatus(@RequestBody TaskStatus status);

    @RequestMapping(value = "/getUserType",method = RequestMethod.POST)
    public String getUserType(@RequestParam Integer teamId,@RequestParam Integer uid);

    @RequestMapping(value = "/getOpenTask",method = RequestMethod.POST)
    public List<TaskStatus> getOpenTask();

    @RequestMapping(value = "/getIsRecruiting",method = RequestMethod.POST)
    public Integer getIsRecruiting(@RequestParam Integer taskStatusId);

    @RequestMapping(value = "/getTeamId",method = RequestMethod.POST)
    public Integer getTeamId(@RequestParam Integer taskStatusId);

    @RequestMapping(value = "/updateIsRecruiting",method = RequestMethod.POST)
    public boolean updateIsRecruiting(@RequestParam Integer taskStatusId,@RequestParam Integer isRecruiting);

    @RequestMapping(value = "/addToTeam",method = RequestMethod.POST)
    public boolean addToTeam(@RequestBody Team team);

    @RequestMapping(value = "/addSubtasks",method = RequestMethod.POST)
    public boolean addSubtasks(@RequestBody Subtasks subtasks);

    @RequestMapping(value = "/getSelfUnDoneSubtasks",method = RequestMethod.POST)
    public List<Subtasks> getSelfUnDoneSubtasks(@RequestParam Integer uid,@RequestParam Integer isCompleted);

    @RequestMapping(value = "/getTeamUnDoneSubtasks",method = RequestMethod.POST)
    public List<Subtasks> getTeamUnDoneSubtasks(@RequestParam Integer teamId,@RequestParam Integer isCompleted);

    @RequestMapping(value = "/submitTackResult",method = RequestMethod.POST)
    public boolean submitTackResult(@RequestBody Completion completion);

    @RequestMapping(value = "/updateIsSubmit",method = RequestMethod.POST)
    public boolean updateIsSubmit(@RequestParam Integer subtasksId,@RequestParam Integer isSubmit);

    @RequestMapping(value = "updateIsCompleted",method = RequestMethod.POST)
    public boolean updateIsCompleted(@RequestParam Integer subtasksId,@RequestParam Integer isCompleted);

    @RequestMapping(value = "/completeTackResult",method = RequestMethod.POST)
    public boolean completeTackResult(@RequestBody Completion completion);

    @RequestMapping(value = "/getMaxCaseId",method = RequestMethod.POST)
    public Integer getMaxCaseId();

    @RequestMapping(value = "/getMaxTaskStatusId",method = RequestMethod.POST)
    public Integer getMaxTaskStatusId();

    @RequestMapping(value = "/getMaxTeamId",method = RequestMethod.POST)
    public Integer getMaxTeamId();

    @RequestMapping(value = "/getMaxSubtasksId",method = RequestMethod.POST)
    public Integer getMaxSubtasksId();

    @RequestMapping(value = "/getMaxCompletionId",method = RequestMethod.POST)
    public Integer getMaxCompletionId();

    @RequestMapping(value = "/getTaskStatus",method = RequestMethod.POST)
    public List<TaskStatus> getTaskStatus(@RequestParam Integer caseId);

    @RequestMapping(value = "/getTaskStatusStatus",method = RequestMethod.POST)
    public String getTaskStatusStatus(@RequestParam Integer taskStatusId);

    @RequestMapping(value = "/updateTaskStatus",method = RequestMethod.POST)
    public boolean updateTaskStatus(@RequestBody TaskStatus taskStatus);

    @RequestMapping(value = "/updateTaskStatusStatus",method = RequestMethod.POST)
    public boolean updateTaskStatusStatus(@RequestParam Integer taskStatusId,@RequestParam String taskStatus);

    @RequestMapping(value = "/updateSubTasks",method = RequestMethod.POST)
    public boolean updateSubTasks(@RequestBody Subtasks subtasks);

    @RequestMapping(value = "/getSubTeamId",method = RequestMethod.POST)
    public Integer getSubTeamId(@RequestParam Integer subtasksId);

    @RequestMapping(value = "/deleteSubTask",method = RequestMethod.POST)
    public boolean deleteSubTask(@RequestParam Integer subtasksId);

    @RequestMapping(value = "/getTeamUnDoneSubtasksById",method = RequestMethod.POST)
    public List<Subtasks> getTeamUnDoneSubtasksById(@RequestParam Integer teamId,@RequestParam Integer uid,@RequestParam Integer isCompleted);

    @RequestMapping(value = "/getHasUidInTeam",method = RequestMethod.POST)
    public Integer getHasUidInTeam(@RequestParam Integer teamId,@RequestParam Integer uid);
}
