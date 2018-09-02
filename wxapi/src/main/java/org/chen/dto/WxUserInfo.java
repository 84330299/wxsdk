package org.chen.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author:Mr.Chen
 * Date:2018/8/6
 */
//微信用户基础信息
@Data
public class WxUserInfo {

    @ApiModelProperty(value = "网页授权接口调用凭证")
    private String openid;
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
    @ApiModelProperty(value = "用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
    private String sex;
    @ApiModelProperty(value = "用户个人资料填写的省份")
    private String province;
    @ApiModelProperty(value = "普通用户个人资料填写的城市")
    private String city;
    @ApiModelProperty(value = "国家")
    private String country;
    //用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效
    @ApiModelProperty(value = "用户头像")
    private String headimgurl;
    //用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
    @ApiModelProperty(value = "用户特权信息")
    private String[] privilege;
    @ApiModelProperty(value = "只有将公众号绑定到微信开放平台帐号后，才会出现该字段")
    private String unionid;

}
