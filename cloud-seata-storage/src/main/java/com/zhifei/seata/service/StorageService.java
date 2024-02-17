package com.zhifei.seata.service;

/**
 * @author He Zhifei
 * @date 2020/8/19 12:51
 */
public interface StorageService {
    void decrease(Long providerId);
}
