package com.tx.dllogin.dao;

import com.tx.dllogin.model.DeptUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptUserMapper {
    int deleteByPrimaryKey(String deptUserId);

    int insert(DeptUser record);

    int insertSelective(DeptUser record);

    DeptUser selectByPrimaryKey(String deptUserId);

    int updateByPrimaryKeySelective(DeptUser record);

    int updateByPrimaryKey(DeptUser record);

    void  deleteUserList(List<String> list);

    //删除单个用户
    void  deleteUserOneByUserId(@Param("userId") String userId);
    //修改绑定关系
    void  updateUser(@Param("userId") String userId
            ,@Param("deptId") String deptId
            ,@Param("firmId") String firmId);

    //根据用户账号 修改 关联部门 公司
    void     updateUserByName(@Param("userName") String userName
            ,@Param("deptId") String deptId
            ,@Param("firmId") String firmId);



}