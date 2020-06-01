package com.fzn.pesystem.provider.service;

import com.fzn.pesystem.common.entities.*;

import java.util.Date;
import java.util.List;

public interface DepartService {

    List<Depart> getAllDepart();

    Depart getDepartByDepartId(Integer departId);

    List<User> getUserByDepartId(Integer departId);

    Boolean upDateDepartByDepartId(Depart depart);

    List<Perform> getListPersonalByUid(Integer uid);

    Integer insertNotice(Notice notice);

    List<Notice> getAllNotice();

    List<Notice> getNoticeByDate(Date date);

}
