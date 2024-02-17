package com.zhifei.cloud.feign;

import com.zhifei.cloud.constant.ServiceNames;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(ServiceNames.CLOUD_PROVIDER_PAYMENT_NACOS)
@RequestMapping("/payment")
public interface NacosProviderPaymentControllerFeign {

    @GetMapping("/test")
    public String test();
}
