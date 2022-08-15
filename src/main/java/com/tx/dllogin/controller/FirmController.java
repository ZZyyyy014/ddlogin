package com.tx.dllogin.controller;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.services.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FirmController {

    @Autowired
    public FirmService firmService;

    @GetMapping("/firm/findAllFirm")
    public CommonResult  findAllFirm(){
        CommonResult allFirm = firmService.findAllFirm();
        return allFirm;
    }





}
