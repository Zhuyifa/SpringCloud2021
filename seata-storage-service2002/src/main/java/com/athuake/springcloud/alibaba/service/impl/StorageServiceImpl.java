package com.athuake.springcloud.alibaba.service.impl;


import com.athuake.springcloud.alibaba.dao.StorageDao;
import com.athuake.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageDao storageDao;
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("库存做减库开始......");
        storageDao.decrease(productId, count);
        log.info("库存做减库结束。");
    }
}
