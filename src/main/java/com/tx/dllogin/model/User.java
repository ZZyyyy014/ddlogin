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

    private String spareV1;

    private String spareV2;

    private String spareV3;

    private String level;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPasswrod() {
        return userPasswrod;
    }

    public void setUserPasswrod(String userPasswrod) {
        this.userPasswrod = userPasswrod == null ? null : userPasswrod.trim();
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getSpareV1() {
        return spareV1;
    }

    public void setSpareV1(String spareV1) {
        this.spareV1 = spareV1 == null ? null : spareV1.trim();
    }

    public String getSpareV2() {
        return spareV2;
    }

    public void setSpareV2(String spareV2) {
        this.spareV2 = spareV2 == null ? null : spareV2.trim();
    }

    public String getSpareV3() {
        return spareV3;
    }

    public void setSpareV3(String spareV3) {
        this.spareV3 = spareV3 == null ? null : spareV3.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }
}