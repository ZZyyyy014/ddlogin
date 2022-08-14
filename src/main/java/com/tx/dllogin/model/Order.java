package com.tx.dllogin.model;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private String orderId;

    private String orderNumber;

    private Date orderDate;

    private String spareV1;

    private String spareV2;

    private String spareV3;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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