package com.fzn.pesystem.depart.controller;

import com.fzn.pesystem.common.entities.Depart;
import com.fzn.pesystem.common.entities.Notice;
import com.fzn.pesystem.common.entities.Perform;
import com.fzn.pesystem.common.entities.User;
import com.fzn.pesystem.common.response.ResponseResultJson;
import com.fzn.pesystem.depart.service.IDepartService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class DepartController {

    @Autowired
    private IDepartService departService;

    @PreAuthorize("hasAnyAuthority('general')")
    @ApiOperation("查询全部部门")
    @RequestMapping(value = "/getAllDepart",method = RequestMethod.GET)
    public ResponseResultJson<List<Depart>> getAllDepart(){
        try{
            List<Depart> departList=departService.queryAllDepart();
            return ResponseResultJson.success("查询成功",departList);
        }
        catch (Exception e){
            return ResponseResultJson.unknownError(e.getMessage(),null);
        }
    }
    /**
     * 查询通过id查询部门部门结构
     * @param departId
     * @return
     */
    @PreAuthorize("hasAuthority('general')")
    @ApiOperation("通过部门ID查询部门")
    @RequestMapping(value = "/getDepart/{departId}",method = RequestMethod.GET)
    public ResponseResultJson<Depart> getDepart(@ApiParam("部门ID") @PathVariable("departId") Integer departId){
        try{
            Depart depart=departService.queryDepartById(departId);
            return  ResponseResultJson.success("查询成功",depart);
        }
        catch (Exception e){
            return ResponseResultJson.unknownError(e.getMessage(),null);
        }
    }

    /**
     * 查询部门人员结构(出现错误)
     * @param departId
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("通过部门id查询部门全部成员")
    @RequestMapping(value = "/getMember/{departId}",method = RequestMethod.GET)
    public ResponseResultJson<List<User>> getMember(@ApiParam("部门id") @PathVariable("departId") Integer departId){
        try{
            List<User> userList= departService.queryUserByDepartId(departId);
            return  ResponseResultJson.success("查询成功",userList);
        }
        catch (Exception e){
            return ResponseResultJson.unknownError(e.getMessage(),null);
        }
    }

    /**
     * 修改部门结构
     */
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation("修改部门结构")
    @RequestMapping(value = "/updateDepart",method=RequestMethod.PUT)
    public ResponseResultJson<Boolean> updateDepart(@ApiParam("修改后的部门类") @RequestBody Depart depart){
        try{
            departService.upDateDepartByDepartId(depart);
            return ResponseResultJson.success("更新成功",Boolean.TRUE);
        }
        catch (Exception e){
            return ResponseResultJson.unknownError(e.getMessage(),Boolean.FALSE);
        }
    }


    /**
     * 查看个人绩效
     */
    @PreAuthorize("hasAuthority('general')")
    @ApiOperation("通过用户id查询绩效")
    @RequestMapping(value = "/queryPersonalMark/{uid}",method = RequestMethod.GET)
    public ResponseResultJson<List<Perform>> queryPersonalMark(@ApiParam("用户id") @PathVariable("uid") Integer uid){
        try{
            List<Perform> performList=departService.getListPersonalByUid(uid);
            return ResponseResultJson.success("查询成功",performList);
        }catch (Exception e){
            return ResponseResultJson.unknownError(e.getMessage(),null);
        }
    }

    /**
     * 发布考核通知
     */
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation("发布考核通知")
    @RequestMapping(value = "/addNotice",method = RequestMethod.POST)
    public ResponseResultJson<Boolean> addNotice(@ApiParam("考核标题") @RequestParam String noticeTitle,
                                                 @ApiParam("考核信息") @RequestParam String noticeInfo,
                                                 @ApiParam("考核附件") @RequestParam String noticeAttachmentsUrl){
        try{
//            Notice notice=new Notice();
//            notice.setNoticeTitle(noticeTitle);
//            notice.setNoticeInfo(noticeInfo);
//            notice.setNoticeAttachmentsUrl(noticeAttachmentsUrl);
//            notice.setUpdateTime(new Date());
            Integer result= departService.insertNotice(noticeTitle,noticeInfo,noticeAttachmentsUrl);
            return ResponseResultJson.success("发布成功",Boolean.TRUE);
        }
        catch (Exception e){
            return ResponseResultJson.unknownError(e.getMessage(),Boolean.FALSE);
        }
    }

    @PreAuthorize("hasAuthority('general')")
    @ApiOperation("查看全部考核通知")
    @RequestMapping(value = "/getAllNotice",method = RequestMethod.GET)
    public ResponseResultJson<List<Notice>> getAllNotice(){
        try{
            List<Notice> noticeList=departService.queryAllNotice();
            return ResponseResultJson.success("查询成功",noticeList);
        }
        catch (Exception e){
            return ResponseResultJson.unknownError(e.getMessage(),null);
        }
    }

    @PreAuthorize("hasAuthority('general')")
    @ApiOperation("查看指定日期前发布的考核通知")
    @RequestMapping(value = "/getNotice/{date}",method = RequestMethod.GET)
    public ResponseResultJson<List<Notice>> getNotice(@ApiParam("发布日期") @PathVariable("date") String date){
        try{
            List<Notice> noticeList=departService.queryNoticeByDate(date);
            return ResponseResultJson.success("查询成功",noticeList);
        }
        catch (Exception e){
            return ResponseResultJson.unknownError(e.getMessage(),null);
        }
    }
}
