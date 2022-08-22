package com.tx.dllogin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("批量添加订单实体")
public class InsertListOrder {

    @ApiModelProperty("订单id  可以不填 后台自动生成")
    private String orderId;
    //订单号
    @ApiModelProperty("订单号")
    private String orderNumber;
    //订单时间
    @ApiModelProperty("订单时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDate;

    //店铺账号
    @ApiModelProperty("店铺账号")
    private String shopUserName;
    //手机号码
    @ApiModelProperty("手机号码")
    private String customerTel;
    //快递单号
    @ApiModelProperty("快递单号")
    private String expressageCode;
    //联系人姓名
    @ApiModelProperty("联系人姓名")
    private String customerName;
    //备用字段 123

    @ApiModelProperty("备用字段123")
    private String sparessV1;

    private String sparessV2;
    private String sparessV3;


}
