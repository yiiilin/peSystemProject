package com.fzn.pesystem.provider.controller;

import com.fzn.pesystem.common.entities.User;
import com.fzn.pesystem.common.service.IUserClientService;
import com.fzn.pesystem.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements IUserClientService {

    @Autowired
    private UserService service;

    @Override
    public Boolean existUser(Integer uid) {
        return service.existUser(uid);
    }

    @Override
    public String getUserPasswordByUid(Integer uid) {
        return service.getUserPasswordByUid(uid);
    }

    @Override
    public Boolean updatePasswordByUid(Integer uid, String newPassword) {
        return service.updatePasswordByUid(uid, newPassword);
    }

    @Override
    public Boolean updateInfoByUid(Integer uid, String name, String phone, String departId) {
        return service.updateInfoByUid(uid, name, phone, departId);
    }

    @Override
    public Boolean insertUser(User user) {
        return service.insertUser(user);
    }

    @Override
    public Boolean deleteUser(Integer uid) {
        return service.deleteUser(uid);
    }

    @Override
    public Boolean insertUserRole(Integer uid, Integer roleId) {
        return service.insertUserRole(uid, roleId);
    }

    @Override
    public String getRoleCodeByRoleId(Integer roleId) {
        return service.getRoleCodeByRoleId(roleId);
    }

    @Override
    public Boolean updateRoleByUid(Integer uid, Integer role_id){
        return service.updateRoleByUid(uid,role_id);
    }
}
