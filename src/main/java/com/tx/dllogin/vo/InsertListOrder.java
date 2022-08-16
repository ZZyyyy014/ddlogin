package com.tx.dllogin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class InsertListOrder {

    private String orderId;
    //订单号
    private String orderNumber;
    //订单时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDate;

    //店铺账号
    private String shopUserName;
    //手机号码
    private String customerTel;
    //快递单号
    private String expressageCode;
    //联系人姓名
    private String customerName;
    //备用字段 123
    private String sparessV1;
    private String sparessV2;
    private String sparessV3;


}
