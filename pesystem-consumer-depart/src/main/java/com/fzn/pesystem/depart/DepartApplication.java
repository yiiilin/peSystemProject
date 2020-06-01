package com.fzn.pesystem.depart;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.fzn.pesystem.depart"})
public class DepartApplication {
    public static void main(String[] args) {
        SpringApplication.run(DepartApplication.class,args);
    }
}
