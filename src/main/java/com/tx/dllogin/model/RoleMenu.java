package com.tx.dllogin.model;

public class RoleMenu {
    private Integer roleMenuId;

    private Integer roleId;

    private Integer menuId;

    private String sparessV1;

    private String sparessV2;

    private String sparessV3;

    public Integer getRoleMenuId() {
        return roleMenuId;
    }

    public void setRoleMenuId(Integer roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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