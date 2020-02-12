package zxs.up.newlife.dto;

import lombok.Data;
import zxs.up.newlife.model.User;

/**
 * @auther ZhangXiusen
 * @date 2020/01/31 22:05
 */
@Data
public class QuestionDTO {

    private Integer id;
    private String title;
    private String description;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private User user;
}
