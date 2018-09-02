package org.chen.request;

import org.chen.response.ApiResponse;

/**
 * Author:Mr.Chen
 * Date:2018/8/3
 */
public class StringRequest extends  ApiRequest<StringRequest,ApiResponse> {
    public StringRequest(String url) {
        super(url, ApiResponse.class);
    }
}
