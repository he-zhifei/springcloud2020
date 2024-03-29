package com.zhifei.cloud.controller;

import com.zhifei.cloud.constant.ServiceNames;
import com.zhifei.cloud.feign.NacosProviderPaymentControllerFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 测试服务注册发现、配置中心
 */
@RefreshScope       // 支持动态刷新
@RestController
@RequestMapping("/order")
public class OrderController {

    @Value("${config.info: default-info}")
    private String info;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NacosProviderPaymentControllerFeign nacosProviderPaymentControllerFeign;

    /**
     * 测试通过ribbon进行微服务通信
     * @return
     */
    @GetMapping("/echo")
    public String echo() {
        return restTemplate.getForObject(ServiceNames.HTTP_PREFIX + ServiceNames.CLOUD_PROVIDER_PAYMENT_NACOS + "/payment/test",
                String.class);
    }

    /**
     * 测试通过openfeign进行微服务通信
     * @return
     */
    @GetMapping("/testFeign")
    public String testFeign() {
        return nacosProviderPaymentControllerFeign.test();
    }

    @GetMapping("/info")
    public String info() {
        return info;
    }
}
