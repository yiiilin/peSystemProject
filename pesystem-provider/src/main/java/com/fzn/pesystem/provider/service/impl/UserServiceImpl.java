package com.fzn.pesystem.provider.service.impl;

import com.fzn.pesystem.common.entities.User;
import com.fzn.pesystem.provider.dao.UserDao;
import com.fzn.pesystem.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public Boolean existUser(Integer uid) {
        return dao.existUser(uid);
    }

    @Override
    public String getUserPasswordByUid(Integer uid) {
        return dao.getUserPasswordByUid(uid);
    }

    @Override
    public Boolean updatePasswordByUid(Integer uid, String newPassword) {
        return dao.updatePasswordByUid(uid,newPassword);
    }

    @Override
    public Boolean updateInfoByUid(Integer uid, String name, String phone, String departId) {
        return dao.updateInfoByUid(uid, name, phone, departId);
    }

    @Override
    public Boolean insertUser(User user) {
        return dao.insertUser(user);
    }

    @Override
    public Boolean deleteUser(Integer uid) {
        return dao.deleteUser(uid);
    }

    @Override
    public Boolean insertUserRole(Integer uid, Integer roleId) {
        return dao.insertUserRole(uid, roleId);
    }

    @Override
    public String getRoleCodeByRoleId(Integer roleId) {
        return dao.getRoleCodeByRoleId(roleId);
    }

    @Override
    public Boolean updateRoleByUid(Integer uid, Integer roleId) {
        return dao.updateRoleByUid(uid, roleId);
    }
}
