package org.chen.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Author: 陈亮
 * @Date: 2018/8/5 17:13
 */
@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class OutMsg {

    @ApiModelProperty(value = "发送方的账号")
    private String FromUserName;
    @ApiModelProperty(value = "接收方的账号(OpenID)")
    private String ToUserName;
    @ApiModelProperty(value = "消息创建时间")
    private Long CreateTime;
    @ApiModelProperty(value = "消息类型")
    private String MsgType;
    @XmlElementWrapper(name = "Image")
    @ApiModelProperty(value = "图片消息媒体id，可以调用多媒体文件下载接口拉取数据")
    private String[] MediaId;
    @ApiModelProperty(value = "文本消息内容")
    private String Content;

    //图文消息
    @ApiModelProperty(value = "图文消息个数，限制为8条以内")
    private Integer ArticleCount;
    @XmlElementWrapper(name = "Articles")
    @ApiModelProperty(value = "图文列表")
    private ArticleItem[] item;

}
