package com.fzn.pesystem.concase.service;

import com.fzn.pesystem.common.service.ICaseClientService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "SERVICE-PROVIDER")
public interface ICaseService extends ICaseClientService {
}
