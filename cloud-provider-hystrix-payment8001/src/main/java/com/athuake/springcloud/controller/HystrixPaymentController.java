package com.athuake.springcloud.controller;


import com.athuake.springcloud.service.HystrixPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class HystrixPaymentController {

    @Resource
    private HystrixPaymentService service;
    @GetMapping(value = "/payment/Hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id")  Integer id){
        String result = service.paymentInfoOk(id);
        log.info("****result:"+result);
        return result;
    }

    @GetMapping(value = "/payment/Hystrix/TimeOut/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id")  Integer id){
        String result = service.paymentInfoTimeOut(id);
        log.info("*****result:"+result);
        return result;
    }

    @GetMapping(value = "/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = service.paymentCircuitBreaker(id);
        log.info("*****result:"+result);
        return result;
    }
}
