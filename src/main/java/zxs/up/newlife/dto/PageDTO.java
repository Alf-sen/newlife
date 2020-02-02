package zxs.up.newlife.dto;

import lombok.Data;

import java.util.List;

/**
 * @auther ZhangXiusen
 * @date 2020/02/01 21:14
 */
@Data
public class PageDTO {
    private List<QuestionDTO> questions;
    private Integer page;
    private Integer totalPage;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showLastPage;
    private List<Integer> pageList;
}
