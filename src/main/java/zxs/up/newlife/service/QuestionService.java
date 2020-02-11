package zxs.up.newlife.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zxs.up.newlife.dto.PageDTO;
import zxs.up.newlife.dto.QuestionDTO;
import zxs.up.newlife.mapper.QuestionMapper;
import zxs.up.newlife.mapper.UserMapper;
import zxs.up.newlife.model.Question;
import zxs.up.newlife.model.User;

import javax.naming.ldap.PagedResultsControl;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther ZhangXiusen
 * @date 2020/02/01 21:18
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PageDTO getPageDTO(Integer page, Integer size) {

        //计算传递的真正页数
        Integer realPage = size * (page - 1);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //获取分页数据
        List<Question> questionList = questionMapper.selectAll(realPage, size);
        if (questionList != null && questionList.size() > 0) {
            for (int i = 0; i < questionList.size(); i++) {
                QuestionDTO questionDTO = new QuestionDTO();
                Question question = questionList.get(i);
                User user = userMapper.findById(question.getCreator());
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
        }
        //获取总的数据量
        Integer count = questionMapper.getCount();
        //设置分页数据
        PageDTO pageDTO = setPageDTO(questionDTOList, page, size, count);
        return pageDTO;
    }

    /**
     * 设置分页数据
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
        List<Question> questionList = questionMapper.selectByUerId(userId, realPage, size);
        if (questionList != null && questionList.size() > 0) {
            for (int i = 0; i < questionList.size(); i++) {
                QuestionDTO questionDTO = new QuestionDTO();
                Question question = questionList.get(i);
                User user = userMapper.findById(question.getCreator());
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
        }

        Integer count = questionMapper.getUserCount(userId);
        //设置分页数据
        PageDTO pageDTO = setPageDTO(questionDTOList, page, size, count);
        return pageDTO;
    }

    public QuestionDTO getQuestion(String id) {

        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.getQuestionById(id);
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }
}
