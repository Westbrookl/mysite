package com.exceptionHandler;

import com.exception.BusinessException;
import com.utils.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一的异常处理用来捕获Controller抛出的异常
 * 并且注意@ControllerAdvice与@RestControllerAdvice之间的区别
 * 前者需要和@ResponseBody结合在一起返回JSON数据格式
 * 而后者是对两个的结合。
 *
 * @ExceptionHandler是对异常捕获后进行的处理。
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger("GlobalExceptionHadler.class");

    /**
     * 也可以直接定义一个类直接去引用设置包装成为一个ResponseEntity<>

     * @param e
     * @return
     */
    @ExceptionHandler(value= BusinessException.class)
    public APIResponse businessException(Exception e){

        String msg = "请求错误";
        if(e instanceof BusinessException){
            msg = ((BusinessException)e).getErrorCode();
        }

        logger.error("find exception:e={}",e.getMessage());
        e.printStackTrace();
        return APIResponse.fail(msg);
    }
}
