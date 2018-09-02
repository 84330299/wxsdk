package org.chen.exception;

/**
 * Author:Mr.Chen
 * Date:2018/8/3
 */
public class WxException extends RuntimeException {

    public WxException() {
    }

    public WxException(String message) {
        super(message);
    }

    public WxException(String message, Throwable cause) {
        super(message, cause);
    }

    public WxException(Throwable cause) {
        super(cause);
    }

    public WxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
