package zxs.up.newlife.dto;

import lombok.Data;

import java.util.List;

/**
 * @auther ZhangXiusen
 * @date 2020/03/01 10:59
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tagList;
}
