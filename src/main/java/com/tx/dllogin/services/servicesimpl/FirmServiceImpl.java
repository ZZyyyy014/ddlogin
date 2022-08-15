package com.tx.dllogin.services.servicesimpl;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.dao.FirmMapper;
import com.tx.dllogin.model.Firm;
import com.tx.dllogin.services.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FirmServiceImpl implements FirmService {

    @Autowired
    private FirmMapper firmMapper;


    @Override
    public CommonResult findAllFirm() {
        List<Firm> allFrims = firmMapper.findAllFrims();
        return CommonResult.success(allFrims);
    }



}
