package com.fzn.pesystem.user.service;

import com.fzn.pesystem.common.service.ISimpleClientService;
import com.fzn.pesystem.user.service.fallback.SimpleMethodFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "SERVICE-PROVIDER", contextId = "simpleService", fallbackFactory = SimpleMethodFallBackFactory.class)
public interface ISimpleService extends ISimpleClientService {
}
