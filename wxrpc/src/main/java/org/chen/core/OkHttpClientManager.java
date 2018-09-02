package org.chen.core;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.chen.constant.Constant;
import org.chen.exception.WxException;
import org.chen.request.ApiRequest;
import org.chen.response.ApiResponse;
import org.chen.utils.JsonUtil;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author:Mr.Chen
 * Date:2018/8/3
 */
@Component
public class OkHttpClientManager {
    private static OkHttpClientManager okHttpClientManager;
    private static OkHttpClient client = null;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClientManager() {
        client = new OkHttpClient().newBuilder().build();
    }

    /**
     * 构建请求身体
     */
    private RequestBody createRequestBody(ApiRequest<?, ?> request) {
        if (request.isMultipart()) {
            MediaType contentType = MediaType.parse(request.getContentType());
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

            for (Map.Entry<String, Object> parameter : request.getParameters().entrySet()) {
                String name = parameter.getKey();
                Object value = parameter.getValue();
                if (value instanceof byte[]) {
                    builder.addFormDataPart(name, request.getFileName(), RequestBody.create(contentType, (byte[]) value));
                } else if (value instanceof File) {
                    builder.addFormDataPart(name, request.getFileName(), RequestBody.create(contentType, (File) value));
                } else if (value instanceof RequestBody) {
                    builder.addFormDataPart(name, request.getFileName(), (RequestBody) value);
                } else {
                    builder.addFormDataPart(name, String.valueOf(value));
                }
            }
            return builder.build();
        } else {
            if (request.isJsonBody()) {
                String json = JSONObject.toJSONString(request.getParameters());
                return RequestBody.create(JSON, json);
            } else {
                FormBody.Builder builder = new FormBody.Builder();
                for (Map.Entry<String, Object> parameter : request.getParameters().entrySet()) {
                    builder.add(parameter.getKey(), String.valueOf(parameter.getValue()));
                }
                return builder.build();
            }

        }
    }

    /**
     * 构建请求对象
     */
    private Request createRequest(ApiRequest request) {
        Request.Builder builder = new Request.Builder();
        if (Constant.GET.equalsIgnoreCase(request.getMethod())) {
            builder.get();
            if (null != request.getParameters() && request.getParameters().size() > 0) {
                Set<String> keys = request.getParameters().keySet();
                StringBuilder sbuf = new StringBuilder(request.getUrl());
                if (request.getUrl().contains("=")) {
                    sbuf.append("&");
                } else {
                    sbuf.append("?");
                }
                for (String key : keys) {
                    sbuf.append(key).append('=').append(request.getParameters().get(key)).append('&');
                }
                request.url(sbuf.substring(0, sbuf.length() - 1));
            }
        } else {
            builder.method(request.getMethod(), createRequestBody(request));
        }
        builder.url(request.getUrl());
        if (null != request.getHeaders()) {
            builder.headers(request.getHeaders());
        }
        return builder.build();
    }


    public <T extends ApiRequest, R extends ApiResponse> R send(ApiRequest<T, R> request) {
        try {
            Request okHttpRequest = createRequest(request);
            Response response = client.newCall(okHttpRequest).execute();
            String body = response.body().string();
            // 获取头部的Cookie,注意：可以通过Cooke.parseAll()来获取
            List<Cookie> cookies = Cookie.parseAll(okHttpRequest.url(), response.headers());
            if (ApiResponse.class.equals(request.getResponseType())) {
                return (R) new ApiResponse(body);
            }
            R result = JsonUtil.fromJson(body, request.getResponseType());
            result.setRawBody(body);
            return result;
        } catch (IOException e) {
            throw new WxException(e);
        }
    }
}
