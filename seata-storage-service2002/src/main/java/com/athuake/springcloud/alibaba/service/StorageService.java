package com.athuake.springcloud.alibaba.service;

import org.apache.ibatis.annotations.Param;

public interface StorageService {
    //库存做减库操作
    void decrease(Long productId,Integer count);
}
