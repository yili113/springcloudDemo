package cqupt.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cqupt.service.mapper")// 扫描包的路径

public class ServiceProviderApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServiceProviderApplication.class, args);
    }

}
