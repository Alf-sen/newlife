package zxs.up.newlife.intercept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import zxs.up.newlife.mapper.UserMapper;
import zxs.up.newlife.model.User;
import zxs.up.newlife.model.UserExample;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @auther ZhangXiusen
 * @date 2020/02/09 11:25
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    UserExample userExample = new UserExample();
                    userExample.createCriteria()
                            .andTokenEqualTo(token);
                    List<User> userList = userMapper.selectByExample(userExample);;
                    //登录成功，写cookie和session
                    if (userList.size() != 0) {
                        request.getSession().setAttribute("user", userList.get(0));
                    }
                    break;
                }
            }
        }
        return true;
    }
}
