package com.tx.dllogin.dao;

import com.tx.dllogin.model.Dept;

import java.util.List;

public interface DeptMapper {
    int deleteByPrimaryKey(String deptId);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(String deptId);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    List<Dept> findAllDept();
}