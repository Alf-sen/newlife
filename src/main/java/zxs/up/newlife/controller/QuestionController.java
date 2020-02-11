package zxs.up.newlife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zxs.up.newlife.dto.QuestionDTO;
import zxs.up.newlife.service.QuestionService;

/**
 * @auther ZhangXiusen
 * @date 2020/02/10 19:22
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") String id,
                           Model model) {

        QuestionDTO question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question";
    }
}
