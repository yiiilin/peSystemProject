package com.fzn.pesystem.uaa.config;

import com.fzn.pesystem.common.entities.User;
import com.fzn.pesystem.uaa.dao.RBACDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomTokenEnhancer implements TokenEnhancer {

    @Autowired
    private RBACDao dao;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Object principal = authentication.getPrincipal();
        User user = null;
        if (principal instanceof org.springframework.security.core.userdetails.User) {
            user = dao.getUserByUid(((org.springframework.security.core.userdetails.User) principal).getUsername());
        }
        final Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("uid", user.getUid());
        additionalInfo.put("name", user.getName());
        additionalInfo.put("phone", user.getPhone());
        additionalInfo.put("departId", user.getDepartId());
        String departName = null;
        if (user.getDepartId() != null) {
            departName = dao.getDepartNameByDepartId(user.getDepartId().toString());
        }
        additionalInfo.put("departName", departName);
        // 注意添加的额外信息，最好不要和已有的json对象中的key重名，容易出现错误
        //additionalInfo.put("authorities", user.getAuthorities());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
