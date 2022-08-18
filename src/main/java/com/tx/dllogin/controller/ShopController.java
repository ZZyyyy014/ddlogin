package com.tx.dllogin.controller;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.model.Shop;
import com.tx.dllogin.services.ShopService;
import com.tx.dllogin.vo.FindAllShopVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "店铺管理")
public class ShopController {
    @Autowired
    private ShopService shopService;


    @ApiOperation("查询所有店铺（根据用户账号可以为null）")
    @PostMapping("/shop/findAllShop")
    public CommonResult findAllShop(@RequestBody FindAllShopVo shopVo){
       return  shopService.findAllShop(shopVo);
    }


    @ApiOperation("修改单个店铺")
    @PostMapping("/shop/updateOneShop")
    public CommonResult updateOneShop(@RequestBody Shop shopVo){
        return  shopService.updateShop(shopVo);
    }


}
