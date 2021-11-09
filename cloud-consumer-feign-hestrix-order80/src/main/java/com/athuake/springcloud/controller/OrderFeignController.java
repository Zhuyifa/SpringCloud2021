package com.athuake.springcloud.controller;


import com.athuake.springcloud.entities.CommonResult;
import com.athuake.springcloud.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderFeignController {
    @Resource
    private PaymentFeignService service;

    @GetMapping(value = "/consumer/payment/Hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id")  Integer id){
        String result = service.paymentInfoOk(id);
        log.info("****result:"+result);
        return result;
    }

//    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutFallBack",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
//    })
    //没有指定的fallback就用上面全局的服务降级
    @HystrixCommand
    @GetMapping(value = "/consumer/payment/Hystrix/TimeOut/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id")  Integer id){
//        int age=10/0;
        String result = service.paymentInfoTimeOut(id);
        log.info("*****result:"+result);
        return result;
    }
    public String paymentInfoTimeOutFallBack(@PathVariable("id")  Integer id){
        return "我是消费者8101，对方支付系统繁忙，请10秒钟之后再试，或者自己运行出错请检查自己,o(╥﹏╥)o";
    }


    //下面是全局fallback 方法
    public String payment_Global_FallbackMethod() {
        return "Global异常信息处理,请稍后再试。o(╥﹏╥)o";
    }

}
