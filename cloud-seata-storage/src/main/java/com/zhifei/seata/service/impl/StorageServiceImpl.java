package com.zhifei.seata.service.impl;

import com.zhifei.seata.dao.StorageMapper;
import com.zhifei.seata.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author He Zhifei
 * @date 2020/8/19 12:52
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    @Override
    public void decrease(Long providerId) {
        storageMapper.decrease(providerId);
    }
}
