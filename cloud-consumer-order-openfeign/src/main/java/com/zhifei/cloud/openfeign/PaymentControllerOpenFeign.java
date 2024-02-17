package com.zhifei.cloud.openfeign;

import com.zhifei.cloud.conf.exception.entity.R;
import com.zhifei.cloud.constant.ServiceNames;
import com.zhifei.cloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * <!--要添加这个依赖，不然@PathVariable的请求会自动把请求转为POST-->
 * <dependency>
 *     <groupId>io.github.openfeign</groupId>
 *     <artifactId>feign-httpclient</artifactId>
 *     <version>10.2.3</version>
 * </dependency>
 *
 * @author He Zhifei
 * @date 2020/7/11 21:51
 */
@RequestMapping("/payment")
@FeignClient(ServiceNames.CLOUD_PROVIDER_PAYMENT)
public interface PaymentControllerOpenFeign {

    @GetMapping("/{id:[0-9]+}")
    R<Payment> getById(@PathVariable Long id);

    @PostMapping
    R<Integer> add(@RequestBody @Validated Payment payment);

    @GetMapping("/testTimeout")
    R<String> testTimeout();
}
