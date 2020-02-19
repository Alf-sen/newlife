package zxs.up.newlife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zxs.up.newlife.dto.CommentDTO;
import zxs.up.newlife.dto.ResultDTO;
import zxs.up.newlife.enums.CustomizeCodeEnum;
import zxs.up.newlife.exception.CustomizeException;
import zxs.up.newlife.mapper.QuestionMapper;
import zxs.up.newlife.model.Comment;
import zxs.up.newlife.model.User;
import zxs.up.newlife.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther ZhangXiusen
 * @date 2020/02/14 17:45
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionMapper questionMapper;

    @ResponseBody
    @RequestMapping("/comment")
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.resultOf(CustomizeCodeEnum.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(0);
        comment.setLikeCount(0L);

        commentService.insert(comment);

        return ResultDTO.resultOf(CustomizeCodeEnum.COMMENT_SUCCESS);
    }
}
