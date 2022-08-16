package com.tx.dllogin.controller;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.services.OrderService;
import com.tx.dllogin.vo.FindLikeAllOrderVo;
import com.tx.dllogin.vo.InsertListOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;


    @GetMapping("/order/findALLOrder")
    public CommonResult findALLOrder(Integer pageNum, Integer pageSize) {
        CommonResult allOrder = orderService.findALLOrder(pageNum, pageSize);
        return allOrder;
    }

    @PostMapping("/order/findLikeALLOrder")
    public CommonResult findLikeALLOrder(@RequestBody FindLikeAllOrderVo findLikeAllOrderVo) {
        CommonResult allOrder = orderService.findLikeAllOrder(findLikeAllOrderVo);
        return allOrder;
    }

    @PostMapping("/order/insertListOrder")
    public CommonResult insertListOrder(@RequestBody List<InsertListOrder> listOrders) {
        return orderService.insetListOrder(listOrders);
    }



}
