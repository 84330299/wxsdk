package org.chen.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: 陈亮
 * @Date: 2018/8/6 22:53
 */
@Data
public class SubButtonInfo {
    @ApiModelProperty(value = "菜单标题，不超过16个字节，子菜单不超过60个字节")
    private String name;
    @ApiModelProperty(value = "二级菜单数组，个数应为1~5个")
    private SubButton[] sub_button;
}
