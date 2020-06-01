package com.fzn.pesystem.user.service;

import com.fzn.pesystem.common.service.IUserClientService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "SERVICE-PROVIDER")
public interface IUserService extends IUserClientService {
}
