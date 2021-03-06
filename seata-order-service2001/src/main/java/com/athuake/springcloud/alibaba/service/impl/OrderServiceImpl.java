package com.athuake.springcloud.alibaba.service.impl;


import com.athuake.springcloud.alibaba.dao.OrderDao;
import com.athuake.springcloud.alibaba.domain.Order;
import com.athuake.springcloud.alibaba.service.AccountService;
import com.athuake.springcloud.alibaba.service.OrderService;
import com.athuake.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;  //订单
    @Resource
    private AccountService accountService;  //账户
    @Resource
    private StorageService storageService;  //库存

    @Override
//    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)//发生了任何异常都回滚
    public void create(Order order) {
        log.info("开始创建订单......");
        orderDao.create(order);
        log.info("订单微服务调用库存,库存做扣减......");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("库存做扣减完成！");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("订单微服务调用用户,用户账户金额做扣减......");
        log.info("用户账户金额扣减完成！");
        log.info("正在修改订单状态......");
        orderDao.update(order.getUserId(),order.getStatus());
        log.info("订单状态修改完成！");
        log.info("订单创建成功！");

    }
}
