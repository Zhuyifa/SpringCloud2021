package com.athuake.springcloud.alibaba.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.athuake.springcloud.alibaba.myhandler.CustomerBlockHandler;
import com.athuake.springcloud.entities.CommonResult;
import com.athuake.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handlerException",fallback = "byResourceFallback")
    public CommonResult byResource(){
        int age=10/0;
        return new CommonResult(200,"按资源名称限流测试ok",new Payment(2020L,"serial"));
    }
    public CommonResult handlerException(BlockException exception){
        return new CommonResult(444, exception.getClass().getCanonicalName()+"\t服务不可用,blockHandler");
    }
    public CommonResult byResourceFallback(BlockException exception){
        return new CommonResult(444, exception.getClass().getCanonicalName()+"\t服务不可用,fallback");
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"按URL限流测试ok",new Payment(2020L,"serial"));
    }

    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handlerException2")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200, "按客戶自定义", new Payment(2020L, "serial003"));    }
}
