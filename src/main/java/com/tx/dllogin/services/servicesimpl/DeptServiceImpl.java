package com.tx.dllogin.services.servicesimpl;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.dao.DeptMapper;
import com.tx.dllogin.model.Dept;
import com.tx.dllogin.services.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public CommonResult findAllDept() {
        List<Dept> allDept = deptMapper.findAllDept();
        return CommonResult.success(allDept);
    }
}
