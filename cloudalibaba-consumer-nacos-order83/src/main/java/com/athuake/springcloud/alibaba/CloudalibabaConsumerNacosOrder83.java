package com.athuake.springcloud.alibaba;


import com.athuake.springcloud.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name = "nacos-payment-provider",configuration = MySelfRule.class)
public class CloudalibabaConsumerNacosOrder83 {
    public static void main(String[] args) {
        SpringApplication.run(CloudalibabaConsumerNacosOrder83.class,args);
    }
}
