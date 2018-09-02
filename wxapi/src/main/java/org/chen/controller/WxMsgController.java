package org.chen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.chen.dto.ServiceNewsMsg;
import org.chen.dto.ServiceTextMsg;
import org.chen.dto.TemplateMsg;
import org.chen.utils.WxMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:Mr.Chen
 * Date:2018/8/6
 */
@Api(tags = "消息管理")
@RestController
public class WxMsgController {

    @Autowired
    private WxMessageUtil wxMessageUtil;

    @ApiOperation(value = "发送客服消息[文本消息]")
    @PostMapping(value = "sendmsgtext")
    public Object sendMsgText(@RequestBody ServiceTextMsg msg) {
        return wxMessageUtil.sendMsgText(msg);
    }

    @ApiOperation(value = "发送客服消息[图文消息]")
    @PostMapping(value = "sendmsgnews")
    public Object sendMsgNews(@RequestBody ServiceNewsMsg msg) {
        return wxMessageUtil.sendMsgNews(msg);
    }

    @ApiOperation(value = "发送模板消息")
    @PostMapping(value = "sendmsgtemplate")
    public Object sendMsgTemplate(@RequestBody TemplateMsg msg) {
        return wxMessageUtil.sendMsgTemplate(msg);
    }


}
