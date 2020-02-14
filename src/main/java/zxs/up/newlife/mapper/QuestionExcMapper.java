package zxs.up.newlife.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @auther ZhangXiusen
 * @title
 * @date 2020/02/14 11:37
 */
public interface QuestionExcMapper {

    @Update("update question set view_Count = view_Count + 1 where id = ${id}")
    void updateViewCount(@Param("id") Integer id);
}
