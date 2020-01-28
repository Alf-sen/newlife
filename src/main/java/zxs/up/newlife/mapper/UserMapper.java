package zxs.up.newlife.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import zxs.up.newlife.model.User;

/**
 * @auther ZhangXiusen
 * @date 2020/01/28 13:29
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified})")
    void insert(User user);
}
