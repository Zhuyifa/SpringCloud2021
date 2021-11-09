package com.athuake.springcloud.service;

import org.apache.ibatis.annotations.Param;

import com.athuake.springcloud.entities.Payment;

public interface PaymentService {
    public Integer write(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
