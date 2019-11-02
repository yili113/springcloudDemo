package cqupt.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient// 开启eureka客户端
@EnableCircuitBreaker // 开启熔断  肯定是在消费端开启 提供方根本就不知道 1.请求超时 2.线程池已满
@EnableFeignClients // 开启feign客户端
public class ServiceConsumerApplication {

    @Bean
    @LoadBalanced // 启动负载均衡
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }

    public static void main(String[] args) {

        SpringApplication.run(ServiceConsumerApplication.class, args);
    }

}
