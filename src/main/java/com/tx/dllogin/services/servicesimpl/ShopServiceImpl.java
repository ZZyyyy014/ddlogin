package com.tx.dllogin.services.servicesimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.dao.ShopMapper;
import com.tx.dllogin.dao.UserMapper;
import com.tx.dllogin.findBean.DeleteShopeOneBean;
import com.tx.dllogin.model.Shop;
import com.tx.dllogin.services.ShopService;
import com.tx.dllogin.utill.LogUtill;
import com.tx.dllogin.vo.FindAllShopVo;
import com.tx.dllogin.vo.findDeptAndFirmVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserMapper userMapper;

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
        //当前登录的用户Id
        String userName = LogUtill.GetUserName(request);
        //获取当前 身份
        String roles = request.getSession().getAttribute("roles").toString();
        //当前为部门管理员
        if("admin_min".equals(roles)){
            //部门管理员 的公司Id  deptId
            findDeptAndFirmVo d1 = userMapper.findDeIdAndFirId(userName);
            //被修改 店铺绑定的userId
            String userId = shop.getSparessV1();
            //查询 绑定用户dept  id
            findDeptAndFirmVo d2 = userMapper.findDeIdAndFirIdByUserId(userId);
            if(!d1.getFirmId().equals(d2.getFirmId())&&!d1.getDeptId().equals(d2.getDeptId())){
                 return CommonResult.error("不是同一公司同一部门不能修改");
            }
        }

        try {
            //出去空格  防止空格 修改。。。。。。+
            if (shop.getShopUserPasswrod()!=null) {
                shop.setShopUserPasswrod(shop.getShopUserPasswrod().trim());
            }
            shopMapper.updateByPrimaryKeySelective(shop);
            log.info("账号{} 修改店铺{}成功",userName,shop);
        }catch (Exception e){
            e.printStackTrace();
            log.info("账号{} 修改店铺{}失败",userName,shop);
            return CommonResult.error("修改出错");
        }
        return CommonResult.success();
    }

    @Override
    public CommonResult deleteShopOne(DeleteShopeOneBean deleteShopeOneBean) {
        //当前登录的用户Id
        String userName = LogUtill.GetUserName(request);
        //获取当前 身份
        String roles = request.getSession().getAttribute("roles").toString();
        //当前为部门管理员
        if("admin_min".equals(roles)){
            //部门管理员 的公司Id  deptId
            findDeptAndFirmVo d1 = userMapper.findDeIdAndFirId(userName);
            //被修改 店铺绑定的userId
            String userId = deleteShopeOneBean.getSparessV1();
            //查询 绑定用户dept  id
            findDeptAndFirmVo d2 = userMapper.findDeIdAndFirIdByUserId(userId);
            if(!d1.getFirmId().equals(d2.getFirmId())&&!d1.getDeptId().equals(d2.getDeptId())){
                return CommonResult.error("不是同一公司同一部门不能修改");
            }
        }
         shopMapper.deleteByPrimaryKey(deleteShopeOneBean.getShopId());

        return CommonResult.success();
    }


}
