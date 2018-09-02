package org.chen.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author: 陈亮
 * @Date: 2018/8/5 22:31
 */
@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class ArticleItem {

    @ApiModelProperty(value = "图文消息标题")
    private String Title;
    @ApiModelProperty(value = "图文消息描述")
    private String Description;
    @ApiModelProperty(value = "图片链接")
    private String PicUrl;
    @ApiModelProperty(value = "点击图文消息跳转链接")
    private String Url;
}
