package com.zhifei.cloud.service;

import com.zhifei.cloud.entity.Payment;

/**
 * @author He Zhifei
 * @date 2020/7/7 12:19
 */
public interface PaymentService {

    /**
     * 新增
     * @param payment
     * @return 大于0-成功，0-失败
     */
    int add(Payment payment);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    Payment getById(Long id);
}
