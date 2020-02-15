package zxs.up.newlife.dto;

import lombok.Data;
import zxs.up.newlife.enums.CustomizeCodeEnum;
import zxs.up.newlife.exception.CustomizeException;

/**
 * @auther ZhangXiusen
 * @date 2020/02/15 17:45
 */
@Data
public class ResultDTO {
    private String code;
    private String message;

    public static ResultDTO resultOf(CustomizeCodeEnum result) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(result.getCode());
        resultDTO.setMessage(result.getMessage());
        return resultDTO;
    }

    public static ResultDTO resultOf(CustomizeException e) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(e.getCode());
        resultDTO.setMessage(e.getMessage());
        return resultDTO;
    }
}
