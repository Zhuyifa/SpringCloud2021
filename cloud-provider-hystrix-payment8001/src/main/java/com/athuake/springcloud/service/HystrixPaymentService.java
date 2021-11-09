package com.athuake.springcloud.service;



import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;


@Service
public class HystrixPaymentService {

    public String paymentInfoOk(Integer id) {
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_Ok,id"+id+"\t"+"O(∩_∩)O哈哈~";
    }
    /**
     * 超时访问，设置自身调用超时的峰值，峰值内正常运行，超过了峰值需要服务降级 自动调用fallbackMethod 指定的方法
     * 超时异常或者运行异常 都会进行服务降级
     *
     * 服务熔断
     */

    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") //峰值
    })
    public String paymentInfoTimeOut(Integer id) {
        int timeNumber=1;  //与峰值进行比较
//        int age=10/0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:"+Thread.currentThread().getName()+"paymentInfoTimeOut,id"+id+"\t"+"O(∩_∩)O哈哈~";
    }
    /**
     * paymentInfoTimeOut 方法失败后 自动调用此方法 实现服务降级 告知调用者 paymentInfoTimeOut 目前无法正常调用
     */
    public String paymentInfoTimeOutHandler(Integer id) {
        return "线程池:"+Thread.currentThread().getName()+"paymentInfoTimeOutHandler系统繁忙或者运行报错，请稍后再试,id:"+id+"\t"+"o(╥﹏╥)o";
    }

    /**
     服务熔断 超时、异常、都会触发熔断
     * 1、默认是最近10秒内收到不小于10个请求
     * 2、并且有60%是失败的
     * 3、就开启断路器
     * 4、开启后所有请求不再转发，降级逻辑自动切换为主逻辑，减小调用方的响应时间
     * 5、经过一段时间（时间窗口期，默认是5秒），断路器变为半开状态，会让其中一个请求进行转发。
     * 5.1、如果成功，断路器会关闭
     * 5.2、若失败，继续开启。重复4和5
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_Fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),/* 是否开启断路器*/
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")// 超时处理
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id<0){
            throw new RuntimeException("id不能为负数");
        }
        String simpleUUID = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功,流水号为："+simpleUUID;
    }
    public String paymentCircuitBreaker_Fallback(@PathVariable("id") Integer id){
        return "id不能为负数，请稍后再试。id:"+id;
    }
}
