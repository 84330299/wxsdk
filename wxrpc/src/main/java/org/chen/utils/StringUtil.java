package org.chen.utils;

import lombok.Data;

import java.util.Map;

/**
 * Author:Mr.Chen
 * Date:2018/8/4
 */
@Data
public abstract class StringUtil<T> {

    public StringUtil(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    protected final T thisAsT = (T) this;
    private final Map<String, Object> parameters;

    public T add(String key, Object val) {
        parameters.put(key, val);
        return thisAsT;
    }

}
