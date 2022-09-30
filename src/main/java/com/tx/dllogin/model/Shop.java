package com.tx.dllogin.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    private Integer shopId;

    private String shopUserName;

    private String shopUserPasswrod;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date shopLoginDate;

    private String shopIp;

    private String shopAuthorization;

    private String sparessV1;

    private String sparessV2;

    private String sparessV4;

    private String sparessV3;

    private String returnCookie;

    private String shopPhone;

    private String shopRemarkss;

    private String userTypess;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopUserName() {
        return shopUserName;
    }

    public void setShopUserName(String shopUserName) {
        this.shopUserName = shopUserName == null ? null : shopUserName.trim();
    }

    public String getShopUserPasswrod() {
        return shopUserPasswrod;
    }

    public void setShopUserPasswrod(String shopUserPasswrod) {
        this.shopUserPasswrod = shopUserPasswrod == null ? null : shopUserPasswrod.trim();
    }

    public Date getShopLoginDate() {
        return shopLoginDate;
    }

    public void setShopLoginDate(Date shopLoginDate) {
        this.shopLoginDate = shopLoginDate;
    }

    public String getShopIp() {
        return shopIp;
    }

    public void setShopIp(String shopIp) {
        this.shopIp = shopIp == null ? null : shopIp.trim();
    }

    public String getShopAuthorization() {
        return shopAuthorization;
    }

    public void setShopAuthorization(String shopAuthorization) {
        this.shopAuthorization = shopAuthorization == null ? null : shopAuthorization.trim();
    }

    public String getSparessV1() {
        return sparessV1;
    }

    public void setSparessV1(String sparessV1) {
        this.sparessV1 = sparessV1 == null ? null : sparessV1.trim();
    }

    public String getSparessV2() {
        return sparessV2;
    }

    public void setSparessV2(String sparessV2) {
        this.sparessV2 = sparessV2 == null ? null : sparessV2.trim();
    }

    public String getSparessV4() {
        return sparessV4;
    }

    public void setSparessV4(String sparessV4) {
        this.sparessV4 = sparessV4 == null ? null : sparessV4.trim();
    }

    public String getSparessV3() {
        return sparessV3;
    }

    public void setSparessV3(String sparessV3) {
        this.sparessV3 = sparessV3 == null ? null : sparessV3.trim();
    }

    public String getReturnCookie() {
        return returnCookie;
    }

    public void setReturnCookie(String returnCookie) {
        this.returnCookie = returnCookie == null ? null : returnCookie.trim();
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone == null ? null : shopPhone.trim();
    }

    public String getShopRemarkss() {
        return shopRemarkss;
    }

    public void setShopRemarkss(String shopRemarkss) {
        this.shopRemarkss = shopRemarkss == null ? null : shopRemarkss.trim();
    }

    public String getUserTypess() {
        return userTypess;
    }

    public void setUserTypess(String userTypess) {
        this.userTypess = userTypess == null ? null : userTypess.trim();
    }
}