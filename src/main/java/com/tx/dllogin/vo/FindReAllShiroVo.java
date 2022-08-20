package com.tx.dllogin.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("权限页面查询所有")
public class FindReAllShiroVo {


    @ApiModelProperty("用户账号")
    private String userName;
    @ApiModelProperty("用户登录Ip")
    private String Ip;
    @ApiModelProperty("用户真实姓名")
    private String sparessV1;



    @ApiModelProperty("公司Id")
    private String firmId;
    @ApiModelProperty("公司名称")
    private String firmName;


    @ApiModelProperty("部门Id")
    private String depteId;
    @ApiModelProperty("部门名称")
    private String deptName;

    @ApiModelProperty("角色名称")
    private String roleId;
    @ApiModelProperty("角色名称")
    private String roleRemark;


}
