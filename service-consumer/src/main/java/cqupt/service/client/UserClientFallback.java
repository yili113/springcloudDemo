package cqupt.service.client;

import cqupt.service.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @author yiLi
 * @create 2019-11-01 21:23
 */
@Component // 注入到spring容器
public class UserClientFallback implements UserClient {
    @Override
    public User queryUserById(Long id) {
        User user = new User();
        user.setName("服务器繁忙");
        return user;
    }
}
