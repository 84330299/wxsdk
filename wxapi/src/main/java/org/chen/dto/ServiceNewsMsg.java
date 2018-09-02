package org.chen.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author:Mr.Chen
 * Date:2018/8/6
 */
@Data
public class ServiceNewsMsg {

    @ApiModelProperty(value = "普通用户openid")
    private String touser;
    //消息类型，文本为text，图片为image，语音为voice，视频消息为video，音乐消息为music，图文消息为news，图文消息（点击跳转到图文消息页面）为mpnews，卡券为wxcard，小程序为miniprogrampage
    @ApiModelProperty(value = "消息类型")
    private String msgtype;
    @ApiModelProperty(value = "消息体")
    private News news;


}

