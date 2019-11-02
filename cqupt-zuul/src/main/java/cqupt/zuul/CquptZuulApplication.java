package cqupt.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy// 开启网关功能
@EnableDiscoveryClient
public class CquptZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(CquptZuulApplication.class, args);
    }

}
