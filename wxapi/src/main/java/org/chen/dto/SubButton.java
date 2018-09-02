package org.chen.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: 陈亮
 * @Date: 2018/8/6 22:45
 */
@Data
public class SubButton {

    @ApiModelProperty(value = "菜单标题，不超过16个字节，子菜单不超过60个字节\n")
    private String name;
    @ApiModelProperty(value = "菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型")
    private String type;
    @ApiModelProperty(value = "网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url。\n")
    private String url;

}
