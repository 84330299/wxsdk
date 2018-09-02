package org.chen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.chen.dto.AuthUrl;
import org.chen.dto.PageAccessToken;
import org.chen.dto.UserList;
import org.chen.dto.WxUserInfo;
import org.chen.utils.WxUserUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * Author:Mr.Chen
 * Date:2018/8/6
 */
@Api(tags = "用户相关")
@RestController
public class WxUserController {

    @Value("${wx.user_host}")
    private String user_host;

    @Value("${wx.pageauth_url}")
    private String pageauth_url;

    @Value("${wx.appid}")
    private String appid;

    @Resource
    private WxUserUtil wxUserUtil;

    @ApiOperation(value = "通过code获取用户信息")
    @GetMapping(value = "userinfo")
    public WxUserInfo getUserInfo(@RequestParam String code) {
        PageAccessToken pageAccessToken = wxUserUtil.getToken(code);
        WxUserInfo wxUserInfo = wxUserUtil.getUserInfo(pageAccessToken.getAccess_token(), pageAccessToken.getOpenid());
        return wxUserInfo;
    }

    @ApiOperation(value = "获取授权链接")
    @PostMapping(value = "authurl")
    /**
     *  authUrl 中的地址需要配置到网页授权中
     * */
    public String getOAuth2CodeUrl(@RequestBody AuthUrl authUrl) {
        try {
            String auth_url = pageauth_url.replace("APPID", appid).replace("REDIRECT_URI", URLEncoder.encode(authUrl.getAuthUrl(), "utf-8")).replace("SCOPE", "snsapi_userinfo");
            return auth_url;
        } catch (Exception e) {
        }
        return null;
    }

    @ApiOperation(value = "授权重定向获取用户信息")
    @GetMapping(value = "user")
    public ModelAndView user(HttpServletRequest request, HttpServletResponse response) {
        String redirect_uri = null;
        try {
            redirect_uri = URLEncoder.encode(user_host, "utf-8");
            String auth_url = pageauth_url.replace("APPID", appid).replace("REDIRECT_URI", redirect_uri).replace("SCOPE", "snsapi_base");
            String code = request.getParameter("code");
            if (code == null) {
                response.sendRedirect(auth_url);
                return null;
            }
            PageAccessToken pageAccessToken = wxUserUtil.getToken(code);
            WxUserInfo wxUserInfo = wxUserUtil.getUserInfo(pageAccessToken.getAccess_token(), pageAccessToken.getOpenid());
            ModelAndView mv = new ModelAndView("/user");
            mv.addObject("user", wxUserInfo);
            return mv;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @ApiOperation(value = "获取用户列表")
    @GetMapping(value = "userlist")
    public UserList getUserList() {
        return wxUserUtil.getUserList();
    }
}
