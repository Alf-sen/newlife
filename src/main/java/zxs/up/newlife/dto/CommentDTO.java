package zxs.up.newlife.dto;

import lombok.Data;
import zxs.up.newlife.model.User;

/**
 * @auther ZhangXiusen
 * @date 2020/02/24 18:22
 */
@Data
public class CommentDTO {

    private Integer id;

    private Integer parentId;

    private Integer type;

    private String content;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer likeCount;

    private Integer commentator;

    private Integer commentCount;

    private User user;
}
