package org.chen.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.chen.core.OkHttpClientManager;
import org.chen.dto.BaseToken;
import org.chen.dto.SubButtonInfo;
import org.chen.request.StringRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: 陈亮
 * @Date: 2018/8/6 22:33
 */
@Component
@Slf4j
public class WxMenuUtil {

    @Value("${wx.menu_create}")
    private String menu_create;

    @Value("${wx.menu_get}")
    private String menu_get;

    @Value("${wx.menu_delete}")
    private String menu_delete;

    @Resource()
    private OkHttpClientManager clientManager;

    @Resource
    private WxServerUtil wxServerUtil;

    //删除菜单
    public Object menuDelete() {
        BaseToken baseToken = wxServerUtil.getBaseToken();
        StringRequest req = new StringRequest(menu_delete.replace("ACCESS_TOKEN", baseToken.getAccess_token()));
        String rawBody = clientManager.send(req).getRawBody();
        log.info("删除菜单消息:\n" + rawBody);
        return JSON.parse(rawBody);
    }

    //查询菜单
    public Object menuGet() {
        BaseToken baseToken = wxServerUtil.getBaseToken();
        StringRequest req = new StringRequest(menu_get.replace("ACCESS_TOKEN", baseToken.getAccess_token()));
        String rawBody = clientManager.send(req).getRawBody();
        log.info("查询菜单消息:\n" + rawBody);
        return JSON.parse(rawBody);
    }

    //创建菜单
    public Object menuCreate(SubButtonInfo[] button) {
        BaseToken baseToken = wxServerUtil.getBaseToken();
        StringRequest req = new StringRequest(menu_create.replace("ACCESS_TOKEN", baseToken.getAccess_token()));
        log.info("" + JSONObject.toJSONString(button));
        req.post().add("button", button).setJsonBody(true);
        String rawBody = clientManager.send(req).getRawBody();
        log.info("创建菜单消息:\n" + rawBody);
        return JSON.parse(rawBody);
    }
}
