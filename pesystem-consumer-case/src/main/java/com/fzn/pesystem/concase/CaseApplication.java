package com.fzn.pesystem.concase;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.fzn.pesystem.concase"})
public class CaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(CaseApplication.class,args);
    }
}
