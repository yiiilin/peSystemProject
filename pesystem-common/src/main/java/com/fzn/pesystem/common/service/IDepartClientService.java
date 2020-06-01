package com.fzn.pesystem.common.service;

import com.fzn.pesystem.common.entities.Depart;
import com.fzn.pesystem.common.entities.Notice;
import com.fzn.pesystem.common.entities.Perform;
import com.fzn.pesystem.common.entities.User;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author WOLD
 *
 */
@RequestMapping(value = "/depart")
public interface IDepartClientService {

    @RequestMapping(value = "/queryAllDepart",method = RequestMethod.GET)
    public List<Depart> queryAllDepart();

    @RequestMapping(value = "/queryDepartById/{departId}",method = RequestMethod.GET)
    public Depart queryDepartById(@PathVariable("departId") Integer departId);

    @RequestMapping(value = "/queryUserByDepartId",method = RequestMethod.POST)
    public List<User> queryUserByDepartId(@RequestParam Integer departId);

    @RequestMapping(value = "/upDateDepartByDepartId",method = RequestMethod.PUT)
    public Boolean upDateDepartByDepartId(@RequestBody Depart depart);

    @RequestMapping(value = "/getListPersonalByUid/{uid}",method = RequestMethod.GET)
    public List<Perform> getListPersonalByUid(@PathVariable("uid") Integer uid);

    @RequestMapping(value = "addNotice",method = RequestMethod.POST)
    public Integer insertNotice(@RequestParam String noticeTitle,
                                @RequestParam String noticeInfo,
                                @RequestParam String noticeAttachmentsUrl);

    @RequestMapping(value = "/queryAllNotice",method = RequestMethod.GET)
    public List<Notice> queryAllNotice();

    @RequestMapping(value = "/queryUserByDepartId/{date}",method = RequestMethod.GET)
    public  List<Notice>  queryNoticeByDate(@PathVariable String date);
}
