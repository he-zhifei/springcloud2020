package com.zhifei.cloud.controller;

import com.zhifei.cloud.conf.exception.entity.R;
import com.zhifei.cloud.feign.PaymentFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author He Zhifei
 * @date 2020/7/13 14:45
 */
//@DefaultProperties(defaultFallback = "globalFallbackMethod")
@RestController
@RequestMapping("/orderHystrix")
public class OrderHystrixController {

    @Resource
    private PaymentFeignClient paymentFeignClient;

    /**
     * 有指定fallbackMethod的就走自定义的降级方法处理，没有的话，就走默认的，
     * 在类上配置了DefaultProperties(defaultFallback = "globalFallbackMethod")
     * 避免每个方法都要手动写一个降级方法处理（代码膨胀）
     * 但是仍然存在业务代码与逻辑代码混乱的问题，使用@FeignClient的fallback解决
     * @param id
     * @return
     */
//    @HystrixCommand(fallbackMethod = "testHystrixHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
//    @HystrixCommand
    @GetMapping("/testHystrix/{id}")
    public R<String> testHystrix(@PathVariable("id") Integer id) {
//        try {
//            TimeUnit.MILLISECONDS.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int i = 1/0;
        return paymentFeignClient.ok(id);
    }

    public R<String> testHystrixHandler(Integer id) {
        return R.success().data("执行了消费端的testHystrixHandler，线程为：" + Thread.currentThread().getName());
    }

    public R<String> globalFallbackMethod() {
        return R.success().data("globalFallbackMethod!!!");
    }

    @GetMapping("/testFeignFB/{id}")
    public R<String> testFeignFB(@PathVariable("id") Integer id) {
        return paymentFeignClient.ok(id);
    }
}
