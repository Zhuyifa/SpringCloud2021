package com.athuake.springcloud.alibaba.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {
    //用户账单做减额
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
