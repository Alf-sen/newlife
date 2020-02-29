package zxs.up.newlife.service;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import zxs.up.newlife.dto.CommentDTO;
import zxs.up.newlife.dto.PageDTO;
import zxs.up.newlife.dto.QuestionDTO;
import zxs.up.newlife.enums.CommentTypeEnum;
import zxs.up.newlife.enums.CustomizeCodeEnum;
import zxs.up.newlife.exception.CustomizeException;
import zxs.up.newlife.mapper.CommentMapper;
import zxs.up.newlife.mapper.QuestionExcMapper;
import zxs.up.newlife.mapper.QuestionMapper;
import zxs.up.newlife.mapper.UserMapper;
import zxs.up.newlife.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @auther ZhangXiusen
 * @date 2020/02/01 21:18
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExcMapper questionExcMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    public PageDTO getPageDTO(Integer page, Integer size) {

        //计算传递的真正页数
        Integer realPage = size * (page - 1);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //获取分页数据

        QuestionExample example = new QuestionExample();
        example.setOrderByClause("gmt_create desc");
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(realPage, size));
        if (questionList != null && questionList.size() > 0) {
            for (int i = 0; i < questionList.size(); i++) {
                QuestionDTO questionDTO = new QuestionDTO();
                Question question = questionList.get(i);
                User user = userMapper.selectByPrimaryKey(question.getCreator());
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
        }
        //获取总的数据量
        Integer count = (int) questionMapper.countByExample(new QuestionExample());
        //设置分页数据
        PageDTO pageDTO = setPageDTO(questionDTOList, page, size, count);
        return pageDTO;
    }

    /**
     * 设置分页数据
     *
     * @param questionDTOList
     * @param page
     * @param size
     * @return
     */
    private PageDTO setPageDTO(List<QuestionDTO> questionDTOList, Integer page, Integer size, Integer count) {
        List<Integer> pageList = new ArrayList<>();
        PageDTO pageDTO = new PageDTO();

        //计算总页数
        Integer totalPage;
        if (count % size == 0) {
            totalPage = count / size;
        } else {
            totalPage = count / size + 1;
        }

        //处理输入的不合理数据
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage && totalPage >= 1) {
            page = totalPage;
        }

        //计算当前需要展示的页数
        pageList.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pageList.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pageList.add(page + i);
            }
        }

        //是否展示向前一页
        pageDTO.setShowPrevious(true);
        if (page == 1) {
            pageDTO.setShowPrevious(false);
        }
        //是否展示回到第一页
        pageDTO.setShowFirstPage(true);
        if (pageList.contains(1)) {
            pageDTO.setShowFirstPage(false);
        }
        //是否展示向下一页
        pageDTO.setShowNext(true);
        if (page == totalPage) {
            pageDTO.setShowNext(false);
        }
        //是否展示回到最后一页
        pageDTO.setShowLastPage(true);
        if (pageList.contains(totalPage)) {
            pageDTO.setShowLastPage(false);
        }

        //设置分页数据
        pageDTO.setTotalPage(totalPage);
        pageDTO.setPageList(pageList);
        pageDTO.setPage(page);
        pageDTO.setQuestions(questionDTOList);

        return pageDTO;
    }

    public PageDTO getUserPageDTO(Integer userId, Integer page, Integer size) {

        //计算传递的真正页数
        Integer realPage = size * (page - 1);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //获取分页数据
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(realPage, size));
        if (questionList != null && questionList.size() > 0) {
            for (int i = 0; i < questionList.size(); i++) {
                QuestionDTO questionDTO = new QuestionDTO();
                Question question = questionList.get(i);
                User user = userMapper.selectByPrimaryKey(question.getCreator());
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
        }

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer count = (int) questionMapper.countByExample(questionExample);
        //设置分页数据
        PageDTO pageDTO = setPageDTO(questionDTOList, page, size, count);
        return pageDTO;
    }

    public QuestionDTO getQuestion(Integer id) {

        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeCodeEnum.QUESTION_NOT_FOUND);
        }
        questionExcMapper.updateViewCount(id);
        //修改viewCount后的question
        question = questionMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void update(Question question) {
        if (question.getId() == null) {
            //新增
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        } else {
            Question updateQuestion = new Question();
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setGmtModified(question.getGmtModified());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(updateQuestion, example);
        }
    }

    public Question getQuestionById(Integer id) {
        return questionMapper.selectByPrimaryKey(id);
    }

    public List<CommentDTO> getComment(Integer id, CommentTypeEnum question) {
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(question.getType());
        example.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(example);
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        //获取userId，并用set去重
        Set<Integer> set = comments.stream().map(Comment::getCommentator).collect(Collectors.toSet());
        List<Integer> userIds = new ArrayList<>();
        userIds.addAll(set);

        //获取user信息放入map中
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }

    public List<CommentDTO> getComment(Integer id) {
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andIdEqualTo(id)
                .andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        example.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(example);
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        //获取userId，并用set去重
        Set<Integer> set = comments.stream().map(Comment::getCommentator).collect(Collectors.toSet());
        List<Integer> userIds = new ArrayList<>();
        userIds.addAll(set);

        //获取user信息放入map中
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }

    public List<QuestionDTO> getRelatedQuestion(QuestionDTO question) {
        String tag = StringUtils.replace(question.getTag(), "，", "|");
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setTag(tag);
        questionDTO.setId(question.getId());
        List<QuestionDTO> questionDTOList = questionExcMapper.getRelatedQuestion(questionDTO);

        return questionDTOList;
    }
}
