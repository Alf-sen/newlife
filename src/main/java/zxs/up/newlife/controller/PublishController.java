package zxs.up.newlife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @auther ZhangXiusen
 * @date 2020/01/29 10:27
 */
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish() {

        return "publish";
    }
}
