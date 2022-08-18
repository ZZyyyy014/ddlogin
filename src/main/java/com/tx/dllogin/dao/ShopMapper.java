package com.tx.dllogin.dao;

import com.tx.dllogin.model.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ShopMapper {
    int deleteByPrimaryKey(String shopId);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(String shopId);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);


    void  updateShop(Shop shop);

    //条件查询 根据用户账号 发现所有的店铺
    List<Shop> findAllShop(@Param("userName") String userName,
                           @Param("shopUserName") String shopUserName,
                           @Param("shopIp") String shopIp,
                           @Param("fristDate")Date fristDate,
                           @Param("endDate")Date endDate
    );



}