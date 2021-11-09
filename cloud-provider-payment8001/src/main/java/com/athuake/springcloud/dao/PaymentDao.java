package com.athuake.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.athuake.springcloud.entities.Payment;

@Mapper
public interface PaymentDao {
    //写操作
    public Integer write(Payment payment);
    //读操作
    public Payment getPaymentById(@Param("id") Long id);

}
