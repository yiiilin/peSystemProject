package com.fzn.pesystem.common.service;

import com.fzn.pesystem.common.entities.User;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
public interface IUserClientService {
    @RequestMapping(value = "existUser/{uid}",method = RequestMethod.GET)
    public Boolean existUser(@PathVariable("uid") Integer uid);

    @RequestMapping(value = "getUserPasswordByUid/{uid}",method = RequestMethod.GET)
    public String getUserPasswordByUid(@PathVariable("uid") Integer uid);

    @RequestMapping(value = "updatePasswordByUid",method = RequestMethod.POST)
    public Boolean updatePasswordByUid(@RequestParam Integer uid,@RequestParam String newPassword);

    @RequestMapping(value = "updateInfoByUid",method = RequestMethod.POST)
    public Boolean updateInfoByUid(@RequestParam Integer uid,@RequestParam String name,@RequestParam String phone,@RequestParam String departId);

    @RequestMapping(value = "insertUser",method = RequestMethod.POST)
    public Boolean insertUser(@RequestBody User user);

    @RequestMapping(value = "deleteUser/{uid}",method = RequestMethod.GET)
    public Boolean deleteUser(@PathVariable("uid") Integer uid);

    @RequestMapping(value = "insertUserRole",method = RequestMethod.POST)
    public Boolean insertUserRole(@RequestParam Integer uid,@RequestParam Integer roleId);

    @RequestMapping(value = "getRoleCodeByRoleId/{roleId}",method = RequestMethod.GET)
    public String getRoleCodeByRoleId(@PathVariable("roleId") Integer roleId);

    @RequestMapping(value = "updateRoleByUid" ,method = RequestMethod.POST)
    public Boolean updateRoleByUid(@RequestParam Integer uid,@RequestParam Integer role_id);
}
