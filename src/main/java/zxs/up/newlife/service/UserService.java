package zxs.up.newlife.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zxs.up.newlife.dto.GithubUser;
import zxs.up.newlife.mapper.UserMapper;
import zxs.up.newlife.model.User;
import zxs.up.newlife.model.UserExample;

import java.util.List;
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
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> dbUserList = userMapper.selectByExample(userExample);
        if (dbUserList.size() == 0) {
            //新增
            userMapper.insert(user);
        } else {
            User updateUser = new User();
            updateUser.setName(user.getName());
            updateUser.setGmtModified(user.getGmtModified());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(dbUserList.get(0).getId());
            userMapper.updateByExampleSelective(updateUser, userExample);
        }
    }

    public User findByToken(String token) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andTokenEqualTo(token);
        User user = userMapper.selectByExample(userExample).get(0);

        return user;
    }
}
