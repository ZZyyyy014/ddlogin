package com.tx.dllogin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserFindAllVo {
    //部门名称
    private String deptName;
    //部门Id
    private String deptId;
    private String userId;
    private String userName;
    private  String userRealName;
   // private String userPasswrod;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginDate;
    private String ip;

    // 所在公司id
    private String  sparessV1;


    //所在公司名称
    private String firmName;

    // private String spareV3;
    private String levelss;
}
