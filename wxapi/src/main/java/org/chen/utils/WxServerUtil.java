package org.chen.utils;

import com.alibaba.fastjson.JSON;
import org.chen.core.OkHttpClientManager;
import org.chen.dto.BaseToken;
import org.chen.dto.PageAccessToken;
import org.chen.request.StringRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Author:Mr.Chen
 * Date:2018/8/6
 */
@Component
public class WxServerUtil {

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.appsecret}")
    private String appsecret;

    @Value("${wx.base_accesstoken_url}")
    private String base_accesstoken_url;

    @Resource()
    private OkHttpClientManager clientManager;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${wx.redisexpires}")
    private int redisexpires;

    /**
     * 获取基础access_token
     */
    public BaseToken getBaseToken() {
        BaseToken baseToken = (BaseToken) redisTemplate.opsForValue().get("basetoken");
        if (baseToken == null) {
            StringRequest req = new StringRequest(base_accesstoken_url.replace("APPID", appid).replace("APPSECRET", appsecret));
            String rawBody = clientManager.send(req).getRawBody();
            baseToken = JSON.parseObject(rawBody, BaseToken.class);
            redisTemplate.opsForValue().set("basetoken", baseToken);
            redisTemplate.expire("basetoken", redisexpires, TimeUnit.SECONDS);
        }
        return baseToken;
    }


}
