package org.chen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.chen.dto.SubButtonInfo;
import org.chen.utils.WxMenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 陈亮
 * @Date: 2018/8/6 22:30
 */
@Api(tags = "菜单相关")
@RestController
public class WxMenuController {

    @Autowired
    private WxMenuUtil wxMenuUtil;

    @ApiOperation(value = "查询菜单")
    @GetMapping(value = "menuget")
    public Object menuGet() {
        return wxMenuUtil.menuGet();
    }

    @ApiOperation(value = "创建菜单")
    @PostMapping(value = "menucreate")
    public Object menuCreate(@RequestBody SubButtonInfo[] button) {
        return wxMenuUtil.menuCreate(button);
    }

    @ApiOperation(value = "删除菜单")
    @GetMapping(value = "menudelete")
    public Object menuDelete() {
        return wxMenuUtil.menuDelete();
    }

}
