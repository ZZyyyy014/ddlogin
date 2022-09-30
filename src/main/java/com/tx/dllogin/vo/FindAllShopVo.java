package com.tx.dllogin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("根据权限查询返回")
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

    @ApiModelProperty("店铺类型 账号类型  1京东   2拼多多   3淘宝")
    private  String userTypess;

    @ApiModelProperty("店铺登录时间段")
    private List<Date> shopLoginDateList;

}
