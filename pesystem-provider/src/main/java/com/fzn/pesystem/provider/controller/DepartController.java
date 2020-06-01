package com.fzn.pesystem.provider.controller;

import com.fzn.pesystem.common.entities.Depart;
import com.fzn.pesystem.common.entities.Notice;
import com.fzn.pesystem.common.entities.Perform;
import com.fzn.pesystem.common.entities.User;
import com.fzn.pesystem.common.service.IDepartClientService;
import com.fzn.pesystem.provider.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class DepartController implements IDepartClientService {

    @Autowired
    private DepartService service;
    @Override
    public List<Depart> queryAllDepart() {
            return service.getAllDepart();
    }

    @Override
    public Depart queryDepartById(Integer departId) {
        return service.getDepartByDepartId(departId);
    }

    @Override
    public List<User> queryUserByDepartId(Integer departId) {
        return service.getUserByDepartId(departId);
    }

    @Override
    public Boolean upDateDepartByDepartId(Depart depart) {
        return service.upDateDepartByDepartId(depart);
    }

    @Override
    public List<Perform> getListPersonalByUid(Integer uid) {
        return service.getListPersonalByUid(uid);
    }

    @Override
    public Integer insertNotice(String noticeTitle,String noticeInfo,String noticeAttachmentsUrl) {
            Notice notice=new Notice();
            notice.setNoticeTitle(noticeTitle);
            notice.setNoticeInfo(noticeInfo);
            notice.setNoticeAttachmentsUrl(noticeAttachmentsUrl);
            notice.setUpdateTime(new Date());
            Integer result= service.insertNotice(notice);
            return result;
    }

    @Override
    public List<Notice> queryAllNotice() {
        return service.getAllNotice();
    }

    @Override
    public List<Notice> queryNoticeByDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return service.getNoticeByDate(sdf.parse(date));
        } catch (ParseException e) {
            return null;
        }
    }
}
