package com.zhifei.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author He Zhifei
 * @date 2020/7/8 15:20
 */
@EnableDiscoveryClient
@SpringBootApplication
public class PaymentZKApp {
    public static void main(String[] args) {
        SpringApplication.run(PaymentZKApp.class, args);
    }
}
