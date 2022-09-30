package com.tx.dllogin.services.servicesimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.dao.OrderMapper;
import com.tx.dllogin.dao.UserMapper;
import com.tx.dllogin.model.Order;
import com.tx.dllogin.services.OrderService;
import com.tx.dllogin.utill.LogUtill;
import com.tx.dllogin.vo.FindLikeAllOrderVo;
import com.tx.dllogin.vo.InsertListOrder;
import com.tx.dllogin.vo.UserListByName;
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

    @Autowired
    private UserMapper userMapper;

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
        Order order = orderMapper.selectByPrimaryKey(orderId);
        try {
            orderMapper.deleteOrderById(orderId);
            log.info("账号{}删除订单{}成功",s,order);
        } catch (Exception e) {
            log.error("账号{}删除订单{}失败",s,order);
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
            //登录后 获取到用户名称
            String userNameOne = request.getSession().getAttribute("userNameOne").toString();
            //根据用户名称 查询该用户 所在公司Id  部门Id  角色Id
            UserListByName userListByName = userMapper.findUserListByName(userNameOne);

            PageHelper.startPage(findLikeAllOrderVo.getLiekpageNum(), findLikeAllOrderVo.getLikepageSize());
            List<Order> likeAllOrder = orderMapper.findLikeAllOrder(date1,
                    date2,
                    findLikeAllOrderVo.getOrderNumber(),
                    findLikeAllOrderVo.getCustomerTel(),
                    findLikeAllOrderVo.getExpressageCode(),
                    findLikeAllOrderVo.getCustomerName(),
                    findLikeAllOrderVo.getShopUserName(),
                    userListByName.getLevelss(),
                    userListByName.getFirmId());
            PageInfo<Order> orderPageInfo = new PageInfo<>(likeAllOrder);
            return CommonResult.success(orderPageInfo);
        } catch (Exception e) {
                e.printStackTrace();
                return CommonResult.error("模糊查询订单失败");
        }

    }


    @Transactional
    @Override
    public CommonResult insetListOrder(InsertListOrder listOrders) {
          try{
              String orderNumber = listOrders.getOrderNumber();
              Integer byId = orderMapper.findById(orderNumber);
            if (byId==null){
                //登录后 获取到用户名称
                String userNameOne = request.getSession().getAttribute("userNameOne").toString();

                //根据用户名称 查询该用户 所在公司Id  部门Id  角色Id
                UserListByName userListByName = userMapper.findUserListByName(userNameOne);
                Order order = new Order();
                order.setOrderNumber(listOrders.getOrderNumber());
                order.setCustomername(listOrders.getCustomerName());
                order.setCustomertel(listOrders.getCustomerTel());
                order.setExpressagecode(listOrders.getExpressageCode());
                order.setOrderDate(listOrders.getOrderDate());
                order.setShopUserName(listOrders.getShopUserName());
                order.setSparessV1(listOrders.getSparessV1());
                order.setSparessV2(userListByName.getFirmId());
                order.setSparessV3(listOrders.getSparessV3());
                orderMapper.insertSelective(order);
            }else {
                return CommonResult.error("该订单已经存在");
            }
              //  orderMapper.insertListOrder(listOrders);
              return CommonResult.success();
          } catch (Exception e){
              e.printStackTrace();
              return CommonResult.error("添加失败,请重试");
          }
    }

}