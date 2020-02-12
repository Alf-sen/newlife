package zxs.up.newlife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zxs.up.newlife.dto.PageDTO;
import zxs.up.newlife.mapper.UserMapper;
import zxs.up.newlife.service.QuestionService;

/**
 * @auther ZhangXiusen
 * @date 2020/01/19 8:58
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(@RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "5") Integer size,
                        Model model) {

        PageDTO pignation = questionService.getPageDTO(page, size);
        model.addAttribute("pignation", pignation);
        return "index";
    }
}
