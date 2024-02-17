package com.zhifei.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author He Zhifei
 * @date 2020/7/13 14:44
 */
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@SpringBootApplication
//@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = FeignClient.class))
public class OrderEurekaHystrixApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderEurekaHystrixApp.class, args);
    }
}
