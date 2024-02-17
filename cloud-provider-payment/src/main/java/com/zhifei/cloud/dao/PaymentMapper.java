package com.zhifei.cloud.dao;

import com.zhifei.cloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author He Zhifei
 * @date 2020/7/7 12:22
 */
@Mapper
public interface PaymentMapper {
    int add(Payment payment);
    Payment getById(@Param("id") Long id);
}
