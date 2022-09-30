package com.tx.dllogin.dao;

import com.tx.dllogin.model.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ShopMapper {
    int deleteByPrimaryKey(Integer shopId);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Integer shopId);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    //条件查询 根据用户账号 发现所有的店铺
    List<Shop> findAllShop(@Param("userName") String userName,
                           @Param("shopUserName") String shopUserName,
                           @Param("shopIp") String shopIp,
                           @Param("fristDate")Date fristDate,
                           @Param("endDate") Date endDate,
                           @Param("levelss")String levelss ,
                           @Param("firmId")String  firmId,
                           @Param("userTypess") String userTypess
    );

    // 查询店铺的时候   如果是创建店铺账号  把该账号所在的公司加上去
    void  updateAllShop(String userName,String  firmId);


    Shop  findShopByShopName(@Param("shopName")String shopName);

    Shop  findShopByNameAndPassWord(@Param("shopName") String shopName,
                                    @Param("passwrod") String passwrod);


}