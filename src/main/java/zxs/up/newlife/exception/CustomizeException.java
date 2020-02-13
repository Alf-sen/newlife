package zxs.up.newlife.exception;

/**
 * @auther ZhangXiusen
 * @date 2020/02/13 17:45
 */
public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
