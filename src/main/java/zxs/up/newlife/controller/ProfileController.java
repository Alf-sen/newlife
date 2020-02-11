package zxs.up.newlife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import zxs.up.newlife.dto.PageDTO;
import zxs.up.newlife.model.User;
import zxs.up.newlife.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther ZhangXiusen
 * @date 2020/02/06 15:49
 */
@Controller
public class ProfileController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "size", defaultValue = "5") Integer size,
                          @PathVariable(name = "action") String action,
                          HttpServletRequest request,
                          Model model) {

        User user = (User)request.getSession().getAttribute("user");

        if (user == null) {
            return "index";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        PageDTO pignation = questionService.getUserPageDTO(user.getId(), page, size);
        model.addAttribute("pignation", pignation);
        return "profile";
    }
}
