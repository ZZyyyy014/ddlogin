package com.tx.dllogin.findBean;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("权限查询-模糊查询-请求参数")
public class FindAllShiroBean {

    @ApiModelProperty("当前页码")
    private Integer pageNum;

    @ApiModelProperty("每页多少条")
    private Integer pageSize;

    @ApiModelProperty("用户账号")
    private String userName;

    @ApiModelProperty("用户真实姓名")
    private  String sparessV1;

    @ApiModelProperty("所在公司")
    private  String firmName;

    @ApiModelProperty("所在部门")
    private String  deptName;

}
