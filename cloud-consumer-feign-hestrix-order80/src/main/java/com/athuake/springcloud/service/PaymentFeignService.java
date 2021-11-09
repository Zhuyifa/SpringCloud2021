package com.athuake.springcloud.service;


import com.athuake.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentFeignService {
    @GetMapping(value = "/payment/Hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id")  Integer id);

    @GetMapping(value = "/payment/Hystrix/TimeOut/{id}")
    public String paymentInfoTimeOut(@PathVariable("id")  Integer id);
}
