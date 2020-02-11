package zxs.up.newlife.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zxs.up.newlife.dto.GithubUser;
import zxs.up.newlife.mapper.UserMapper;
import zxs.up.newlife.model.User;

import java.util.UUID;

/**
 * @auther ZhangXiusen
 * @date 2020/02/10 21:38
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void updateUser(User user) {
        //获取数据库数据
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if (dbUser == null) {
            //新增
            userMapper.insert(user);
        } else {
            userMapper.update(user);
        }
    }

    public User findByToken(String token) {
        User user = userMapper.findByToken(token);

        return user;
    }
}
