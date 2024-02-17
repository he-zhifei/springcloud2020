package com.zhifei.seata.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author He Zhifei
 * @date 2020/8/19 11:48
 */
@Configuration
@MapperScan(basePackages = "com.zhifei.seata.dao")
public class MybatisConfig {
}
