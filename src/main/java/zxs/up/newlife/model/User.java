package zxs.up.newlife.model;

import lombok.Data;

/**
 * @auther ZhangXiusen
 * @date 2020/01/28 13:30
 */
@Data
public class User {

    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
