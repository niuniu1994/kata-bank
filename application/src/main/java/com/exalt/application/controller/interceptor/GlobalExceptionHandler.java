package com.exalt.application.controller.interceptor;

import com.exalt.application.model.CustomResponse;
import com.exalt.domain.model.exception.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kainingxin
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessLogicException.class)
    @ResponseBody
    public CustomResponse bizExceptionHandler(HttpServletRequest req, BusinessLogicException e){
        log.error("Business logic error : {}",e.getErrorMsg());
        return CustomResponse.error(e.getErrorCode(),e.getErrorMsg());
    }


    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public CustomResponse exceptionHandler(HttpServletRequest req, Exception e){
        log.error("Unexpected error: ",e);
        return CustomResponse.error();
    }


}
