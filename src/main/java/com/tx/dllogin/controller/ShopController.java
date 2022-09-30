package com.tx.dllogin.controller;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.findBean.DeleteShopeOneBean;
import com.tx.dllogin.model.Shop;
import com.tx.dllogin.services.ShopService;
import com.tx.dllogin.vo.FindAllShopVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "店铺管理")
public class ShopController  extends BaseController {
    @Autowired
    private ShopService shopService;


    @ApiOperation("查询所有店铺（根据用户账号可以为null）")
    @PostMapping("/shop/findAllShop")
    @RequiresPermissions(value = {"shop:find"})
    public CommonResult findAllShop(@RequestBody FindAllShopVo shopVo){
       return  shopService.findAllShop(shopVo);
    }

    @ApiOperation("修改单个店铺")
    @PostMapping("/shop/updateOneShop")
    @RequiresPermissions(value = {"order:update"})
    public CommonResult updateOneShop(@RequestBody Shop shopVo){
        return  shopService.updateShop(shopVo);
    }


    @ApiOperation("删除单个店铺")
    @PostMapping("/shop/deteleOneShop")
    @RequiresPermissions(value = {"order:delete"})
    public CommonResult deteleOneShop(@RequestBody DeleteShopeOneBean deleteShopeOneBean){
        System.out.println(deleteShopeOneBean);
        return  shopService.deleteShopOne(deleteShopeOneBean);
    }



    @PostMapping("/shop/insetShopOne")
    @ApiOperation("添加单个店铺")
    @RequiresPermissions(value = {"shop:create"})
    public CommonResult insetShopOne(@RequestBody Shop shop){
        return  shopService.insertShopOne(shop);
    }




}
