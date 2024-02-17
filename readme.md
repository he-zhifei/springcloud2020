```
api-commons		公共模块api
cloud-eureka-server		eureka服务注册中心（通过指定配置文件来启动集群）

cloud-consumer-order		模拟服务消费者（eureka），也包含了sleuth做服务链路
cloud-provider-payment		模拟服务生产者（eureka），通过指定配置文件来启动集群，也包含了sleuth做服务链路

cloud-consumer-order-zookeeper		模拟服务消费者（zk）
cloud-provider-payment-zookeeper	模拟服务生产者（zk）

cloud-consumer-order-consul		模拟服务消费者（consul）
cloud-provider-payment-consul	模拟服务生产者（consul）

cloud-consumer-order-openfeign		使用openfeign进行服务调用

cloud-provider-payment-eureka-hystrix		服务提供方自身使用hystrix进行服务降级（一般使用在消费方）
cloud-consumer-order-eureka-hystrix		服务消费方使用hystrix进行服务降级（使用@HystrixCommand（不推荐）或者整合OpenFeign的FeignClient的fallback属性使用（推荐）），注意@RequestMapping如果要用在FeignClient上的配置

cloud-consumer-hystrix-dashboard		hystrix仪表盘进行服务监控，本案例先访问http://localhost:9001/hystrix，再粘贴http://localhost:8001/actuator/hystrix.stream监控单个节点（或者8002节点）
cloud-consumer-hystrix-turbine		turbine收集eureka上的集群信息，通过dashboard展示出来，具体用法查看yml中的注释说明

cloud-gateway		网关，包括路由（route）、断言（predicate）、过滤器（filter）

config-server		config服务端
config-client		config客户端（手动刷新）

config-server-bus	config服务端整合服务总线
config-client-bus	config客户端整合服务总线，在服务端或客户端手动刷新一个配置，其它配置自动通过mq广播通知修改（一处修改处处修改，或者只修改指定的某几个）

cloud-seata-order	模拟消费者，整合seata处理分布式事务问题，在TM的service方法上加@GlobalTransactional
cloud-seata-storage	模拟生产者，整合seata处理分布式事务问题

cloud-consumer-order-nacos	使用nacos做注册中心+配置中心+sentinel（消费者）
cloud-provider-payment-nacos	使用nacos做注册中心+配置中心+sentinel（生产者）
```

---



1. 在idea上运行集群（通过指定不同配置文件的集群），需要在配置启动类中设置勾选allow parallel run。
2. 在微服务里一个service可以有1个或者多个instance（通过集群构建），就比如这里的eureka-server和payment分别有3个和2个instance，
要区分serviceId和instanceId，微服务之间，可以使用RestTemplate通过http://serviceId（不用加端口号，不是主机名，不要混淆）去访问微服务。
3. eureka客户端往注册中心进行注册服务，对外暴露serviceId，eureka服务端保留serviceId与真实的服务的地址的映射关系，
消费者使用serviceId进行服务消费的时候，去eureka服务端查找对应的实例地址，然后消费服务。其中，每隔30s客户端往服务端发送心跳包，
服务端没有接收到，则把改服务从服务注册表移除。但是，同一端时间，很多来自客户端的心跳包都没有收到，则启动自我保护模式，
会认为是因为网络等原因导致没有接受到心跳包，则会保留那些服务，不会立马移除，但是90s后还没有收到，则移除。属于CAP的AP，A高可用。
自我保护可以在 eureka service端通过配置进行关闭。
4. eureka与zookeeper对比：zk建立的节点是临时的，服务一旦宕机，里面移除节点，而eureka有自我保护机制，不会立马移除
5. eureka/zookeeper/consul对比：
（1）eureka、zookeeper使用java编写的，consul使用go语言
（2）服务健康检查：eureka配置可支持，zookeeper、consul支持
（3）均可集成SpringCloud使用
（4）eureka设计思想为AP，而zookeeper、consul为CP。
CAP: C为一致性（Consistency），A为可用性（Availability），P为分区容忍（Partition tolerance），C和A存在矛盾，C运行网络延迟但是结果一定要一致，A不允许超时，但要保证可用，运行返回旧数据或者假数据。P运行一个节点宕机后其他节点能继续正常使用。
6. hystrix：1.服务降级；2.服务熔断；3.服务限流。
