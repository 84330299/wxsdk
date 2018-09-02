package org.chen.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author:Mr.Chen
 * Date:2018/8/6
 */
//网页授权access_token
@Data
public class PageAccessToken {

    @ApiModelProperty(value = "网页授权接口调用凭证")
    private String access_token;
    @ApiModelProperty(value = "access_token接口调用凭证超时时间，单位（秒）")
    private Long expires_in;
    @ApiModelProperty(value = "用户刷新access_token")
    private String refresh_token;
    @ApiModelProperty(value = "用户唯一标识")
    private String openid;
    @ApiModelProperty(value = "用户授权的作用域")
    private String scope;

}
