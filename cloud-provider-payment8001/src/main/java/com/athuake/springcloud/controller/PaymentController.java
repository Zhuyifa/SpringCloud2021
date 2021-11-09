package com.athuake.springcloud.controller;

import com.athuake.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import com.athuake.springcloud.entities.CommonResult;
import com.athuake.springcloud.entities.Payment;

import java.util.List;


@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String serverPort;
    @PostMapping("/payment/write")
    public CommonResult write(@RequestBody Payment payment){
        Integer res = paymentService.write(payment);
        log.info("******插入结果:"+res);
        if (res>0){
            return new CommonResult(200,"插入成功!返回结果"+res+"\t服务端口",payment);
        }else {
            return new CommonResult(444,"插入失败！",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById (@PathVariable("id") Long id){
        Payment result = paymentService.getPaymentById(id);
        log.info("查询结果："+result);
        if (result!=null){
            return new CommonResult(200,"查询成功！返回结果："+result+"\t服务端口："+serverPort,result);
        }else {
            return new CommonResult(444,"没有对应记录，查询ID："+id,result);
        }
    }
    @GetMapping(value = "/payment/discovery")
    public Object DiscoveryClient () {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*****service:"+service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
}
