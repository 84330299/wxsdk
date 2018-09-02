package org.chen.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: 陈亮
 * @Date: 2018/8/6 22:27
 */
@Data
public class News {

    @ApiModelProperty(value = "图文列表")
    private List<Article> articles;

}
