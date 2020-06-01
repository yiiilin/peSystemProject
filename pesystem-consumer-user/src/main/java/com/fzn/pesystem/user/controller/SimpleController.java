package com.fzn.pesystem.user.controller;

import com.fzn.pesystem.user.service.ISimpleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SimpleController {
    @Autowired
    private ISimpleService simpleService;

    @RequestMapping(value = "simpleMethod", method = RequestMethod.GET)
    public String SimpleMethod() {
        String result = simpleService.simpleMethod();
        log.info("这里是消费者，此次获取的数据内容是：" + result);
        return result;
    }
}
