package com.tx.dllogin.controller;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.services.FirmService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FirmController  extends BaseController {

    @Autowired
    public FirmService firmService;

    @GetMapping("/firm/findAllFirm")
    @RequiresPermissions(value = {"user:find"})
    public CommonResult  findAllFirm(){
        CommonResult allFirm = firmService.findAllFirm();
        return allFirm;
    }



    @GetMapping("/firm/addFirm")
    @RequiresPermissions(value = {"user:create"})
    public  CommonResult addFirm(@Param("firmName") String firmName){
        CommonResult commonResult = firmService.addFirm(firmName);
        return commonResult;
    }




}
