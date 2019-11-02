package cqupt.service.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author yiLi
 * @create 2019-10-30 21:08
 */
/*@Controller
@RequestMapping("consumer/user")*/
@DefaultProperties(defaultFallback = "fallbackMethod")// 定义全局的熔断方法
public class UserController1 {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;// 通过客户端获取服务列表

    @GetMapping
    @ResponseBody
    // @HystrixCommand(fallbackMethod = "")  // 声明熔断的方法   局部声明熔断方法
    @HystrixCommand
    public String queryUserById(@RequestParam("id") Long id) {
 /*        // return this.restTemplate.getForObject("http://localhost:8081/user/" + id, User.class);
        // 根据服务名称，获取服务实例。有可能是集群，所以是service实例集合
        List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
        // 因为只有一个Service-provider。所以获取第一个实例
        ServiceInstance instance = instances.get(0);
        // 获取ip和端口信息，拼接成服务地址
        String baseUrl = "http://" + instance.getHost() + ":" + instance.getPort() + "/user/" + id;
        User user = this.restTemplate.getForObject(baseUrl, User.class);
        return user;*/
        if (id == 1) {
            throw new RuntimeException("繁忙");
        }
        // 通过client获取服务提供方的服务列表，这里我们只有一个
        // ServiceInstance instance = discoveryClient.getInstances("service-provider").get(0);
        String baseUrl = "http://service-provider/user/" + id;
        String user = this.restTemplate.getForObject(baseUrl, String.class);
        return user;

    }

    public String fallbackMethod() {// 跟所有的熔断方法的返回值一样
        return "服务器正忙";
    }
}
