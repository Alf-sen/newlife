package zxs.up.newlife.enums;

/**
 * @auther ZhangXiusen
 * @title
 * @date 2020/2/13 0013 17:37
 */
public enum CustomizeCodeEnum {
    QUESTION_NOT_FOUND("2001", "没法发现该问题，请确认"),
    COMMENT_NOT_FOUND("2002", "评论不存在，请确认"),
    NO_LOGIN("2003", "没有登录，请登录后再操作"),
    COMMENT_SUCCESS("2004", "回复成功"),
    TYPE_PARAM_WRONG("2005", "评论类型错误或不存在"),
    SYS_ERROR("2006", "服务器异常"),
    COMMENT_IS_NULL("2007", "评论为空");

    private String code;

    private String message;

    CustomizeCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
