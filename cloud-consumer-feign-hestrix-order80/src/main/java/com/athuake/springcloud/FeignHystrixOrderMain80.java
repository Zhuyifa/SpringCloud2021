package com.athuake.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix  //激活hystrix
public class FeignHystrixOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(FeignHystrixOrderMain80.class,args);
    }
}
