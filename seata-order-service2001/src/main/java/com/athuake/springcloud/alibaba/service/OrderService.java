package com.athuake.springcloud.alibaba.service;

import com.athuake.springcloud.alibaba.domain.CommonResult;
import com.athuake.springcloud.alibaba.domain.Order;

public interface OrderService {
    //1 新建订单
    public void create(Order order);
}
