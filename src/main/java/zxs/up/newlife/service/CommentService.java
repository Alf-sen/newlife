package zxs.up.newlife.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zxs.up.newlife.enums.CommentTypeEnum;
import zxs.up.newlife.enums.CustomizeCodeEnum;
import zxs.up.newlife.exception.CustomizeException;
import zxs.up.newlife.mapper.CommentExcMapper;
import zxs.up.newlife.mapper.CommentMapper;
import zxs.up.newlife.mapper.QuestionExcMapper;
import zxs.up.newlife.mapper.QuestionMapper;
import zxs.up.newlife.model.Comment;
import zxs.up.newlife.model.Question;

/**
 * @auther ZhangXiusen
 * @date 2020/02/14 17:58
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentExcMapper commentExcMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExcMapper questionExcMapper;

    @Transactional
    public void insert(Comment comment) {
        //判断回复问题是否存在
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeCodeEnum.QUESTION_NOT_FOUND);
        }

        if (comment.getType() == null || !CommentTypeEnum.isExit(comment.getType())) {
            throw new CustomizeException(CustomizeCodeEnum.TYPE_PARAM_WRONG);
        }

        if (CommentTypeEnum.COMMENT.getType() == comment.getType()) {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null || dbComment.getId() == 0) {
                throw new CustomizeException(CustomizeCodeEnum.COMMENT_NOT_FOUND);
            }
            commentExcMapper.updateCommentCount(comment.getParentId());
        } else {
            //回复问题
            //判断回复问题是否存在
            Question dbQuestion = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (dbQuestion == null || dbQuestion.getId() == 0) {
                throw new CustomizeException(CustomizeCodeEnum.QUESTION_NOT_FOUND);
            }
        }

        commentMapper.insert(comment);
        questionExcMapper.updateCommentCount(comment.getParentId());
    }
}
