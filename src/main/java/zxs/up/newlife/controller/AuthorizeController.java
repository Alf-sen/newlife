package zxs.up.newlife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zxs.up.newlife.dto.AccessTokenDTO;
import zxs.up.newlife.dto.GithubUser;
import zxs.up.newlife.provider.GithubProvider;

/**
 * @auther ZhangXiusen
 * @date 2020/01/19 20:51
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("390fafdbb045bff12f7d");
        accessTokenDTO.setClient_secret("c44c58bad561ee6619b8c45cfd540f39577547e4");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);

        return "index";
    }
}
