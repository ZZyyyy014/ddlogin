package com.tx.dllogin.model;

import lombok.Data;

@Data
public class Dept {
    private String deptId;

    private String deptName;

    private String deptSuperId;

    private String spareV1;

    private String spareV2;

    private String spareV3;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getDeptSuperId() {
        return deptSuperId;
    }

    public void setDeptSuperId(String deptSuperId) {
        this.deptSuperId = deptSuperId == null ? null : deptSuperId.trim();
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