package com.tx.dllogin.services.servicesimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.dao.OrderMapper;
import com.tx.dllogin.model.Order;
import com.tx.dllogin.services.OrderService;
import com.tx.dllogin.utill.LogUtill;
import com.tx.dllogin.vo.FindLikeAllOrderVo;
import com.tx.dllogin.vo.InsertListOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    public OrderMapper orderMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public CommonResult findALLOrder(Integer pageNum, Integer pagesize) {
        //pageNum 第几页   pagesize 每页多少条
        PageHelper.startPage(pageNum, pagesize);
        List<Order> allOrder = orderMapper.findAllOrder();
        PageInfo<Order> orderPageInfo = new PageInfo<>(allOrder);
        return CommonResult.success(orderPageInfo);
    }


    @Override
    public CommonResult deleteOrderOne(String orderId) {
        String s = LogUtill.GetUserName(request);
        try {
            orderMapper.deleteOrderById(orderId);
            log.info("账号{}删除{}订单Id成功",s,orderId);
        } catch (Exception e) {
            return CommonResult.error("删除单个失败");
        }
        return CommonResult.success();
    }


    @Override
    public CommonResult findLikeAllOrder(FindLikeAllOrderVo findLikeAllOrderVo) {
        Date date1;
        Date date2;
        if (findLikeAllOrderVo.getOrderdateList() != null && findLikeAllOrderVo.getOrderdateList().size() != 0) {
            date1 = findLikeAllOrderVo.getOrderdateList().get(0);
            date2 = findLikeAllOrderVo.getOrderdateList().get(1);
        } else {
            date1 = null;
            date2 = null;
        }
        //添加查询 条件
        try {
            PageHelper.startPage(findLikeAllOrderVo.getLiekpageNum(), findLikeAllOrderVo.getLikepageSize());
            List<Order> likeAllOrder = orderMapper.findLikeAllOrder(date1,
                    date2,
                    findLikeAllOrderVo.getOrderNumber(),
                    findLikeAllOrderVo.getCustomerTel(),
                    findLikeAllOrderVo.getExpressageCode(),
                    findLikeAllOrderVo.getCustomerName(),
                    findLikeAllOrderVo.getShopUserName());
            PageInfo<Order> orderPageInfo = new PageInfo<>(likeAllOrder);
            return CommonResult.success(orderPageInfo);
        } catch (Exception e) {
                e.printStackTrace();
                return CommonResult.error("模糊查询订单失败");
        }

    }

    @Transactional
    @Override
    public CommonResult insetListOrder(List<InsertListOrder> listOrders) {
          try{
              if(listOrders.size()==0){
                  return CommonResult.error("请添加参数");
              }
              orderMapper.insertListOrder(listOrders);
              return CommonResult.success();
          }catch (Exception e){
              e.printStackTrace();
              return CommonResult.error("添加失败");
          }
    }

}