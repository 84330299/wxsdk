package org.chen.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author:Mr.Chen
 * Date:2018/8/7
 */
@Data
public class UserList {

    @ApiModelProperty(value = "关注该公众账号的总用户数")
    private Long total;
    @ApiModelProperty(value = "拉取的OPENID个数，最大值为10000\n")
    private Long count;
    @ApiModelProperty(value = "拉取列表的最后一个用户的OPENID\n")
    private String next_openid;
    @ApiModelProperty(value = "列表数据，OPENID的列表\n")
    private OpenIdList data;

    @Data
    class OpenIdList {
        String[] openid;
    }
}
