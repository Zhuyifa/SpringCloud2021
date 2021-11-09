package com.athuake.springcloud.service.impl;

import com.athuake.springcloud.dao.PaymentDao;
import com.athuake.springcloud.entities.Payment;
import com.athuake.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;


    @Override
    public Integer write(Payment payment) {
        return paymentDao.write(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
