package org.chen.exception;

import java.io.Serializable;

/**
 * @Author: Mr.Chen
 * @Date: 2018/7/4 11:11
 */
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private static volatile ErrorResponse instance = null;
    private String msg;
    private Integer code;
    private Object data;

    private ErrorResponse() {
    }

    public static ErrorResponse getInstance() {
        if (instance == null) {
            synchronized (ErrorResponse.class) {
                if (instance == null) {
                    instance = new ErrorResponse();
                }
            }
        }
        return instance;
    }

    public String getMsg() {
        return msg;
    }

    public ErrorResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public ErrorResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ErrorResponse setData(Object data) {
        this.data = data;
        return this;
    }

}
