package com.tx.dllogin.vo;


import lombok.Data;

import java.util.List;

//
@Data
public class findUserDept {


    //部门表 公司id
    private String spareV1;

    //部门表 公司名称
    private String spareV2;

    private List<UserFindAllVo> listUser;


}
