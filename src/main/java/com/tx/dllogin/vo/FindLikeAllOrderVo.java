package com.tx.dllogin.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FindLikeAllOrderVo {

    private Integer LiekpageNum;
    private Integer LikepageSize;

    private String orderNumber;
    private String customerTel;
    private String expressageCode;
    private String customerName;
    private String shopUserName;


    private List<Date> orderdateList;

}
