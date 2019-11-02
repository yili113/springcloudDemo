package cqupt.service.service;

import cqupt.service.mapper.UserMapper;
import cqupt.service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yiLi
 * @create 2019-10-29 22:05
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryUserById(Long id) {

        return this.userMapper.selectByPrimaryKey(id);
    }
}
