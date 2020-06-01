package com.fzn.pesystem.provider.service.impl;

import com.fzn.pesystem.common.entities.*;
import com.fzn.pesystem.provider.dao.DepartDao;
import com.fzn.pesystem.provider.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    private DepartDao dao;

    @Override
    public List<Depart> getAllDepart() {
        return dao.getAllDepart();
    }

    @Override
    public Depart getDepartByDepartId(Integer departId) {
        return dao.getDepartByDepartId(departId);
    }

    @Override
    public List<User> getUserByDepartId(Integer departId) {
        return dao.getUserByDepartId(departId);
    }

    @Override
    public Boolean upDateDepartByDepartId(Depart depart) {
        return dao.upDateDepartByDepartId(depart);
    }

    @Override
    public List<Perform> getListPersonalByUid(Integer uid) {
        return dao.getListPersonalByUid(uid);
    }

    @Override
    public Integer insertNotice(Notice notice) {
        return dao.insertNotice(notice);
    }

    @Override
    public List<Notice> getAllNotice() {
        return dao.getAllNotice();
    }

    @Override
    public List<Notice> getNoticeByDate(Date date) {
        return dao.getNoticeByDate(date);
    }

}
