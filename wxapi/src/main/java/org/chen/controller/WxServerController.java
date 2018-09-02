package org.chen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.chen.dto.BaseToken;
import org.chen.dto.InMsg;
import org.chen.dto.OutMsg;
import org.chen.utils.WxMessageUtil;
import org.chen.utils.WxServerUtil;
import org.chen.utils.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:Mr.Chen
 * Date:2018/8/6
 */
@Api(tags = "服务器相关以及消息处理")
@RestController
public class WxServerController {

    @Autowired
    private WxUtil wxUtil;

    @Autowired
    private WxMessageUtil wxMessageUtil;

    @Autowired
    private WxServerUtil wxServerUtil;

//    @GetMapping
//    public Map<String, Object> index() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("msg", "success");
//        map.put("code", 200);
//        return map;
//    }


    @ApiOperation(value = "验证服务器")
    @GetMapping(value = "wechat")
    public String validate(String signature, String timestamp, String nonce, String echostr) {
        if (wxUtil.validate(signature, timestamp, nonce)) {
            return echostr;
        } else {
            return null;
        }
    }

    @ApiOperation(value = "被动回复用户消息")
    @PostMapping(value = "wechat")
    public Object messageHandle(@RequestBody InMsg msg) {
        return wxUtil.beanToXml(wxMessageUtil.passiveReply(msg), OutMsg.class);
    }


    @ApiOperation(value = "获取基础Access_Token")
    @PostMapping(value = "basetoken")
    public BaseToken messageHandle() {
        return wxServerUtil.getBaseToken();
    }

}
