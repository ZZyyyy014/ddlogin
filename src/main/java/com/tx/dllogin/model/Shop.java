package com.tx.dllogin.model;

import lombok.Data;

import java.util.Date;

@Data
public class Shop {
    private String shopId;

    private String shopUserName;

    private String shopUserPasswrod;

    private Date shopLoginDate;

    private String shopIp;

    private String shopAuthorization;

    private String sparessV1;

    private String sparessV2;

    private String sparessV3;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
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

    public String getSparessV3() {
        return sparessV3;
    }

    public void setSparessV3(String sparessV3) {
        this.sparessV3 = sparessV3 == null ? null : sparessV3.trim();
    }
}