package com.fzn.pesystem.uaa.dao;


import com.fzn.pesystem.common.entities.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RBACDao {
    public User getUserByUid(String uid);
    public String getRoleCodeByUid(String uid);
    public List<String> getAuthorityByRoleCode(String roleCode);
    public String getDepartNameByDepartId(String departId);
}
