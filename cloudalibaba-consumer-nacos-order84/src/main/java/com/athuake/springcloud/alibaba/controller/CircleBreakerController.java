package com.athuake.springcloud.alibaba.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.athuake.springcloud.alibaba.service.PaymentFallbackService;
import com.athuake.springcloud.alibaba.service.PaymentService;
import com.athuake.springcloud.entities.CommonResult;
import com.athuake.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {

    @Resource
    private PaymentService service;

    @GetMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback") //什么都没有配置 ，只让sentinel监测到消费者
//    @SentinelResource(value = "fallback",fallback = "fallbackHandel") //配置了运行时异常，进入自己写的fallback兜底方法。
//    @SentinelResource(value = "fallback",blockHandler = "blockHandler") //当违反了sentinel的配置，弹出自己写的blockhandler
    @SentinelResource(value = "fallback",fallback = "fallbackHandel",blockHandler = "blockHandler")

    /*
    总结：
        fallback：java程序运行异常，fallback方法兜底，不让出现error page页面
        blockHandler： sentinel控制台 违规配置
        现实项目中，两者搭配使用,java代码如果runtime期间运行异常，弹出fallback的友好提示从而代替error page
        如果异常数或者异常比例高于sentinel配置阈值，弹出blockHandler 的方法。
     */
    public CommonResult fallback(@PathVariable("id") Long id) {
        CommonResult<Payment> result = service.paymentSQL(1L);
        if (id==4){
            throw new IllegalArgumentException("IllegalArgumentException 非法参数异常");
        }else if (result.getData()==null){
            throw new NullPointerException("NullPointerException 空指针异常");
        }
        return result;
    }
    public CommonResult fallbackHandel(@PathVariable("id") Long id,Throwable e) {
        return new CommonResult(444,"兜底异常fallbackHandel，异常内容"+e.getMessage(),null);
    }
    public CommonResult blockHandler(@PathVariable Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
    return new CommonResult<>(445, "blockHandler-sentinel限流,无此流水: blockException  " + blockException.getMessage(), payment);
}

}
