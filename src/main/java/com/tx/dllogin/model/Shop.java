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

    private String spareV1;

    private String spareV2;

    private String spareV3;

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
}