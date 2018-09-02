package org.chen.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author:Mr.Chen
 * Date:2018/8/6
 */
@Data
public class BaseToken {

    @ApiModelProperty(value = "获取到的凭证")
    private String access_token;
    @ApiModelProperty(value = "凭证有效时间，单位：秒")
    private Long expires_in;
}
