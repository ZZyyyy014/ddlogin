package com.tx.dllogin.controller;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.services.DeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class DeptController extends BaseController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private HttpServletRequest request;


    @GetMapping("/dept/findAllDept")
    @RequiresPermissions(value = {"user:find"})
   public  CommonResult   findAllDept(){
        CommonResult allDept = deptService.findAllDept();
        return  allDept;
    }


    //创建部门
    @GetMapping("/dept/createDept")
    @RequiresPermissions(value = {"user:create"})
    public  CommonResult createDept( @RequestParam ("createdeptName") String createdeptName){
        String roles = request.getSession().getAttribute("roles").toString();
       if("admin_all".equals(roles)){
           CommonResult allDept = deptService.createDept(createdeptName);
           return  allDept;
       }else {
           return CommonResult.error("请联系超级管理员添加");
       }
    }

}
