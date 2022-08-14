package com.tx.dllogin.controller;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.services.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/findAllDept")
   public  CommonResult   findAllDept(){
        CommonResult allDept = deptService.findAllDept();
        return  allDept;
    }


}
