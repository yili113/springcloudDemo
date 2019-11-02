package cqupt.service.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import cqupt.service.client.UserClient;
import cqupt.service.pojo.User;
import javafx.beans.DefaultProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author yiLi
 * @create 2019-10-30 21:08
 */
@Controller
@RequestMapping("consumer/user")
public class UserController {

    @Autowired
    private UserClient userClient;// 注入实现feign熔断的接口

    @GetMapping
    @ResponseBody
    public String queryUserById(@RequestParam("id") Long id){
        User user = this.userClient.queryUserById(id);
        return user.toString();
    }

}
