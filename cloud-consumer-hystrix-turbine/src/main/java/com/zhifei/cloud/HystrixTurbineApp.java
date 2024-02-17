package com.zhifei.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author He Zhifei
 * @date 2020/7/13 19:49
 */
@EnableEurekaClient
@EnableTurbine
@SpringBootApplication
public class HystrixTurbineApp {
    public static void main(String[] args) {
        SpringApplication.run(HystrixTurbineApp.class, args);
    }
}
