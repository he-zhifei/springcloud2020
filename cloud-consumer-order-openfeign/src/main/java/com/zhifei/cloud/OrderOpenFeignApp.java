package com.zhifei.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author He Zhifei
 * @date 2020/7/11 21:49
 */
@EnableFeignClients
@SpringBootApplication
public class OrderOpenFeignApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeignApp.class, args);
    }
}
