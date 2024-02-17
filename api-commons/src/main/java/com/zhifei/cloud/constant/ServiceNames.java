package com.zhifei.cloud.constant;

/**
 * 微服务服务名称，方便通过服务名称去调用服务
 *
 * @author He Zhifei
 * @date 2023/3/30 11:06
 */
public interface ServiceNames {

    String HTTP_PREFIX = "http://";

    String CLOUD_PROVIDER_PAYMENT = "cloud-provider-payment";

    String CLOUD_PROVIDER_PAYMENT_ZOOKEEPER = "cloud-provider-payment-zookeeper";

    String CLOUD_PROVIDER_PAYMENT_CONSUL = "cloud-provider-payment-consul";

    String CLOUD_PROVIDER_PAYMENT_EUREKA_HYSTRIX = "cloud-provider-payment-eureka-hystrix";

    String CLOUD_PROVIDER_PAYMENT_NACOS = "cloud-provider-payment-nacos";

    String CLOUD_SEATA_STORAGE = "cloud-seata-storage";
}
