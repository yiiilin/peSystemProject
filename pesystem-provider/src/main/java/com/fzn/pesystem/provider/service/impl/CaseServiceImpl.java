package com.fzn.pesystem.provider.service.impl;

import com.fzn.pesystem.common.entities.*;
import com.fzn.pesystem.provider.dao.ICaseDao;
import com.fzn.pesystem.provider.service.ICaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CaseServiceImpl implements ICaseService {
    @Autowired
    private ICaseDao caseDao;

    @Override
    public boolean addCase(Case acase) {
        return caseDao.addCase(acase);
    }

    @Override
    public List<CaseType> getCaseType() {
        return caseDao.getCaseType();
    }

    @Override
    public List<Case> getCase() {
        return caseDao.getCase();
    }

    @Override
    public boolean updateCaseStatus(Integer caseId, String caseStatus, Date reviewTime) {
        return caseDao.updateCaseStatue(caseId,caseStatus,reviewTime);
    }

    @Override
    public String getCaseStatus(Integer caseId) {
        return caseDao.getCaseStatus(caseId);
    }

    @Override
    public boolean createTeam(Integer uid, String userType) {
        return caseDao.createTeam(uid, userType);
    }

    @Override
    public boolean createTaskStatus(TaskStatus status) {
        return caseDao.createTaskStatus(status);
    }

    @Override
    public String getUserType(Integer teamId, Integer uid) {
        return caseDao.getUserType(teamId, uid);
    }

    @Override
    public List<TaskStatus> getOpenTask() {
        return caseDao.getOpenTask();
    }

    @Override
    public Integer getIsRecruiting(Integer taskStatusId) {
        return caseDao.getIsRecruiting(taskStatusId);
    }

    @Override
    public Integer getTeamId(Integer taskStatusId) {
        return caseDao.getTeamId(taskStatusId);
    }

    @Override
    public boolean updateIsRecruiting(Integer taskStatusId, Integer isRecruiting) {
        return caseDao.updateIsRecruiting(taskStatusId, isRecruiting);
    }

    @Override
    public boolean addToTeam(Team team) {
        return caseDao.addToTeam(team);
    }

    @Override
    public boolean addSubtasks(Subtasks subtasks) {
        return caseDao.addSubtasks(subtasks);
    }

    @Override
    public List<Subtasks> getSelfUnDoneSubtasks(Integer uid,Integer isCompleted) {
        return caseDao.getSelfUnDoneSubtasks(uid, isCompleted);
    }

    @Override
    public List<Subtasks> getTeamUnDoneSubtasks(Integer teamId, Integer isCompleted) {
        return caseDao.getTeamUnDoneSubtasks(teamId, isCompleted);
    }

    @Override
    public boolean submitTackResult(Completion completion) {
        return caseDao.submitTackResult(completion);
    }

    @Override
    public boolean updateIsSubmit(Integer subtasksId, Integer isSubmit) {
        return caseDao.updateIsSubmit(subtasksId, isSubmit);
    }

    @Override
    public boolean updateIsCompleted(Integer subtasksId, Integer isCompleted) {
        return caseDao.updateIsCompleted(subtasksId, isCompleted);
    }

    @Override
    public boolean completeTackResult(Completion completion) {
        return caseDao.completeTackResult(completion);
    }

    @Override
    public Integer getMaxCaseId(){
        return caseDao.getMaxCaseId();
    }

    @Override
    public Integer getMaxTaskStatusId() {
        return caseDao.getMaxTaskStatusId();
    }

    @Override
    public Integer getMaxTeamId() {
        return caseDao.getMaxTeamId();
    }

    @Override
    public Integer getMaxSubtasksId() {
        return caseDao.getMaxSubtasksId();
    }

    @Override
    public Integer getMaxCompletionId() {
        return caseDao.getMaxCompletionId();
    }


    @Override
    public List<TaskStatus> getTaskStatus(Integer caseId) {
        return caseDao.getTaskStatus(caseId);
    }

    @Override
    public String getTaskStatusStatus(Integer taskStatusId) {
        return caseDao.getTaskStatusStatus(taskStatusId);
    }

    @Override
    public boolean updateTaskStatus(TaskStatus taskStatus) {
        return caseDao.updateTaskStatus(taskStatus);
    }

    @Override
    public boolean updateTaskStatusStatus(Integer taskStatusId, String taskStatus) {
        return caseDao.updateTaskStatusStatus(taskStatusId, taskStatus);
    }

    @Override
    public boolean updateSubTasks(Subtasks subtasks) {
        return caseDao.updateSubTasks(subtasks);
    }

    @Override
    public Integer getSubTeamId(Integer subtasksId) {
        return caseDao.getSubTeamId(subtasksId);
    }

    @Override
    public boolean deleteSubTask(Integer subtasksId) {
        return caseDao.deleteSubTask(subtasksId);
    }

    @Override
    public List<Subtasks> getTeamUnDoneSubtasksById(Integer teamId, Integer uid, Integer isCompleted) {
        return caseDao.getTeamUnDoneSubtasksById(teamId, uid, isCompleted);
    }

    @Override
    public Integer getHasUidInTeam(Integer teamId, Integer uid){
        return caseDao.getHasUidInTeam(teamId, uid);
    }
}
