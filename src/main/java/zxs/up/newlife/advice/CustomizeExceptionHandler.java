package zxs.up.newlife.advice;

import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import zxs.up.newlife.dto.ResultDTO;
import zxs.up.newlife.enums.CustomizeCodeEnum;
import zxs.up.newlife.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @auther ZhangXiusen
 * @date 2020/02/13 17:49
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e,
                        Model model,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        ResultDTO resultDTO;
        String contetnType = request.getContentType();
        if ("application/json".equals(contetnType)) {

            if (e instanceof CustomizeException) {
                resultDTO = ResultDTO.resultOf((CustomizeException) e);
                response.setContentType(contetnType);
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                try {
                    PrintWriter writer = response.getWriter();
                    writer.write(JSON.toJSONString(resultDTO));
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } else {
                resultDTO = ResultDTO.resultOf(CustomizeCodeEnum.SYS_ERROR);
            }
            return null;
        } else {
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomizeCodeEnum.SYS_ERROR.getMessage());
            }

            return new ModelAndView("error");
        }
    }
}
