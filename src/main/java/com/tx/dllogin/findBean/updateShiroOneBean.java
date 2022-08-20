package com.tx.dllogin.findBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("操作中 修改单个权限请求")
public class updateShiroOneBean {




    @ApiModelProperty("用户名称")
    private String userName;


    @ApiModelProperty("真实姓名")
    private String sparessV1;



    @ApiModelProperty("部门name")
    private  String deptName;


    @ApiModelProperty("公司Id")
   private String firmName;


    @ApiModelProperty("公司名称")
   private String roleRemark;


}
