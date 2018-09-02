package org.chen.request;

import okhttp3.Headers;
import org.chen.constant.Constant;
import org.chen.response.ApiResponse;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:Mr.Chen
 * Date:2018/8/3
 */
public abstract class ApiRequest<T extends ApiRequest, R extends ApiResponse> {
    protected int timeout = 10;
    protected boolean noRedirect;
    protected boolean jsonBody;
    protected boolean multipart;

    protected String url;
    protected String method = "GET";
    protected String fileName;
    protected String contentType = "application/x-www-form-urlencoded";
    protected Headers headers;
    @SuppressWarnings("unchecked")
    protected final T thisAsT = (T) this;
    private final Class<? extends R> responseClass;
    private final Map<String, Object> parameters;


    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isNoRedirect() {
        return noRedirect;
    }

    public void setNoRedirect(boolean noRedirect) {
        this.noRedirect = noRedirect;
    }

    public boolean isJsonBody() {
        return jsonBody;
    }

    public void setJsonBody(boolean jsonBody) {
        this.jsonBody = jsonBody;
    }

    public boolean isMultipart() {
        return multipart;
    }

    public void setMultipart(boolean multipart) {
        this.multipart = multipart;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public T getThisAsT() {
        return thisAsT;
    }

    public Class<? extends R> getResponseClass() {
        return responseClass;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public ApiRequest(String url, Class<? extends R> responseClass) {
        this.url = url;
        this.responseClass = responseClass;
        this.parameters = new HashMap<>();
        this.headers = Headers.of("User-Agent", Constant.USER_AGENT, "Content-Type", this.contentType);
    }

    public T header(String name, String value) {
        this.headers = this.headers.newBuilder().set(name, value).build();
        return thisAsT;
    }

    public T add(String name, Object val) {
        parameters.put(name, val);
        return thisAsT;
    }

    public T noRedirect() {
        this.noRedirect = true;
        return thisAsT;
    }

    public T multipart() {
        this.multipart = true;
        return thisAsT;
    }

    public Type getResponseType() {
        return responseClass;
    }

    public T url(String url) {
        this.url = url;
        return thisAsT;
    }

    public T timeout(int seconds) {
        this.timeout = seconds;
        return thisAsT;
    }

    public T fileName(String fileName) {
        this.fileName = fileName;
        return thisAsT;
    }

    public T post() {
        this.method = "POST";
        return thisAsT;
    }

    public T delete() {
        this.method = "DELETE";
        return thisAsT;
    }

    public T put() {
        this.method = "PUT";
        return thisAsT;
    }

    public T jsonBody() {
        this.jsonBody = true;
        this.contentType = "application/json; charset=UTF-8";
        this.header("Content-Type", this.contentType);
        return thisAsT;
    }
}
