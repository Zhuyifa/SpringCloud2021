package com.athuake.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.athuake.springcloud.alibaba.dao"})
public class MyBatisConfig {
}