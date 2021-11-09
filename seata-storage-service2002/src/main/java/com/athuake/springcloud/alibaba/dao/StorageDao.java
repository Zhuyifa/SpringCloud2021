package com.athuake.springcloud.alibaba.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {
    //库存做减库操作
   void decrease(@Param("productId") Long productId,@Param("count") Integer count);
}
