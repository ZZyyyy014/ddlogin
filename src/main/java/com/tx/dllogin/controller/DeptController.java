package com.tx.dllogin.controller;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.services.DeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class DeptController extends BaseController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/findAllDept")
    @RequiresPermissions(value = {"user:find"})
   public  CommonResult   findAllDept(){
        CommonResult allDept = deptService.findAllDept();
        return  allDept;
    }


    @GetMapping("/dept/createDept")
    @RequiresPermissions(value = {"user:create"})
    public  CommonResult createDept(String createdeptName){
        CommonResult allDept = deptService.createDept(createdeptName);
        return  allDept;
    }




}
