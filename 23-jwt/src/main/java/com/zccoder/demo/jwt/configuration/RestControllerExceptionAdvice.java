package com.zccoder.demo.jwt.configuration;

import com.zccoder.demo.jwt.domain.EmptyMeta;
import com.zccoder.demo.jwt.domain.ResponseBody;
import com.zccoder.demo.jwt.exception.DemoJwtException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理器
 *
 * @author zc
 * @date 2020/10/12
 */
@RestControllerAdvice(annotations = {RestController.class})
public class RestControllerExceptionAdvice {

    private Logger logger = LoggerFactory.getLogger(RestControllerExceptionAdvice.class);

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseBody<Object, EmptyMeta> handleMethodValidationException(ConstraintViolationException ex) {
        return ResponseBody.fail(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY), ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseBody<Object, EmptyMeta> handleBeanValidationException(MethodArgumentNotValidException ex) {
        return ResponseBody.fail(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY), ex.getMessage());
    }

    @ExceptionHandler({BindException.class})
    public ResponseBody<Object, EmptyMeta> handleBindException(BindException ex) {
        return ResponseBody.fail(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY), ex.getMessage());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseBody<Object, EmptyMeta> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ResponseBody.fail(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY), ex.getMessage());
    }

    @ExceptionHandler({MissingPathVariableException.class})
    public ResponseBody<Object, EmptyMeta> handleMissingPathVariableException(MissingPathVariableException ex) {
        return ResponseBody.fail(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY), ex.getMessage());
    }

    @ExceptionHandler({UnsatisfiedServletRequestParameterException.class})
    public ResponseBody<Object, EmptyMeta> handleUnsatisfiedServletRequestParameterException(UnsatisfiedServletRequestParameterException ex) {
        return ResponseBody.fail(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY), ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseBody<Object, EmptyMeta> handleAllException(Exception ex) {
        String message;
        if (ex instanceof DemoJwtException) {
            DemoJwtException demoJwtException = (DemoJwtException) ex;

            return ResponseBody.fail(demoJwtException.getStatusCode(), demoJwtException.getMessage());
        } else {
            message = "运行时异常===>" + ExceptionUtils.getRootCauseMessage(ex);
            logger.error(message, ex);
            return ResponseBody.fail(String.valueOf(HttpStatus.BAD_REQUEST), message);
        }
    }
}
