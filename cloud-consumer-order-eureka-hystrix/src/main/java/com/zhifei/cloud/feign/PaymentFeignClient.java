package com.zhifei.cloud.feign;

import com.zhifei.cloud.conf.exception.entity.R;
import com.zhifei.cloud.config.FeignConfig;
import com.zhifei.cloud.constant.ServiceNames;
import com.zhifei.cloud.fallback.PaymentFeignClientFB;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * 这里使用FeignConfiguration.class作为FeignClient配置的原因：在FeignClient类上
 * 使用了@RequestMapping注解，在SpringMVC中，会加载使用了@Controller和@RequestMapping的类，
 * 而Feign也会加载使用了@FeignClient的类，这里SpringMVC和Feign加载FeignClient产生了冲突
 *
 * @author He Zhifei
 * @date 2020/7/13 14:46
 */
@RequestMapping("/paymentHystrix")
@FeignClient(value = ServiceNames.CLOUD_PROVIDER_PAYMENT_EUREKA_HYSTRIX, fallback = PaymentFeignClientFB.class, configuration = FeignConfig.class)
public interface PaymentFeignClient {

    @GetMapping("/ok/{id}")
    R<String> ok(@PathVariable("id") Integer id);
}
