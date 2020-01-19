package zxs.up.newlife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther ZhangXiusen
 * @date 2020/01/19 8:58
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}
