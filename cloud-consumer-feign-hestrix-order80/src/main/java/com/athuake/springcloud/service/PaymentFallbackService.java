package com.athuake.springcloud.service;


import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentFeignService {
    @Override
    public String paymentInfoOk(Integer id) {
        return "PaymentFallbackService---- fallback ----paymentInfoOk o(╥﹏╥)o";
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        return "PaymentFallbackService---- fallback ----paymentInfoTimeOut o(╥﹏╥)o";
    }
}
