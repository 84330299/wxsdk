package org.chen.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @Author: Mr.Chen
 * @Date: 2018/7/4 11:08
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("缺少请求参数", e);
        return ErrorResponse.getInstance().setCode(400).setMsg("缺少请求参数").setData(null);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("缺少请求参数", e);
        return ErrorResponse.getInstance().setCode(400).setMsg("参数解析失败").setData(null);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        log.error("参数验证失败", message);
        return ErrorResponse.getInstance().setCode(400).setMsg("参数验证失败").setData(null);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorResponse handleBindException(BindException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        log.error("参数绑定失败", message);
        return ErrorResponse.getInstance().setCode(400).setMsg("参数绑定失败").setData(null);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleServiceException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        log.error("参数验证失败", message);
        return ErrorResponse.getInstance().setCode(400).setMsg("参数验证失败").setData(null);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorResponse handleValidationException(ValidationException e) {
        log.error("参数验证失败", e.getMessage());
        return ErrorResponse.getInstance().setCode(400).setMsg("参数验证失败").setData(null);
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求方法", e.getMessage());
        return ErrorResponse.getInstance().setCode(500).setMsg("不支持当前请求方法").setData(null);
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ErrorResponse handleHttpMediaTypeNotSupportedException(Exception e) {
        log.error("不支持当前媒体类型", e.getMessage());
        return ErrorResponse.getInstance().setCode(405).setMsg("不支持当前媒体类型").setData(null);
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(WxException.class)
    public ErrorResponse handleServiceException(WxException e) {
        log.error("业务逻辑异常", e.getMessage());
        return ErrorResponse.getInstance().setCode(600).setMsg(e.getMessage()).setData(null);
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {
        e.printStackTrace();
        log.error("通用异常", e.getMessage());
        return ErrorResponse.getInstance().setCode(500).setMsg(e.getMessage()).setData(null);
    }

    /**
     * 操作数据库出现异常:名称重复，外键关联
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse handleException(DataIntegrityViolationException e) {
        log.error("操作数据库出现异常:", e.getMessage());
        return ErrorResponse.getInstance().setCode(500).setMsg(e.getMessage()).setData(null);
    }


}
