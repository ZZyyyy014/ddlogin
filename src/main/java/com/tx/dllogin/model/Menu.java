package com.tx.dllogin.model;

import lombok.Data;

@Data
public class Menu {
    private Integer menuId;

    private String menuName;

    private String menuParentId;

    private String menuUrl;

    private String parms;

    private String type;

    private String icon;

    private Integer orderNum;

    private String sparessV1;

    private String sparessV2;

    private String sparessV3;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId == null ? null : menuParentId.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getParms() {
        return parms;
    }

    public void setParms(String parms) {
        this.parms = parms == null ? null : parms.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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