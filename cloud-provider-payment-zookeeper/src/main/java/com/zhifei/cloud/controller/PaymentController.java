package com.zhifei.cloud.controller;

import com.zhifei.cloud.conf.exception.entity.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author He Zhifei
 * @date 2020/7/7 12:15
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/test")
    public R test() {
        return R.success().data(port + " " + UUID.randomUUID().toString());
    }
}
