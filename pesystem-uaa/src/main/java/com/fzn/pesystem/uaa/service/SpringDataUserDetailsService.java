package com.fzn.pesystem.uaa.service;


import com.fzn.pesystem.common.entities.User;
import com.fzn.pesystem.uaa.dao.RBACDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装用户信息时需要
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {
    @Autowired
    private RBACDao rbacDao;

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        try{
            User user = rbacDao.getUserByUid(uid);
            String roleCode = rbacDao.getRoleCodeByUid(user.getUid().toString());
            List<String> authorities=rbacDao.getAuthorityByRoleCode(roleCode);
            authorities.add("ROLE_"+roleCode);
            authorities.addAll(authorities);
            String[] authoritiesArray = authorities.toArray(new String[authorities.size()]);
            UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUid().toString())
                    .password(user.getPassword())
                    .authorities(authoritiesArray)
                    .build();
            return userDetails;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
