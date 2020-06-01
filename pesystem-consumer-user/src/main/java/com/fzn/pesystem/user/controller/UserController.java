package com.fzn.pesystem.user.controller;

import com.fzn.pesystem.common.entities.User;
import com.fzn.pesystem.common.response.ResponseResultJson;
import com.fzn.pesystem.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private IUserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("isAuthenticated()")
    @ApiOperation("更新密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.PATCH)
    public ResponseResultJson<Boolean> updatePassword(@ApiParam("旧密码") @RequestParam String oldPassword,
                                                      @ApiParam("新密码") @RequestParam String newPassword) {
        if (StringUtils.isEmpty(oldPassword)) {
            return ResponseResultJson.paramError("旧密码为空", Boolean.FALSE);
        }
        if (StringUtils.isEmpty(newPassword)) {
            return ResponseResultJson.paramError("新密码为空", Boolean.FALSE);
        }
        if (newPassword.equals(oldPassword)) {
            return ResponseResultJson.failed("新旧密码不能相同", Boolean.FALSE);
        }
        try {
            Integer uid = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            String password = service.getUserPasswordByUid(uid);
            password = password.substring(password.indexOf('}') + 1);
            if (passwordEncoder.matches(oldPassword, password)) {
                newPassword = "{bcrypt}" + passwordEncoder.encode(newPassword);
                if (service.updatePasswordByUid(uid, newPassword)) {
                    return ResponseResultJson.success("修改密码成功", Boolean.TRUE);
                } else {
                    return ResponseResultJson.unknownError("数据库异常", Boolean.FALSE);
                }
            } else {
                return ResponseResultJson.failed("密码不正确", Boolean.FALSE);
            }
        } catch (Exception e) {
            return ResponseResultJson.unknownError(e.getMessage(), Boolean.FALSE);
        }
    }


    @PreAuthorize("hasAnyAuthority('admin,sectionAdmin')")
    @ApiOperation("更新用户信息")
    @RequestMapping(value = "/updateInfo", method = RequestMethod.PATCH)
    public ResponseResultJson<Boolean> updateInfo(@ApiParam("姓名") @RequestParam String name,
                                                  @ApiParam("手机号") @RequestParam String phone,
                                                  @ApiParam("部门id") @RequestParam String departId) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(departId)) {
            return ResponseResultJson.failed("输入有空值", Boolean.FALSE);
        }
        Integer uid = Integer.parseInt((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        try {
            if (service.updateInfoByUid(uid, name, phone, departId)) {
                return ResponseResultJson.success("修改用户信息成功", Boolean.TRUE);
            } else {
                return ResponseResultJson.unknownError("数据库异常", Boolean.FALSE);
            }
        } catch (Exception e) {
            return ResponseResultJson.unknownError("数据库异常", Boolean.FALSE);
        }

    }

    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation("添加用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseResultJson<Boolean> addUser(@ApiParam("用户id") @RequestParam Integer uid,
                                               @ApiParam("用户密码") @RequestParam String password,
                                               @ApiParam("姓名") @RequestParam String name,
                                               @ApiParam("手机号") @RequestParam String phone,
                                               @ApiParam("部门id") @RequestParam Integer departId,
                                               @ApiParam("父部门id") @RequestParam Integer topDepartId,
                                               @ApiParam("角色id") @RequestParam Integer roleId) {
        try {
            if (service.existUser(uid)) {
                return ResponseResultJson.failed("已存在该用户", Boolean.FALSE);
            }
            if ("admin".equals(service.getRoleCodeByRoleId(roleId))) {
                return ResponseResultJson.failed("不能直接添加超级管理员", Boolean.FALSE);
            }
            User user = new User();
            user.setUid(uid)
                    .setPassword(new BCryptPasswordEncoder().encode(password))
                    .setName(name)
                    .setPhone(phone)
                    .setDepartId(departId)
                    .setTopDepartId(topDepartId)
                    .setScore((double) 0);
            if (!service.insertUser(user)) {
                return ResponseResultJson.unknownError("添加用户失败", Boolean.FALSE);
            }
            if (!service.insertUserRole(uid, roleId)) {
                service.deleteUser(uid);
                return ResponseResultJson.unknownError("添加用户角色失败", Boolean.FALSE);
            }
            return ResponseResultJson.success("添加用户成功", Boolean.TRUE);
        } catch (Exception e) {
            return ResponseResultJson.unknownError(e.getMessage(), Boolean.FALSE);
        }
    }
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation("更新用户权限（角色）")
    @RequestMapping(value = "/updateRoleByUid", method = RequestMethod.PATCH)
    public ResponseResultJson<Boolean> updateRoleByUid(@ApiParam("用户id") Integer uid,
                                                       @ApiParam("身份id") Integer roleId){
        try {
            if (!service.existUser(uid)) {
                return ResponseResultJson.failed("不存在该用户", Boolean.FALSE);
            }
            service.updateRoleByUid(uid,roleId);
            return ResponseResultJson.success("权限修改成功",Boolean.TRUE);
        } catch (Exception e) {
            return ResponseResultJson.unknownError(e.getMessage(), Boolean.FALSE);
        }
    }
}
