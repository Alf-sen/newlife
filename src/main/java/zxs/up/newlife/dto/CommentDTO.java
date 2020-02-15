package zxs.up.newlife.dto;

import lombok.Data;

/**
 * @auther ZhangXiusen
 * @date 2020/02/14 17:53
 */
@Data
public class CommentDTO {
    private Integer parentId;
    private Integer type;
    private String content;
}
