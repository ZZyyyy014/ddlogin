package com.tx.dllogin.dao;

import com.tx.dllogin.model.Role;
import com.tx.dllogin.vo.FindReAllShiroVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);



    List<Role>  findAllrole();

    //权限首页面  查询信息
    List<FindReAllShiroVo>  FindAllShiro(@Param("userName") String userName,
                                                   @Param("sparessV1") String sparessV1,
                                                   @Param("firmName") String firmName,
                                                   @Param("deptName")    String deptName);

    //根据 roleRemark名称查询 roleId
    String findRoleIdByName(@Param("roleRemark") String roleRemark);


}