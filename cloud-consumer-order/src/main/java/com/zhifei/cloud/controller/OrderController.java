package com.zhifei.cloud.controller;

import com.zhifei.cloud.OrderApp;
import com.zhifei.cloud.conf.exception.entity.R;
import com.zhifei.cloud.constant.ServiceNames;
import com.zhifei.cloud.entity.Payment;
import com.zhifei.cloud.loadbalancer.RootLoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.Min;

/**
 * @author He Zhifei
 * @date 2020/7/7 14:10
 */
@Validated
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RootLoadBalancer balancer;

    @PostMapping("/payment")
    public R addPayment(@RequestBody @Validated Payment payment) {
        return restTemplate.postForObject(ServiceNames.HTTP_PREFIX + ServiceNames.CLOUD_PROVIDER_PAYMENT + "/payment",
                payment, R.class);
    }

    @GetMapping("/payment/{id:[0-9]+}")
    public R getPayment(@PathVariable @Min(value = 1, message = "id最小为1") Long id) {
        R forObject = restTemplate.getForObject(ServiceNames.HTTP_PREFIX + ServiceNames.CLOUD_PROVIDER_PAYMENT + "/payment/" + id,
                R.class);
        return forObject;
    }

    /**
     * 测试这个时，需要把{@link OrderApp#restTemplate()}上的@LoadBalanced注解注释掉
     * @param id
     * @return
     */
    @GetMapping("/payment4testBalancer/{id:[0-9]+}")
    public R getPayment4testBalancer(@PathVariable @Min(value = 1, message = "id最小为1") Long id) {
        ServiceInstance instance = balancer.instance(ServiceNames.CLOUD_PROVIDER_PAYMENT);
        R result = restTemplate.getForObject(instance.getUri() + "/payment/" + id, R.class);
        return result;
    }

    @GetMapping("/testGateway")
    public R<String> testGateway() {
        return R.success().data("########consumer order testGateway########");
    }
}
