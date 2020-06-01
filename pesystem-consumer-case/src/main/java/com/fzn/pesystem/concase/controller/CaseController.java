package com.fzn.pesystem.concase.controller;

import com.fzn.pesystem.common.entities.*;
import com.fzn.pesystem.common.response.ResponseResultJson;
import com.fzn.pesystem.concase.service.ICaseService;
import com.fzn.pesystem.concase.service.IFileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CaseController{
    @Autowired
    private ICaseService service;

    @Autowired
    private IFileService fileService;

    @ApiOperation("上传案件")
    @RequestMapping(value = "/addCase",method = RequestMethod.POST)
    public ResponseResultJson<Boolean> addCase(@ApiParam("案件类型id") @RequestParam Integer caseTypeId,
                                               @ApiParam("案件种类名称") @RequestParam String caseTypeName,
                                               @ApiParam("案件详细描述") @RequestParam String caseInfo,
                                               @ApiParam("案件附件") @RequestPart("file")MultipartFile file,
                                               @ApiParam("案件上报时间") @RequestParam Date caseUpdateTime) {
        if(caseTypeId == null || StringUtils.isEmpty(caseInfo)){
            return ResponseResultJson.paramError("案件类型id或案件描述不能为空",Boolean.FALSE);
        }
        try {
            Integer typeId = service.getMaxCaseId()+1;
            Case acase = new Case();
            String caseStatus = "pendingReview";
            //判读文件是否为空
            if(file.getSize() == 0 || !StringUtils.isNotBlank(file.getOriginalFilename())) {
                acase.setCaseId(typeId)
                        .setCaseTypeId(caseTypeId)
                        .setCaseTypeName(caseTypeName)
                        .setCaseInfo(caseInfo)
                        .setCaseAttachmentsUrl(null)
                        .setCaseUpdateTime(caseUpdateTime)
                        .setCaseStatus(caseStatus)
                        .setReviewTime(new Date());
            }else {
                String type = "Case";
                String caseAttachmentsUrl = fileService.addAttachments(type,typeId,file);
                //判断文件是否上传失败
                if (caseAttachmentsUrl.equals("false") || caseAttachmentsUrl.equals("服务超时")){
                    return ResponseResultJson.failed("上传文件失败("+caseAttachmentsUrl+")，案件未上报",Boolean.FALSE);
                }else {
                    acase.setCaseId(typeId)
                            .setCaseTypeId(caseTypeId)
                            .setCaseTypeName(caseTypeName)
                            .setCaseInfo(caseInfo)
                            .setCaseAttachmentsUrl(caseAttachmentsUrl)
                            .setCaseUpdateTime(caseUpdateTime)
                            .setCaseStatus(caseStatus)
                            .setReviewTime(new Date());
                }
            }
            if(service.addCase(acase)){
                return ResponseResultJson.success("上传案件成功",Boolean.TRUE);
            }
            else {
                return ResponseResultJson.failed("上传失败",Boolean.FALSE);
            }
        }catch (Exception e){
            return ResponseResultJson.unknownError(e.getMessage(),Boolean.FALSE);
        }
    }

    @ApiOperation("获取全部案件(按时间降序排)")
    @RequestMapping(value = "/getCase",method = RequestMethod.GET)
    public ResponseResultJson<Object> getCase() {
        try {
            List<Case> caseList = service.getCase();
            return ResponseResultJson.success("获取案件成功",caseList);
        } catch (Exception e) {
            return ResponseResultJson.unknownError(e.getMessage(), Boolean.FALSE);
        }
    }

    @ApiOperation("获取案件类型")
    @RequestMapping(value = "/getCaseType",method = RequestMethod.GET)
    public ResponseResultJson<Object> getCaseType(){
        try {
            List<CaseType> caseTypes = service.getCaseType();
            return ResponseResultJson.success("获取案件类型成功",caseTypes);
        }catch (Exception e){
            return ResponseResultJson.unknownError(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("hasAuthority('admin,sectionAdmin')")
    @ApiOperation("审核案件")
    @RequestMapping(value = "/updateCaseStatus",method = RequestMethod.PATCH)
    public ResponseResultJson<Boolean> updateCaseStatus(@ApiParam("案件id") @RequestParam Integer caseId,
                                                        @ApiParam("案件状态") @RequestParam String caseStatus,
                                                        @ApiParam("审核时间") @RequestParam Date reviewTime){
        if(StringUtils.isEmpty(caseStatus)){
            return ResponseResultJson.paramError("案件状态不能为空",Boolean.FALSE);
        }
        try {
            if(service.updateCaseStatus(caseId,caseStatus,reviewTime)){
                return ResponseResultJson.success("更新案件审核状态成功",Boolean.TRUE);
            }
            else{
                return ResponseResultJson.failed("更新案件审核状态失败",Boolean.FALSE);
            }
        }catch (Exception e){
            return ResponseResultJson.unknownError(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation("接案件并自动创建团队")
    @RequestMapping(value = "/acceptTask",method = RequestMethod.POST)
    public ResponseResultJson<Object> acceptTask(@ApiParam("案件id") @RequestParam Integer caseId){
        try {
            //判断案件状态
            String caseStatus = service.getCaseStatus(caseId);
            String needCaseStatus = "noTreatment";
            if(!needCaseStatus.equals(caseStatus)){
                return ResponseResultJson.failed("案件状态不支持接收任务",Boolean.FALSE);
            }
            //创建团队
            String userType = "leader";
            Integer uid = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            if(service.createTeam(uid,userType)){
                return ResponseResultJson.success("接收任务并创建团队成功",Boolean.TRUE);
            }else {
                return ResponseResultJson.failed("接收任务失败",Boolean.FALSE);
            }
        }catch (Exception e){
            return ResponseResultJson.unknownError(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation("队长创建任务进行情况")
    @RequestMapping(value = "/createTaskStatus",method = RequestMethod.POST)
    public ResponseResultJson<Boolean> createTaskStatus(@ApiParam("案件id") @RequestParam Integer caseId,
                                                        @ApiParam("团队id") @RequestParam Integer teamId,
                                                        @ApiParam("任务进行情况附件附件") @RequestPart("file") MultipartFile file,
                                                        @ApiParam("任务总分") @RequestParam Double taskScore,
                                                        @ApiParam("任务进行情况") @RequestParam String taskStatus,
                                                        @ApiParam("成员招募状态") @RequestParam Integer isRecruiting){
        if (caseId == null || teamId == null){
            return ResponseResultJson.paramError("案件或团队id不能为空",Boolean.FALSE);
        }
        Integer uid = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        try {
            String userType = service.getUserType(teamId,uid);
            String needUserType = "leader";
            if(!needUserType.equals(userType)){
                return ResponseResultJson.accessError("用户权限不足",Boolean.FALSE);
            }

            Integer typeId = service.getMaxTaskStatusId()+1;
            TaskStatus status = new TaskStatus();
            Date taskCompletionTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2000-1-1 00:00:00");
            if(file.getSize() == 0 || !StringUtils.isNotBlank(file.getOriginalFilename())) {
                status.setTaskStatusId(typeId)
                        .setCaseId(caseId)
                        .setTeamId(teamId)
                        .setTaskCompletionTime(taskCompletionTime)
                        .setTaskCompletionInfo(null)
                        .setTaskCompletionAttachmentsUrl(null)
                        .setTaskScore(taskScore)
                        .setTaskStatus(taskStatus)
                        .setIsRecruiting(isRecruiting);
            }else {
                String type = "TaskStatus";
                String taskCompletionAttachmentsUrl = fileService.addAttachments(type, typeId, file);
                //判断文件是否上传失败
                if (taskCompletionAttachmentsUrl.equals("false") || taskCompletionAttachmentsUrl.equals("服务超时")) {
                    return ResponseResultJson.failed("上传文件失败("+taskCompletionAttachmentsUrl+")，未添加任务进行情况", Boolean.FALSE);
                } else {
                    status.setTaskStatusId(typeId)
                            .setCaseId(caseId)
                            .setTeamId(teamId)
                            .setTaskCompletionTime(taskCompletionTime)
                            .setTaskCompletionInfo(null)
                            .setTaskCompletionAttachmentsUrl(taskCompletionAttachmentsUrl)
                            .setTaskScore(taskScore)
                            .setTaskStatus(taskStatus)
                            .setIsRecruiting(isRecruiting);
                }
            }
            if(service.createTaskStatus(status)){
                return ResponseResultJson.success("创建任务成功",Boolean.TRUE);
            }else {
                return ResponseResultJson.failed("创建任务失败",Boolean.FALSE);
            }
        }catch (Exception e){
            return ResponseResultJson.failed(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation("自行加入团队")
    @RequestMapping(value = "/joinToTeam",method = RequestMethod.POST)
    public ResponseResultJson<Boolean> joinToTeam(@ApiParam("任务进行id") @RequestParam Integer taskStatusId){
        try {
            //判断是否招募
            if(service.getIsRecruiting(taskStatusId)==0){
                return ResponseResultJson.failed("该任务未开启招募",Boolean.FALSE);
            }
            //查找任务团队id
            Integer teamId = service.getTeamId(taskStatusId);
            String userType = "member";
            Integer uid = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            //查看团队中是否有该成员
            if(service.getHasUidInTeam(teamId,uid) > 0){
                return ResponseResultJson.failed("你已在该团队中",Boolean.FALSE);
            }

            Team team = new Team();
            team.setTeamId(teamId)
                    .setUid(uid)
                    .setUserType(userType);

            //加入团队
            if(service.addToTeam(team)){
                return ResponseResultJson.success("加入团队成功",Boolean.TRUE);
            }else {
                return ResponseResultJson.failed("加入团队失败",Boolean.FALSE);
            }
        }catch (Exception e){
            return ResponseResultJson.failed(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation("队长添加队员")
    @RequestMapping(value = "/addToTeam",method = RequestMethod.POST)
    public ResponseResultJson<Boolean> addToTeam(@ApiParam("任务进行id") @RequestParam Integer taskStatusId,
                                                 @ApiParam("队员id") @RequestParam Integer uuid){
        try {
            //判断是否招募
            if(service.getIsRecruiting(taskStatusId)==0){
                return ResponseResultJson.failed("该任务未开启招募",Boolean.FALSE);
            }
            //获取团队id并判断当前登录用户是否为团队的队长
            Integer teamId = service.getTeamId(taskStatusId);
            Integer uid = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            String needUserType = "leader";
            if(!needUserType.equals(service.getUserType(teamId,uid))){
                ResponseResultJson.accessError("不是团队队长，无法添加团队成员",Boolean.FALSE);
            }
            //查看团队中是否有该成员
            if(service.getHasUidInTeam(teamId,uuid) > 0){
                return ResponseResultJson.failed("该成员已在该团队中",Boolean.FALSE);
            }
            String userType = "member";
            Team team = new Team();
            team.setTeamId(teamId)
                    .setUid(uuid)
                    .setUserType(userType);
            if(service.addToTeam(team)){
                return ResponseResultJson.success("添加队员成功",Boolean.TRUE);
            }else {
                return ResponseResultJson.failed("添加队员失败",Boolean.FALSE);
            }
        }catch (Exception e){
            return ResponseResultJson.failed(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation("队长修改招募状态")
    @RequestMapping(value = "/updateIsRecruiting",method = RequestMethod.PATCH)
    public ResponseResultJson<Boolean> updateIsRecruiting(@ApiParam("任务进行id") @RequestParam Integer taskStatusId,
                                                          @ApiParam("成员招募状态") @RequestParam Integer isRecruiting){
        if(!(isRecruiting.equals(0) || isRecruiting.equals(1))){
            return ResponseResultJson.paramError("成员招募状态必须为1或0",Boolean.FALSE);
        }
        try {
            Integer uid = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            Integer teamId = service.getTeamId(taskStatusId);
            String needUserType = "leader";
            if(!needUserType.equals(service.getUserType(teamId,uid))){
                ResponseResultJson.accessError("不是团队队长，无法修改招募状态",Boolean.FALSE);
            }
            if(service.updateIsRecruiting(taskStatusId, isRecruiting)){
                return ResponseResultJson.success("修改成员招募状态成功",Boolean.TRUE);
            }else {
                return ResponseResultJson.failed("修改成员招募状态失败",Boolean.FALSE);
            }
        }catch (Exception e){
            return ResponseResultJson.failed(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation("查看任务进行情况")
    @RequestMapping(value = "/getTaskStatus/{caseId}",method = RequestMethod.GET)
    public ResponseResultJson<Object> getTaskStatus(@ApiParam("案件id") @PathVariable("caseId") Integer caseId){
        try {
            List<TaskStatus> list = service.getTaskStatus(caseId);
            return ResponseResultJson.success("获取任务进行情况成功",list);
        }catch (Exception e){
            return ResponseResultJson.failed(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation("修改任务进行情况（任务未完成时）")
    @RequestMapping(value = "/updateTaskStatus",method = RequestMethod.PUT)
    public ResponseResultJson<Boolean> updateTaskStatus(@ApiParam("任务进行情况实体类") @RequestBody TaskStatus taskStatus){
        try {
            Integer uid = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            Integer teamId = service.getTeamId(taskStatus.getTaskStatusId());
            String needUserType = "leader";
            if(!needUserType.equals(service.getUserType(teamId,uid))){
                ResponseResultJson.accessError("不是团队队长，无法添加团队成员",Boolean.FALSE);
            }

            Integer iscompleted = 0;
            List<Subtasks> list = service.getTeamUnDoneSubtasks(teamId,iscompleted);
            if((list.size() > 0) && taskStatus.getTaskStatus().equals("completed")){
                return ResponseResultJson.failed("还有子任务未完成，不能设置任务进行情况为已完成状态",Boolean.FALSE);
            }
            String needtaskStatusStatus = "undone";
            if(!needtaskStatusStatus.equals(service.getTaskStatusStatus(taskStatus.getTaskStatusId()))){
                return ResponseResultJson.failed("该任务进行情况已完成，不能修改",Boolean.FALSE);
            }
            if(service.updateTaskStatus(taskStatus)) {
                return ResponseResultJson.success("修改任务进行情况成功", Boolean.TRUE);
            }else {
                return ResponseResultJson.failed("修改任务进行情况失败", Boolean.FALSE);
            }
        }catch (Exception e){
            return ResponseResultJson.failed(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation("修改任务进行情况是否已完成")
    @RequestMapping(value = "/updateIsTaskStatusCompleted",method = RequestMethod.PATCH)
    public ResponseResultJson<Boolean> updateIsTaskStatusCompleted(@ApiParam("任务进行id") @RequestParam Integer taskStatusId,
                                                                   @ApiParam("任务进行情况") @RequestParam String taskStatus){
        try {
            Integer uid = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            Integer teamId = service.getTeamId(taskStatusId);
            String needUserType = "leader";
            if(!needUserType.equals(service.getUserType(teamId,uid))){
                ResponseResultJson.accessError("不是团队队长，无法任务进行情况完成状态",Boolean.FALSE);
            }
            Integer isCompleted = 0;
            List<Subtasks> list = service.getTeamUnDoneSubtasks(teamId,isCompleted);
            if((list.size() > 0) && taskStatus.equals("completed")){
                return ResponseResultJson.failed("还有子任务未完成，不能设置任务进行情况为已完成状态",Boolean.FALSE);
            }
            if(service.updateTaskStatusStatus(taskStatusId, taskStatus)){
                return ResponseResultJson.success("修改任务进行情况是否已完成成功",Boolean.TRUE);
            }else {
                return ResponseResultJson.failed("修改任务进行情况是否已完成失败",Boolean.FALSE);
            }
        }catch (Exception e){
            return ResponseResultJson.failed(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation("队长分配子任务")
    @RequestMapping(value = "/addSubtasks",method = RequestMethod.POST)
    public ResponseResultJson<Boolean> addSubtasks(@ApiParam("团队id") @RequestParam Integer teamId,
                                                   @ApiParam("队员id") @RequestParam Integer uuid,
                                                   @ApiParam("子任务描述") @RequestParam String subtasksDesciption,
                                                   @ApiParam("子任务附件") @RequestPart("file") MultipartFile file,
                                                   @ApiParam("创建时间") @RequestParam Date updateTime,
                                                   @ApiParam("任务完成权重") @RequestParam Double weights,
                                                   @ApiParam("是否有提交结果") @RequestParam Integer isSubmit,
                                                   @ApiParam("是否已完成") @RequestParam Integer isCompleted){
        try {
            Integer uid = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            String needUserType = "leader";
            if(!needUserType.equals(service.getUserType(teamId,uid))){
                ResponseResultJson.accessError("不是团队队长，无法添加子任务",Boolean.FALSE);
            }
            Integer typeId = service.getMaxSubtasksId()+1;
            Subtasks subtasks = new Subtasks();
            if(file.getSize() == 0 || !StringUtils.isNotBlank(file.getOriginalFilename())) {
                subtasks.setSubtasksId(typeId)
                        .setTeamId(teamId)
                        .setUid(uuid)
                        .setSubtasksDesciption(subtasksDesciption)
                        .setSubtasksAttachmentsUrl(null)
                        .setUpdateTime(updateTime)
                        .setWeights(weights)
                        .setIsSubmit(isSubmit)
                        .setIsCompleted(isCompleted);
            }else {
                String type = "Subtasks";
                String subtasksAttachmentsUrl = fileService.addAttachments(type, typeId, file);
                //判断文件是否上传失败
                if (subtasksAttachmentsUrl.equals("false")) {
                    return ResponseResultJson.failed("上传文件失败，未创建子任务", Boolean.FALSE);
                } else {
                    subtasks.setSubtasksId(typeId)
                            .setTeamId(teamId)
                            .setUid(uuid)
                            .setSubtasksDesciption(subtasksDesciption)
                            .setSubtasksAttachmentsUrl(subtasksAttachmentsUrl)
                            .setUpdateTime(updateTime)
                            .setWeights(weights)
                            .setIsSubmit(isSubmit)
                            .setIsCompleted(isCompleted);
                }
            }
            if(service.addSubtasks(subtasks)){
                return ResponseResultJson.success("添加子任务成功",Boolean.TRUE);
            }else {
                return ResponseResultJson.failed("添加子任务失败",Boolean.FALSE);
            }
        }catch (Exception e){
            return ResponseResultJson.failed(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation("修改子任务")
    @RequestMapping(value = "/updateSubTask",method = RequestMethod.PUT)
    public ResponseResultJson<Boolean> updateSubTask(@ApiParam("子任务实体类") @RequestBody Subtasks subtasks){
        try {
            Integer uid = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            String needUserType = "leader";
            if(!needUserType.equals(service.getUserType(subtasks.getTeamId(),uid))){
                ResponseResultJson.accessError("不是团队队长，无法修改子任务",Boolean.FALSE);
            }
            if(service.updateSubTasks(subtasks)){
                return ResponseResultJson.success("修改子任务成功",Boolean.TRUE);
            }else {
                return ResponseResultJson.failed("修改子任务失败",Boolean.FALSE);
            }
        }catch (Exception e){
            return ResponseResultJson.failed(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation("删除子任务")
    @RequestMapping(value = "/deleteSubTask",method = RequestMethod.DELETE)
    public ResponseResultJson<Boolean> deleteSubTask(@ApiParam("") @RequestParam Integer subtasksId){
        try {
            Integer uid = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            String needUserType = "leader";
            Integer teamId = service.getSubTeamId(subtasksId);
            if(!needUserType.equals(service.getUserType(teamId,uid))){
                ResponseResultJson.accessError("不是团队队长，无法修改子任务",Boolean.FALSE);
            }
            if(service.deleteSubTask(subtasksId)){
                return ResponseResultJson.success("删除子任务成功",Boolean.TRUE);
            }else {
                return ResponseResultJson.failed("删除子任务失败",Boolean.FALSE);
            }
        }catch (Exception e){
            return ResponseResultJson.failed(e.getMessage(),Boolean.FALSE);
        }
    }


    @PreAuthorize("isAuthenticated()")
    @ApiOperation("查看自己未完成子任务")
    @RequestMapping(value = "/getSelfUnDoneSubtasks",method = RequestMethod.GET)
    public ResponseResultJson<Object> getSelfUnDoneSubtasks(){
        try {
            Integer uid = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            Integer isCompleted = 0;
            List<Subtasks> list = service.getSelfUnDoneSubtasks(uid,isCompleted);
            return ResponseResultJson.success("获取个人未完成子任务成功",list);
        }catch (Exception e){
            return ResponseResultJson.failed(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation("队长查看团队所有成员未完成子任务")
    @RequestMapping(value = "/getTeamUnDoneSubtasks",method = RequestMethod.GET)
    public ResponseResultJson<Object> getTeamUnDoneSubtasks(@ApiParam("团队id") @RequestParam Integer teamId){
        try {
            Integer uid = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            String userType = service.getUserType(teamId,uid);
            String needUserType = "leader";
            if(!needUserType.equals(userType)){
                return ResponseResultJson.accessError("权限不足，无法查看团队所有未完成子任务",Boolean.FALSE);
            }
            Integer isCompleted = 0;
            List<Subtasks> list = service.getTeamUnDoneSubtasks(teamId,isCompleted);
            return ResponseResultJson.success("获取团队所有未完成子任务成功",list);
        }catch (Exception e){
            return ResponseResultJson.failed(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation("队长查看团队单个成员未完成子任务")
    @RequestMapping(value = "/getTeamUnDoneSubtasksById/{teamId}/{uuid}",method = RequestMethod.GET)
    public ResponseResultJson<Object> getTeamUnDoneSubtasksById(@ApiParam("团队id") @PathVariable("teamId") Integer teamId,
                                                            @ApiParam("队员id") @PathVariable("uuid") Integer uuid){
        try {
            Integer uid = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            String userType = service.getUserType(teamId,uid);
            String needUserType = "leader";
            if(!needUserType.equals(userType)){
                return ResponseResultJson.accessError("权限不足，无法查看团队所有未完成子任务",Boolean.FALSE);
            }
            Integer isCompleted = 0;
            uid=uuid;
            List<Subtasks> list = service.getTeamUnDoneSubtasksById(teamId,uid,isCompleted);
            return ResponseResultJson.success("获取团队成员所有未完成子任务成功",list);
        }catch (Exception e){
            return ResponseResultJson.failed(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation("提交个人任务完成结果")
    @RequestMapping(value = "/submitTackResult",method = RequestMethod.POST)
    public ResponseResultJson<Boolean> submitTackResult(@ApiParam("子任务id") @RequestParam Integer subtasksId,
                                                        @ApiParam("工作量描述") @RequestParam String completionInfo,
                                                        @ApiParam("视频文件") @RequestPart("video") MultipartFile video,
                                                        @ApiParam("个人任务完成情况附件") @RequestPart("file") MultipartFile file,
                                                        @ApiParam("提交时间") @RequestParam Date updateTime,
                                                        @ApiParam("保存状态(save或submit)") @RequestParam String saveOrSubmit){
        if(!(saveOrSubmit.equals("save") || saveOrSubmit.equals("submit"))){
            return ResponseResultJson.paramError("saveOrSubmit字段必须为save或submit",Boolean.FALSE);
        }
        try {
            Integer uid = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            Integer typeId = service.getMaxCompletionId() + 1;
            Completion completion = new Completion();
            //先上传视频
            if (file.getSize() == 0 || !StringUtils.isNotBlank(file.getOriginalFilename())) {
                completion.setCompletionId(typeId)
                        .setSubtasksId(subtasksId)
                        .setUid(uid)
                        .setCompletionInfo(completionInfo)
                        .setCompletionVideoUrl(null)
                        .setCompletionAttachmentsUrl(null)
                        .setUpdateTime(updateTime);
            } else {
                String type = "Completion";
                String completionVideoUrl = fileService.addVideo(type, typeId, video);
                //判断文件是否上传失败
                if (completionVideoUrl.equals("false") || completionVideoUrl.equals("服务超时")) {
                    return ResponseResultJson.failed("上传视频文件失败("+completionVideoUrl+")，未完成上传个人任务完成情况", Boolean.FALSE);
                } else {
                    completion.setCompletionId(typeId)
                            .setSubtasksId(subtasksId)
                            .setUid(uid)
                            .setCompletionInfo(completionInfo)
                            .setCompletionVideoUrl(completionVideoUrl)
                            .setCompletionAttachmentsUrl(null)
                            .setUpdateTime(updateTime);
                }
                String completionAttachmentsUrl = fileService.addAttachments(type, typeId, file);
                if (completionAttachmentsUrl.equals("false") || completionAttachmentsUrl.equals("服务超时")) {
                    return ResponseResultJson.failed("上传附件失败("+completionAttachmentsUrl+")，未完成上传个人任务完成情况", Boolean.FALSE);
                } else {
                    completion.setCompletionId(typeId)
                            .setSubtasksId(subtasksId)
                            .setUid(uid)
                            .setCompletionInfo(completionInfo)
                            .setCompletionVideoUrl(completionVideoUrl)
                            .setCompletionAttachmentsUrl(completionAttachmentsUrl)
                            .setUpdateTime(updateTime);
                }
            }

            if (saveOrSubmit.equals("save")) {
                if (service.submitTackResult(completion)) {
                    Integer isSubmit = 1;
                    if (service.updateIsSubmit(completion.getSubtasksId(), isSubmit)) {
                        return ResponseResultJson.success("保存提交任务结果并修改是否有提交结果成功", Boolean.TRUE);
                    } else {
                        return ResponseResultJson.failed("保存任务结果成功，修改是否有提交结果失败", Boolean.FALSE);
                    }
                } else {
                    return ResponseResultJson.failed("提交任务结果失败", Boolean.FALSE);
                }
            } else {
                if(service.submitTackResult(completion)){
                    Integer isSubmit = 1;
                    Integer isCompleted = 1;
                    if(service.updateIsSubmit(completion.getSubtasksId(),isSubmit)&&service.updateIsCompleted(completion.getSubtasksId(),isCompleted)){
                        return ResponseResultJson.success("提交任务结果成功并修改子任务表成功",Boolean.TRUE);
                    }else {
                        return ResponseResultJson.failed("提交任务结果成功，修改子任务表失败",Boolean.FALSE);
                    }
                }else {
                    return ResponseResultJson.failed("提交任务结果失败",Boolean.FALSE);
                }
            }
        }catch (Exception e){
            return ResponseResultJson.failed(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation("下载文件")
    @RequestMapping(value = "/downloadFile",method = RequestMethod.POST)
    public ResponseResultJson<Object> downloadFile(@ApiParam("文件保存地址") @RequestParam String downLoadUrl,
                                                    @ApiParam("下载保存位置") @RequestParam String saveUrl){
        try {
            String mesasge = fileService.downloadFile(downLoadUrl,saveUrl);
            if (mesasge.equals("true")){
                return ResponseResultJson.success("下载成功",Boolean.TRUE);
            }else {
                return ResponseResultJson.success("下载失败",mesasge);
            }
        }catch (Exception e){
            return ResponseResultJson.failed(e.getMessage(),Boolean.FALSE);
        }
    }
}
