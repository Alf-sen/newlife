package zxs.up.newlife.exception;

import zxs.up.newlife.enums.CustomizeCodeEnum;

/**
 * @auther ZhangXiusen
 * @date 2020/02/13 17:45
 */
public class CustomizeException extends RuntimeException{

    private String code;

    private String message;


    public CustomizeException(String message, String code) {
        this.code = code;
        this.message = message;
    }

    public CustomizeException(CustomizeCodeEnum notFoundQuestion) {
        this.code = notFoundQuestion.getCode();
        this.message = notFoundQuestion.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
