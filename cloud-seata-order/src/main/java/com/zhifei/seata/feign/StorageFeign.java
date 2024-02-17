package com.zhifei.seata.feign;

import com.zhifei.cloud.constant.ServiceNames;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author He Zhifei
 * @date 2020/8/19 10:35
 */
@RequestMapping("/storage")
@FeignClient(ServiceNames.CLOUD_SEATA_STORAGE)
public interface StorageFeign {

    @PatchMapping("/decrease/{providerId}")
    public void decrease(@PathVariable("providerId") Long providerId);
}
