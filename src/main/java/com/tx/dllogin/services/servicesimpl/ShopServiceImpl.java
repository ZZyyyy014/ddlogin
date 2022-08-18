package com.tx.dllogin.services.servicesimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.dao.ShopMapper;
import com.tx.dllogin.model.Shop;
import com.tx.dllogin.services.ShopService;
import com.tx.dllogin.vo.FindAllShopVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    @Autowired
    public ShopMapper shopMapper;


    @Override//根据用户名查询所有 userName不是必填
    public CommonResult findAllShop(FindAllShopVo findAllShopVo) {
        Date date1 ;
        Date date2;
        List<Date> shopLoginDateList = findAllShopVo.getShopLoginDateList();
     if(shopLoginDateList!=null &&shopLoginDateList.size()!=0 ){
         date1=shopLoginDateList.get(0);
         date2=shopLoginDateList.get(1);
     }else {
         date1=null;
         date2=null;
     }
        PageHelper.startPage(findAllShopVo.getPageNum(),findAllShopVo.getPageSize());
        List<Shop> allShop = shopMapper.findAllShop(findAllShopVo.getUserName(),
                findAllShopVo.getShopUserName(),
                findAllShopVo.getShopIp(),date1,date2);
        PageInfo<Shop> shopPageInfo = new PageInfo<>(allShop);
        return CommonResult.success(shopPageInfo);
    }




    @Override
    public CommonResult updateShop(Shop shop) {
        try {
            //出去空格  防止空格 修改。。。。。。
            shop.setShopUserPasswrod(shop.getShopUserPasswrod().trim());
            shopMapper.updateShop(shop);
        }catch (Exception e){
            e.printStackTrace();
            return CommonResult.error("修改出错");
        }
        return CommonResult.success();
    }


}