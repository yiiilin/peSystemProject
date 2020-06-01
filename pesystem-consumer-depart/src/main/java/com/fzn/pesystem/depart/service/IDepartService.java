package com.fzn.pesystem.depart.service;

import com.fzn.pesystem.common.service.IDepartClientService;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "SERVICE-PROVIDER")
public interface IDepartService extends IDepartClientService {

}
