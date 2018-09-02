package org.chen.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author:Mr.Chen
 * Date:2018/8/7
 */
@Data
public class AuthUrl {

    @ApiModelProperty(value = "授权URL")
    private String authUrl;

}
