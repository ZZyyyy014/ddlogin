package com.tx.dllogin.model;

import lombok.Data;

@Data
public class DeptUser {
    private String deptUserId;

    private String deptId;

    private String userId;

    private String spareV1;

    private String spareV2;

    private String spareV3;

    public String getDeptUserId() {
        return deptUserId;
    }

    public void setDeptUserId(String deptUserId) {
        this.deptUserId = deptUserId == null ? null : deptUserId.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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