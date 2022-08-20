package com.tx.dllogin.services;


import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.findBean.FindAllShiroBean;
import com.tx.dllogin.findBean.updateShiroOneBean;

public interface RoleService {


    //根据 多条件 查询数据库
    CommonResult   findAllShiro(FindAllShiroBean findAllShiroBean);

    CommonResult findAllRole();

    CommonResult updateShiroOne(updateShiroOneBean updateShiroOneBean);

}
