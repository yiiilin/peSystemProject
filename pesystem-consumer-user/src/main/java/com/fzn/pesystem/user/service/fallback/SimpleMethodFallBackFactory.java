package com.fzn.pesystem.user.service.fallback;

import com.fzn.pesystem.user.service.ISimpleService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleMethodFallBackFactory implements FallbackFactory<ISimpleService> {
    @Override
    public ISimpleService create(Throwable throwable) {
        return new ISimpleService() {
            @Override
            public String simpleMethod() {
                return "服务熔断";
            }
        };
    }
}
