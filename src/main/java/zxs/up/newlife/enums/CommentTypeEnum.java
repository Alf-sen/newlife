package zxs.up.newlife.enums;

/**
 * @auther ZhangXiusen
 * @title
 * @date 2020/2/15 0015 17:26
 */
public enum CommentTypeEnum {
    COMMENT(1),
    QUESTION(2);

    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static Boolean isExit(Integer type) {
        for (CommentTypeEnum commentTypeEnum: CommentTypeEnum.values()) {
           if (commentTypeEnum.getType().equals(type)) {
               return true;
           }
        }

        return false;
    }
}
