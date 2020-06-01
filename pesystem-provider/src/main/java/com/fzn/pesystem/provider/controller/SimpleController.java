package com.fzn.pesystem.provider.controller;

import com.fzn.pesystem.common.service.ISimpleClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController implements ISimpleClientService {
    @Value("${server.port}")
    private String port;

    @Override
    public String simpleMethod() {
        try{
            Thread.sleep(2000);
            return "这是部署在端口：" + port + "的服务";
        }catch (Exception e){
            return "fault";
        }
    }
}
