package com.tx.dllogin.services;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.vo.FindLikeAllOrderVo;
import com.tx.dllogin.vo.InsertListOrder;

import java.util.List;

public interface OrderService {


     //查询所有
    CommonResult  findALLOrder(Integer pageNum, Integer pagesize);

    //删除一个订单
    CommonResult deleteOrderOne (String orderId);


    CommonResult findLikeAllOrder(FindLikeAllOrderVo findLikeAllOrderVo);

    CommonResult insetListOrder(List<InsertListOrder> listOrders);

}
