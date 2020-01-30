package zxs.up.newlife.model;

import lombok.Data;

/**
 * @auther ZhangXiusen
 * @date 2020/01/29 16:15
 */
@Data
public class Question {

    private Integer id;
    private String title;
    private String description;
    private int creator;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
}
