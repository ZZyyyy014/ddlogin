package com.tx.dllogin.dao;

import com.tx.dllogin.model.User;
import com.tx.dllogin.vo.UserFindAllVo;
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
    Integer  findExitByUserName(@Param("userName") String userName);

    //根据 用户名称   删除多个用户  和绑定关系表
    void deleteUserList(@Param("lists") List<String> lists);
}