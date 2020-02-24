package zxs.up.newlife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zxs.up.newlife.dto.CommentCreatDTO;
import zxs.up.newlife.dto.ResultDTO;
import zxs.up.newlife.enums.CustomizeCodeEnum;
import zxs.up.newlife.mapper.QuestionMapper;
import zxs.up.newlife.model.Comment;
import zxs.up.newlife.model.User;
import zxs.up.newlife.service.CommentService;

import javax.servlet.http.HttpServletRequest;

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
    public Object post(@RequestBody CommentCreatDTO commentCreatDTO,
                       HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.resultOf(CustomizeCodeEnum.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreatDTO.getParentId());
        comment.setContent(commentCreatDTO.getContent());
        comment.setType(commentCreatDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);

        commentService.insert(comment);

        return ResultDTO.resultOf(CustomizeCodeEnum.COMMENT_SUCCESS);
    }
}
