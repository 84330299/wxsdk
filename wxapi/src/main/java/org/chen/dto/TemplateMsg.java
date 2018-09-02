package org.chen.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author:Mr.Chen
 * Date:2018/8/7
 */
@Data
public class TemplateMsg {

    @ApiModelProperty(value = "接收者openid")
    private String touser;
    @ApiModelProperty(value = "模板ID")
    private String template_id;
    @ApiModelProperty(value = "模板跳转链接")
    private String url;
    //    @ApiModelProperty(value = "跳小程序所需数据，不需跳小程序可不用传该数据")
//    private String miniprogram;
//    @ApiModelProperty(value = "所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）")
//    private String appid;
//    @ApiModelProperty(value = "所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），暂不支持小游戏")
//    private String pagepath;
    @ApiModelProperty(value = "模板数据")
    private TemplateMsgOrder data;
//    @ApiModelProperty(value = "模板内容字体颜色，不填默认为黑色")
//    private String color;

}
