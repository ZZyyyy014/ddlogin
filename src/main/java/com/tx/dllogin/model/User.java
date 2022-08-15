package com.tx.dllogin.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String userId;

    private String userName;

    private String userPasswrod;

    private Date loginDate;

    private String deptId;

    private String ip;

    private String sparessV1;

    private String sparessV2;

    private String sparessV3;

    private String levelss;

}