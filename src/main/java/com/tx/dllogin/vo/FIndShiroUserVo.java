package com.tx.dllogin.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("用户查询shiro资源vo")
public class FIndShiroUserVo {

    @ApiModelProperty("用户Id")
    private  String userId;

    @ApiModelProperty("查询角色 admin等 名称")
    private String roleName;

    @ApiModelProperty("管理员名称    例如超级管理员")
    private String roleReamark;

    @ApiModelProperty("查询该角色的所有 资源例如 user:update:* 可以简写  该角色能对用户模块所有用户修改 ")
    private List<String> parmsList;



}
