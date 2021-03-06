package zxs.up.newlife.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @auther ZhangXiusen
 * @date 2020/02/15 18:47
 */
public interface CommentExcMapper {

    @Update("update comment set like_count = like_count + 1 where id = ${id}")
    void addLikeCount(@Param("id") Integer id);

    @Update("update comment set like_count = like_count - 1 where id = ${id}")
    void lessLikeCount(@Param("id") Integer id);

    @Update("update comment set comment_count = comment_count + 1 where id = ${id}")
    void updateCommentCount(@Param("id") Integer id);

}
