package com.tx.dllogin.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("根据用户名查询部门id+公司id")
public class findDeptAndFirmVo {


    private String deptId;

    private  String firmId;

}
