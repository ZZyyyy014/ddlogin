package com.tx.dllogin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel
public class FindAllShopVo {

    @ApiModelProperty("当前页数")
    private Integer pageNum;

    @ApiModelProperty("每页多少条")
    private Integer pageSize;

    @ApiModelProperty("绑定用户账号")
    private String userName;

    @ApiModelProperty("店铺账号")
    private String shopUserName;

    @ApiModelProperty("店铺IP")
    private String shopIp;

    @ApiModelProperty("店铺登录时间段")
    private List<Date> shopLoginDateList;



}
