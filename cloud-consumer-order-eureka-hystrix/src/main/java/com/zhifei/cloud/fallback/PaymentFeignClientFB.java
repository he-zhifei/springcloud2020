package com.zhifei.cloud.fallback;

import com.zhifei.cloud.conf.exception.entity.R;
import com.zhifei.cloud.feign.PaymentFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author He Zhifei
 * @date 2020/7/13 15:30
 */
@Component
public class PaymentFeignClientFB implements PaymentFeignClient {
    @Override
    public R<String> ok(Integer id) {
        return R.success().message("ok...fallback(对方服务器已宕机)");
    }
}
