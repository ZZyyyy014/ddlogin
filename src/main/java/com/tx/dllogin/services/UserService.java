package com.tx.dllogin.services;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.vo.AddUserVo;
import com.tx.dllogin.vo.UserFindAllVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    CommonResult login(String userName ,
                       String passWrod,
                       String captcha,
                       HttpServletRequest request);

    CommonResult findAllLogin(Integer pageNum,Integer pageSize);

    CommonResult  deleteOneUser(String userId);

    CommonResult  deleteListUser(List<String> userId);

    CommonResult updateUserFindAllVo(UserFindAllVo userFindAllVo);

    CommonResult  AddUser(AddUserVo addUserVo);

    //登录后查询 1 2级菜单
    CommonResult findLogRoter();




}
