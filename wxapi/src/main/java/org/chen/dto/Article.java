package org.chen.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: 陈亮
 * @Date: 2018/8/6 22:26
 */
@Data
public class Article {

    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "跳转URL")
    private String url;
    @ApiModelProperty(value = "图片URL")
    private String picurl;

}
