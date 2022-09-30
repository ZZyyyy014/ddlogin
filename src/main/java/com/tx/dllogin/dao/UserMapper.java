package com.tx.dllogin.dao;

import com.tx.dllogin.model.User;
import com.tx.dllogin.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    //根据名称 查询 用户
    User  findUserByName(@Param("userName") String userName);

    //查询所有  用户信息
    List<UserFindAllVo> findAllUser();

    //判断是否存在该用户名
    String  findExitByUserName(@Param("userName") String userName);

    //根据 用户名称   删除多个用户  和绑定关系表
    void deleteUserList(@Param("lists") List<String> lists);

    String findPassWrodByUserName(@Param("userName") String userName);

    //shiro 授权时候查询 资源等
    FIndShiroUserVo  findShiroParms(@Param("userName")String userName);

    //前端根据session中存的 roleName 查询roleId   登录后跳转查询
    List<FindLogRoterVo> findLoginShiroParm(@Param("roleId")String roleId);

    //部门管理员  修改 删除用户  先要查询 是否是同一部门 同一公司
    findDeptAndFirmVo findDeIdAndFirId(@Param("userName")String userName);

    //根据List员工Id 查询部门 公司
     List<findDeptAndFirmVo> findDeIdAndFirIdList(@Param("list") List<String> list);

     //修改用户 真实姓名+权限Id
    void  updateUserReNameAndRoleByUserName(@Param("userName")String userName,
                                            @Param("sparessV1") String sparessV1,
                                            @Param("levelss") String levelss);

      //部门管理员  修改 删除用户  先要查询 是否是同一部门 同一公司
    findDeptAndFirmVo    findDeIdAndFirIdByUserId(@Param("userId")String userId);




    UserListByName findUserListByName(@Param("userName")String userName);


    //根据用户角色查询(超级管理员除外---只能查询同一公司 同一部门下的用户)     用户分页
    List<UserFindAllVo> findUserByUserList(@Param("levelss") String levelss
                                           ,@Param("deptId") String deptId
                                            ,@Param("firmId") String firmId);



}