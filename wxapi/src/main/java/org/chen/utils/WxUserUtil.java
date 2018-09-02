package org.chen.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.chen.core.OkHttpClientManager;
import org.chen.dto.BaseToken;
import org.chen.dto.PageAccessToken;
import org.chen.dto.UserList;
import org.chen.dto.WxUserInfo;
import org.chen.request.StringRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Author:Mr.Chen
 * Date:2018/8/6
 */
@Component
@Slf4j
public class WxUserUtil {

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.appsecret}")
    private String appsecret;

    @Value("${wx.accesstoken_url}")
    private String accesstoken_url;

    @Value("${wx.pageauth_url}")
    private String pageauth_url;

    @Value("${wx.userinfo_url}")
    private String userinfo_url;

    @Value("${wx.userlist_url}")
    private String userlist_url;

    @Resource()
    private OkHttpClientManager clientManager;

    @Resource
    private WxServerUtil wxServerUtil;

    /**
     * 通过code获取access_token
     *
     * @param code
     */
    public PageAccessToken getToken(String code) {
        StringRequest req = new StringRequest(accesstoken_url.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code));
        String rawBody = clientManager.send(req).getRawBody();
        log.info("通过code获取access_token:\n" + rawBody);
        return JSON.parseObject(rawBody, PageAccessToken.class);
    }

    /**
     * 通过access_token和openid获取用户信息
     *
     * @param access_token
     * @param openid
     */
    public WxUserInfo getUserInfo(String access_token, String openid) {
        StringRequest req = new StringRequest(userinfo_url.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid));
        String rawBody = clientManager.send(req).getRawBody();
        log.info("通过access_token和openid获取用户信息:\n" + rawBody);
        return JSON.parseObject(rawBody, WxUserInfo.class);
    }

    /**
     * 获取用户列表
     */
    public UserList getUserList() {
        BaseToken baseToken = wxServerUtil.getBaseToken();
        StringRequest req = new StringRequest(userlist_url.replace("ACCESS_TOKEN", baseToken.getAccess_token()).replace("NEXT_OPENID", ""));
        String rawBody = clientManager.send(req).getRawBody();
        log.info("获取用户列表信息:\n" + rawBody);
        return JSON.parseObject(rawBody, UserList.class);
    }
}
