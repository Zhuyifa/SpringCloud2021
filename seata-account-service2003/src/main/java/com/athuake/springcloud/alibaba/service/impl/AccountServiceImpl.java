package com.athuake.springcloud.alibaba.service.impl;

import com.athuake.springcloud.alibaba.dao.AccountDao;
import com.athuake.springcloud.alibaba.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("用户账户减额开始......");
        accountDao.decrease(userId, money);
        log.info("用户账户减额完成。");
    }
}
