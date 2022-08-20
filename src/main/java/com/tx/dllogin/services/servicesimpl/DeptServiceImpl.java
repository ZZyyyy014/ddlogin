package com.tx.dllogin.services.servicesimpl;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.dao.DeptMapper;
import com.tx.dllogin.model.Dept;
import com.tx.dllogin.services.DeptService;
import com.tx.dllogin.utill.GetUUIdUtil;
import com.tx.dllogin.utill.LogUtill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Transactional
@Service
@Slf4j
public class DeptServiceImpl implements DeptService {

    @Autowired
    private HttpServletRequest request;


    @Autowired
    private DeptMapper deptMapper;

    @Override
    public CommonResult findAllDept() {
        List<Dept> allDept = deptMapper.findAllDept();
        return CommonResult.success(allDept);
    }


    @Override
    public CommonResult createDept(String deptName) {
        String s = LogUtill.GetUserName(request);
        if(deptName==null||deptName==""){
            return  CommonResult.error("请添加部门名称");
        }
        String deptByName = deptMapper.findDeptByName(deptName);
        if(deptByName!=null){
            return  CommonResult.error("该部门存在");
        }

        Dept dept = new Dept();
        dept.setDeptId(GetUUIdUtil.getUUId());
        dept.setDeptName(deptName);
        deptMapper.insertSelective(dept);
        log.info("账号{} 添加部门(名称)成功{}",s,deptName);
        return CommonResult.success();
    }



}
