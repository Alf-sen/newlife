package zxs.up.newlife.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import zxs.up.newlife.model.Question;

import java.util.List;

/**
 * @auther ZhangXiusen
 * @title
 * @date 2020/01/29 16:19
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title, description, tag, gmt_create, gmt_modified, creator, comment_count, view_count, like_count) values (#{title}, #{description}, #{tag}, #{gmtCreate}, #{gmtModified}, #{creator}, #{commentCount}, #{viewCount}, #{likeCount})")
    void insert(Question question);

    @Select("select * from question limit #{page}, #{size}")
    List<Question> selectAll(@Param("page") Integer page, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer getCount();

    @Select("select * from question where creator = #{userId} limit #{page}, #{size}")
    List<Question> selectByUerId(@Param("userId") Integer userId, @Param("page") Integer page, @Param("size") Integer size);

    @Select("select count(1) from question where creator = #{userId}")
    Integer getUserCount(@Param("userId") Integer userId);

    @Select("select * from question where id = #{id}")
    Question getQuestionById(@Param("id") Integer id);

    @Update("update question set title = #{title}, description = #{description}, tag = #{tag}, gmt_modified = #{gmtModified} where id = #{id}")
    void update(Question question);
}
