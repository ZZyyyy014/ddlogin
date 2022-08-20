package com.tx.dllogin.services.servicesimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.dao.*;
import com.tx.dllogin.findBean.FindAllShiroBean;
import com.tx.dllogin.findBean.updateShiroOneBean;
import com.tx.dllogin.model.Role;
import com.tx.dllogin.services.RoleService;
import com.tx.dllogin.vo.FindReAllShiroVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private FirmMapper firmMapper;

    @Autowired
    private DeptUserMapper deptUserMapper;

    @Autowired
    private  UserMapper userMapper;



    //多条件查询数据库
    @Override
    public CommonResult findAllShiro(FindAllShiroBean findAllShiroBean) {
        //开启分页插件
        PageHelper.startPage(findAllShiroBean.getPageNum(),findAllShiroBean.getPageSize());
       try {
           List<FindReAllShiroVo> findAllShopVos = roleMapper.FindAllShiro(
                   findAllShiroBean.getUserName(),
                   findAllShiroBean.getSparessV1(),
                   findAllShiroBean.getFirmName(),
                   findAllShiroBean.getDeptName());
           PageInfo<FindReAllShiroVo> findAllShopVoPageInfo = new PageInfo<>(findAllShopVos);
           return CommonResult.success(findAllShopVoPageInfo);
       }catch (Exception e){
           e.printStackTrace();
           return  CommonResult.error("系统出错,请重新尝试");
       }


    }

    @Override
    public CommonResult findAllRole() {
        List<Role> allrole = roleMapper.findAllrole();
        return CommonResult.success(allrole) ;
    }



    @Override
    public CommonResult updateShiroOne(updateShiroOneBean updateShiroOneBean) {
        //先根据名称 查询出 公司id  部门 id  权限id  后关联 。。。 (用户账号不会变)
        //根据名称查询 公司Id
        String firmId = firmMapper.findFirmIdByName(updateShiroOneBean.getFirmName());
           //根据名称 查询 部门Id
        String deptId = deptMapper.findDeptIdByName(updateShiroOneBean.getDeptName());
           //根据名称查询 RoleId
        String roleId = roleMapper.findRoleIdByName(updateShiroOneBean.getRoleRemark());
        //根据 用户账号 修改 关联 部门id+公司Id
        deptUserMapper.updateUserByName(updateShiroOneBean.getUserName(),deptId,firmId);
        //修改真实姓名（可以为空） +账号权限
        userMapper.updateUserReNameAndRoleByUserName(updateShiroOneBean.getUserName(),updateShiroOneBean.getSparessV1(),roleId);

        return CommonResult.success();
    }


}
