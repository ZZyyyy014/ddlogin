package com.tx.dllogin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("登录后查询菜单栏")
public class FindLogRoterVo {

    @ApiModelProperty("1级菜单栏")
    private String   oneMenu;

    @ApiModelProperty("1级菜单栏Id")
    private String  oneId;

    private String oneMenuName;

    @ApiModelProperty("2级菜单路由跳转")
    private List<TwoMenuRouter> twoMenuRouterList;



}
