package zxs.up.newlife.dto;

import lombok.Data;

/**
 * @auther ZhangXiusen
 * @date 2020/01/19 21:01
 */
@Data
public class AccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
