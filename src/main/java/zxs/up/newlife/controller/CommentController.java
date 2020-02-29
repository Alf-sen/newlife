package zxs.up.newlife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zxs.up.newlife.dto.CommentCreatDTO;
import zxs.up.newlife.dto.CommentDTO;
import zxs.up.newlife.dto.ResultDTO;
import zxs.up.newlife.enums.CommentTypeEnum;
import zxs.up.newlife.enums.CustomizeCodeEnum;
import zxs.up.newlife.mapper.CommentExcMapper;
import zxs.up.newlife.mapper.QuestionMapper;
import zxs.up.newlife.model.Comment;
import zxs.up.newlife.model.User;
import zxs.up.newlife.service.CommentService;
import zxs.up.newlife.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @auther ZhangXiusen
 * @date 2020/02/14 17:45
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentExcMapper commentExcMapper;

    @Autowired
    private QuestionService questionService;

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

        if (commentCreatDTO == null || commentCreatDTO.getContent() == null) {
            return ResultDTO.resultOf(CustomizeCodeEnum.COMMENT_IS_NULL);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreatDTO.getParentId());
        comment.setContent(commentCreatDTO.getContent());
        comment.setType(commentCreatDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0);
        comment.setCommentCount(0);

        commentService.insert(comment);

        return ResultDTO.resultOf(CustomizeCodeEnum.COMMENT_SUCCESS);
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public Object post(@PathVariable(name = "id") Integer id) {
        List<CommentDTO> commentDTOS = questionService.getComment(id, CommentTypeEnum.COMMENT);

        return ResultDTO.resultOf(commentDTOS);
    }

    @ResponseBody
    @RequestMapping(value = "/addLike/{id}", method = RequestMethod.GET)
    public Object addLikeCount(@PathVariable(name = "id") Integer id) {

        List<CommentDTO> dbCommentDTOS = questionService.getComment(id);
        if (dbCommentDTOS == null || dbCommentDTOS.size() == 0) {
            return ResultDTO.resultOf(CustomizeCodeEnum.QUESTION_NOT_FOUND);
        }
        commentExcMapper.addLikeCount(id);
        //修改likeCount后重新取值
        List<CommentDTO> commentDTOS = questionService.getComment(id);
        return ResultDTO.resultOf(commentDTOS);
    }

    @ResponseBody
    @RequestMapping(value = "/lessLike/{id}", method = RequestMethod.GET)
    public Object lessLikeCount(@PathVariable(name = "id") Integer id) {

        List<CommentDTO> dbCommentDTOS = questionService.getComment(id);
        if (dbCommentDTOS == null || dbCommentDTOS.size() == 0) {
            return ResultDTO.resultOf(CustomizeCodeEnum.QUESTION_NOT_FOUND);
        }
        commentExcMapper.lessLikeCount(id);
        //修改likeCount后重新取值
        List<CommentDTO> commentDTOS = questionService.getComment(id);
        return ResultDTO.resultOf(commentDTOS);
    }
}
