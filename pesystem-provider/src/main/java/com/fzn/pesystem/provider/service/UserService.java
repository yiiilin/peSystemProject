package com.fzn.pesystem.provider.service;

import com.fzn.pesystem.common.entities.User;

public interface UserService {
    public Boolean existUser(Integer uid);

    public String getUserPasswordByUid(Integer uid);

    public Boolean updatePasswordByUid(Integer uid, String newPassword);

    public Boolean updateInfoByUid(Integer uid, String name, String phone, String departId);

    public Boolean insertUser(User user);

    public Boolean deleteUser(Integer uid);

    public Boolean insertUserRole(Integer uid, Integer roleId);

    public String getRoleCodeByRoleId(Integer roleId);

    public Boolean updateRoleByUid(Integer uid, Integer roleId);
}
