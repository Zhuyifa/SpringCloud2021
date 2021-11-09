package com.athuake.springcloud.dao;

import com.athuake.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    //写操作
    public Integer write(Payment payment);
    //读操作
    public Payment getPaymentById(@Param("id") Long id);

}
