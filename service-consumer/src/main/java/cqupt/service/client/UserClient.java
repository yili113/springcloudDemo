package cqupt.service.client;

import cqupt.service.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yiLi
 * @create 2019-11-01 21:06
 */


@FeignClient(value = "service-provider", fallback = UserClientFallback.class)// 声明哪个服务的feign的接口
/*@RequestMapping("/user")  类上面加注解会报错*/
public interface UserClient {
    @GetMapping("user/{id}")
    User queryUserById(@RequestParam("id") Long id);
}
