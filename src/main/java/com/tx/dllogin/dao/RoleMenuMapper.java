package com.tx.dllogin.dao;

import com.tx.dllogin.model.RoleMenu;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(Integer roleMenuId);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    RoleMenu selectByPrimaryKey(Integer roleMenuId);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);




}