package com.zhifei.cloud.controller;

import com.zhifei.cloud.conf.exception.entity.R;
import com.zhifei.cloud.constant.ServiceNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author He Zhifei
 * @date 2020/7/8 18:55
 */
@RestController
@RequestMapping("/orderConsul")
public class OrderConsulController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public R test() {
        return restTemplate.getForObject(ServiceNames.HTTP_PREFIX + ServiceNames.CLOUD_PROVIDER_PAYMENT_CONSUL + "/paymentConsul/test", R.class);
    }
}
