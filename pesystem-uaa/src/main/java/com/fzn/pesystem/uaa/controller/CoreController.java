package com.fzn.pesystem.uaa.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzn.pesystem.common.response.ResponseResultJson;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoreController {
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseResultJson<Object> getUserInfo() {
        String details = JSONObject.toJSONString(SecurityContextHolder.getContext().getAuthentication().getDetails());
        JSONObject jsonObject = JSONObject.parseObject(details);
        Object userDetails =jsonObject.get("decodedDetails");
        return ResponseResultJson.success("", userDetails);
    }
}