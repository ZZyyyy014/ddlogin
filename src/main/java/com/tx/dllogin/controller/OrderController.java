package com.tx.dllogin.controller;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.services.OrderService;
import com.tx.dllogin.vo.FindLikeAllOrderVo;
import com.tx.dllogin.vo.InsertListOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "订单模块",tags = "订单模块")
@RestController
public class OrderController   extends BaseController{
    @Autowired
    private OrderService orderService;


    @GetMapping("/order/findALLOrder")
    @RequiresPermissions(value = {"order:find"})
    public CommonResult findALLOrder(Integer pageNum, Integer pageSize) {
        CommonResult allOrder = orderService.findALLOrder(pageNum, pageSize);
        return allOrder;
    }

    @ApiOperation("模糊查询所有订单")
    @PostMapping("/order/findLikeALLOrder")
    @RequiresPermissions(value = {"order:find"})
    public CommonResult findLikeALLOrder(@RequestBody FindLikeAllOrderVo findLikeAllOrderVo) {
        CommonResult allOrder = orderService.findLikeAllOrder(findLikeAllOrderVo);
        return allOrder;
    }

    @ApiOperation("批量添加订单")
    @PostMapping("/order/insertListOrder")
    @RequiresPermissions(value = {"order:create"})
    public CommonResult insertListOrder(@RequestBody List<InsertListOrder> listOrders) {
        return orderService.insetListOrder(listOrders);
    }



}
