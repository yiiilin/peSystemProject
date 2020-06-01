package com.fzn.pesystem.provider.dao;

import com.fzn.pesystem.common.entities.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface DepartDao {

    List<Depart> getAllDepart();

    Depart getDepartByDepartId(Integer departId);

    List<User> getUserByDepartId(Integer departId);

    Boolean upDateDepartByDepartId(Depart depart);

    List<Perform> getListPersonalByUid(Integer uid);

    Integer insertNotice(Notice notice);

    List<Notice> getAllNotice();

    List<Notice> getNoticeByDate(Date date);

}
