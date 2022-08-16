package com.tx.dllogin.services;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.vo.FindLikeAllOrderVo;

public interface OrderService {


     //查询所有
    CommonResult  findALLOrder(Integer pageNum, Integer pagesize);

    //删除一个订单
    CommonResult deleteOrderOne (String orderId);


    CommonResult findLikeAllOrder(FindLikeAllOrderVo findLikeAllOrderVo);


}
