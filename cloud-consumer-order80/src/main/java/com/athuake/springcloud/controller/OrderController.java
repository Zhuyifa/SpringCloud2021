package com.athuake.springcloud.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.annotation.Resource;
import com.athuake.springcloud.entities.CommonResult;
import com.athuake.springcloud.entities.Payment;

@RestController
@Slf4j
@EnableEurekaClient
public class OrderController {
//    public static final String PAYMENT_RUL="http://localhost:8001";
    public static final String PAYMENT_RUL="http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/payment/write")
    public CommonResult<Payment> write(@RequestBody Payment payment){
        return restTemplate.postForObject(PAYMENT_RUL+"/payment/write",payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById (@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_RUL+"/payment/get/"+id,CommonResult.class);
    }

    //getForEntity 返回的是ResponseEntity对象，其中包含了响应头，响应码等。
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult getPaymentById2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_RUL + "/payment/get/" + id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else {
            return new CommonResult(444,"操作失败");
        }
    }
}
