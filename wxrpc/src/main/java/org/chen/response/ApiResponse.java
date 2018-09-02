package org.chen.response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;

/**
 * Author:Mr.Chen
 * Date:2018/8/3
 */
public class ApiResponse {
    private static final Gson gson = new Gson();

    protected String rawBody;

    public <T> T parse(Class<T> type) {
        return gson.fromJson(rawBody, type);
    }

    public <T> T parse(Type type) {
        return gson.fromJson(rawBody, type);
    }

    public JsonObject toJsonObject() {
        return new JsonParser().parse(rawBody).getAsJsonObject();
    }

    public String getRawBody() {
        return rawBody;
    }

    public void setRawBody(String rawBody) {
        this.rawBody = rawBody;
    }

    public ApiResponse(String rawBody) {
        this.rawBody = rawBody;
    }
}
