package com.fzn.pesystem.common.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("simple")
public interface ISimpleClientService {
    @RequestMapping(value = "simpleMethod",method = RequestMethod.GET)
    public String simpleMethod();
}
