package zxs.up.newlife.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zxs.up.newlife.dto.QuestionDTO;
import zxs.up.newlife.mapper.QuestionMapper;
import zxs.up.newlife.mapper.UserMapper;
import zxs.up.newlife.model.Question;
import zxs.up.newlife.model.User;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther ZhangXiusen
 * @date 2020/01/31 22:06
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> getQustionDTO() {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questionList = questionMapper.selectAll();
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
        return questionDTOList;
    }
}
