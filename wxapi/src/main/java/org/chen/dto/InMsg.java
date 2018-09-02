package org.chen.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author: 陈亮
 * @Date: 2018/8/5 16:43
 */
@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class InMsg {

    @ApiModelProperty(value = "开发者微信号")
    private String ToUserName;
    @ApiModelProperty(value = "发送方帐号（一个OpenID）")
    private String FromUserName;
    @ApiModelProperty(value = "消息创建时间")
    private Long CreateTime;
    @ApiModelProperty(value = "消息类型")
    private String MsgType;
    @ApiModelProperty(value = "消息id，64位整型")
    private Long MsgId;
    @ApiModelProperty(value = "图片消息媒体id，可以调用多媒体文件下载接口拉取数据")
    private String MediaId;
    @ApiModelProperty(value = "文本消息内容")
    private String Content;
    @ApiModelProperty(value = "事件类型，subscribe(订阅)、unsubscribe(取消订阅)")
    private String Event;
}
