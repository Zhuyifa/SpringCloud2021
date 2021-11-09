package com.athuake.springcloud.service;

import com.athuake.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public Integer write(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
