package com.tx.dllogin.services;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.findBean.DeleteShopeOneBean;
import com.tx.dllogin.model.Shop;
import com.tx.dllogin.vo.FindAllShopVo;

public interface ShopService {


    CommonResult findAllShop(FindAllShopVo findAllShopVo);

    CommonResult updateShop(Shop shop);

   CommonResult deleteShopOne(DeleteShopeOneBean deleteShopeOneBean);

   CommonResult  insertShopOne(Shop shop);




}
