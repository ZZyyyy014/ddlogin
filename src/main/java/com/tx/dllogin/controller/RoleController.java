package com.tx.dllogin.controller;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.findBean.FindAllShiroBean;
import com.tx.dllogin.findBean.updateShiroOneBean;
import com.tx.dllogin.services.RoleService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "权限管理")
public class RoleController extends BaseController{
    @Autowired
    private RoleService roleService;


        @PostMapping("/shiro/findAllShrio")
        @RequiresPermissions({"shiro:find"})
       public CommonResult findAllShrio(@RequestBody FindAllShiroBean findAllShiroBean){
           return  roleService.findAllShiro(findAllShiroBean);
       }


       @PostMapping("/shiro/updateShiroOne")
       @RequiresPermissions({"shiro:update"})
       public CommonResult updateShiroOne(@RequestBody  updateShiroOneBean updateShiroOneBean){
           roleService.updateShiroOne(updateShiroOneBean);
            return CommonResult.success();
       }


       @GetMapping("/shiro/findALLRoleList")
       @RequiresPermissions({"shiro:find"})
       public  CommonResult findALLRoleList(){
           CommonResult allShiro = roleService.findAllRole();
           return allShiro;
       }






}
