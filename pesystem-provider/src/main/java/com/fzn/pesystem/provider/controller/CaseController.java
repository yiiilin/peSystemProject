package com.fzn.pesystem.provider.controller;

import com.fzn.pesystem.common.entities.*;
import com.fzn.pesystem.common.service.ICaseClientService;
import com.fzn.pesystem.provider.service.ICaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
public class CaseController implements ICaseClientService {

    @Autowired
    private ICaseService service;

    @Override
    public boolean addCase(Case acase) {
        return service.addCase(acase);
    }

    @Override
    public List<CaseType> getCaseType() {
        return service.getCaseType();
    }

    @Override
    public List<Case> getCase() {
        return service.getCase();
    }

    @Override
    public boolean updateCaseStatus(Integer caseId, String caseStatus, Date reviewTime) {
        return service.updateCaseStatus(caseId,caseStatus,reviewTime);
    }

    @Override
    public String getCaseStatus(Integer caseId) {
        return service.getCaseStatus(caseId);
    }

    @Override
    public boolean createTeam(Integer uid, String userType) {
        return service.createTeam(uid, userType);
    }

    @Override
    public boolean createTaskStatus(TaskStatus status) {
        return service.createTaskStatus(status);
    }

    @Override
    public String getUserType(Integer teamId, Integer uid) {
        return service.getUserType(teamId, uid);
    }

    @Override
    public List<TaskStatus> getOpenTask() {
        return service.getOpenTask();
    }

    @Override
    public Integer getIsRecruiting(Integer taskStatusId) {
        return service.getIsRecruiting(taskStatusId);
    }

    @Override
    public Integer getTeamId(Integer taskStatusId) {
        return service.getTeamId(taskStatusId);
    }

    @Override
    public boolean updateIsRecruiting(Integer taskStatusId, Integer isRecruiting) {
        return service.updateIsRecruiting(taskStatusId, isRecruiting);
    }

    @Override
    public boolean addToTeam(Team team) {
        return service.addToTeam(team);
    }

    @Override
    public boolean addSubtasks(Subtasks subtasks) {
        return service.addSubtasks(subtasks);
    }

    @Override
    public List<Subtasks> getSelfUnDoneSubtasks(Integer uid,Integer isCompleted) {
        return service.getSelfUnDoneSubtasks(uid, isCompleted);
    }

    @Override
    public List<Subtasks> getTeamUnDoneSubtasks(Integer teamId, Integer isCompleted) {
        return service.getTeamUnDoneSubtasks(teamId,isCompleted);
    }

    @Override
    public boolean submitTackResult(Completion completion) {
        return service.submitTackResult(completion);
    }

    @Override
    public boolean updateIsSubmit(Integer subtasksId, Integer isSubmit) {
        return service.updateIsSubmit(subtasksId, isSubmit);
    }

    @Override
    public boolean updateIsCompleted(Integer subtasksId, Integer isCompleted) {
        return service.updateIsCompleted(subtasksId, isCompleted);
    }

    @Override
    public boolean completeTackResult(Completion completion) {
        return service.completeTackResult(completion);
    }

    @Override
    public Integer getMaxCaseId() {
        return service.getMaxCaseId();
    }

    @Override
    public Integer getMaxTaskStatusId() {
        return service.getMaxTaskStatusId();
    }

    @Override
    public Integer getMaxTeamId() {
        return service.getMaxTeamId();
    }

    @Override
    public Integer getMaxSubtasksId() {
        return service.getMaxSubtasksId();
    }

    @Override
    public Integer getMaxCompletionId() {
        return service.getMaxCompletionId();
    }

    @Override
    public List<TaskStatus> getTaskStatus(Integer caseId) {
        return service.getTaskStatus(caseId);
    }

    @Override
    public String getTaskStatusStatus(Integer taskStatusId) {
        return service.getTaskStatusStatus(taskStatusId);
    }

    @Override
    public boolean updateTaskStatus(TaskStatus taskStatus) {
        return service.updateTaskStatus(taskStatus);
    }

    @Override
    public boolean updateTaskStatusStatus(Integer taskStatusId, String taskStatus) {
        return service.updateTaskStatusStatus(taskStatusId, taskStatus);
    }

    @Override
    public boolean updateSubTasks(Subtasks subtasks) {
        return service.updateSubTasks(subtasks);
    }

    @Override
    public Integer getSubTeamId(Integer subtasksId) {
        return service.getSubTeamId(subtasksId);
    }

    @Override
    public boolean deleteSubTask(Integer subtasksId) {
        return service.deleteSubTask(subtasksId);
    }

    @Override
    public List<Subtasks> getTeamUnDoneSubtasksById(Integer teamId, Integer uid,Integer isCompleted) {
        return service.getTeamUnDoneSubtasksById(teamId, uid, isCompleted);
    }

    @Override
    public Integer getHasUidInTeam(Integer teamId, Integer uid) {
        return service.getHasUidInTeam(teamId, uid);
    }
}
