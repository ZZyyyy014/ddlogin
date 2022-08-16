package com.tx.dllogin.model;

import lombok.Data;

@Data
public class DeptUser {
    private String deptUserId;

    private String deptId;

    private String userId;

    private String sparessV1;

    private String sparessV2;

    private String sparessV3;

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