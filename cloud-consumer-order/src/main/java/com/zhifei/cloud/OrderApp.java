package com.zhifei.cloud;

import com.zhifei.cloud.constant.ServiceNames;
import com.zhifei.ribbonRule.CusRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author He Zhifei
 * @date 2020/7/7 14:07
 */
@RibbonClient(name = ServiceNames.CLOUD_PROVIDER_PAYMENT, configuration = CusRuleConfig.class)
@EnableEurekaClient
@SpringBootApplication
public class OrderApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }

    /**
     * @LoadBalanced 开启ribbon实现的负载均衡，此时，用RestTemplate只能通过服务名称去访问，不能通过ip地址去访问（因为ribbon内部会做负载均衡，然后再访问）
     * 但是，在使用自定义负载均衡策略RoundRobinLoadBalancer时，要注释这个注解，因为RootLoadBalancer.instance()获取到的已经是某一个微服务的实例了，
     * 只能通过这个微服务实例的url去请求对应的实例，获取响应数据。如果不注释这个注解的话，ribbon会把这个uri当做服务名称，再去eureka server中查找，结果显然是
     * 不存在的。
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
