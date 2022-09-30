package com.tx.dllogin.dao;

import com.tx.dllogin.model.Order;
import com.tx.dllogin.vo.InsertListOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);


    //查询所有的  订单
    List<Order> findAllOrder();

    void deleteOrderById(@Param("orderId") String orderId);

    //根据条件,模糊查询
    List<Order>  findLikeAllOrder(@Param("dateFrist") Date dateFrist,
                                  @Param("dateEnd")Date dateEnd,
                                  @Param("orderNumber")String orderNumber,
                                  @Param("customerTel")String customerTel,
                                  @Param("expressageCode")String expressageCode,
                                  @Param("customerName")String customerName,
                                  @Param("shopUserName")String shopUserName,
                                  @Param("levelss")String levelss,
                                  @Param("firmId")String firmId);


    void   insertListOrder(List<InsertListOrder> list );

    // 根据订单号  查询 是否存在 该订单
    Integer  findById(@Param("orderNuber") String orderNuber);


}