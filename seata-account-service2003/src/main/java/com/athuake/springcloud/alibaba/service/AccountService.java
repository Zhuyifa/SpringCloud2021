package com.athuake.springcloud.alibaba.service;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface AccountService {
    //用户账单做减额
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
