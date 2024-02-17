package com.zhifei.cloud.controller;

import com.zhifei.cloud.conf.exception.entity.R;
import com.zhifei.cloud.entity.Payment;
import com.zhifei.cloud.openfeign.PaymentControllerOpenFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author He Zhifei
 * @date 2020/7/11 21:53
 */
@RestController
@RequestMapping("/consumePayment")
public class ConsumePaymentController {

    @Autowired
    private PaymentControllerOpenFeign paymentControllerOpenFeign;

    @GetMapping("/{id:[0-9]+}")
    public R<Payment> getById(@PathVariable Long id) {
        return paymentControllerOpenFeign.getById(id);
    }

    @PostMapping
    public R<Integer> add(@RequestBody @Validated Payment payment) {
        return paymentControllerOpenFeign.add(payment);
    }

    @GetMapping("/testTimeout")
    public R<String> testTimeout() {
        return paymentControllerOpenFeign.testTimeout();
    }
}
