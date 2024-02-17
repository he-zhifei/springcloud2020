package com.zhifei.seata.dao;

import com.zhifei.seata.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author He Zhifei
 * @date 2020/8/19 10:25
 */
@Mapper
public interface OrderMapper {
    void add(Order order);
}
