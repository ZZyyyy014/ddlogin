package com.tx.dllogin.services.servicesimpl;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.dao.FirmMapper;
import com.tx.dllogin.model.Firm;
import com.tx.dllogin.services.FirmService;
import com.tx.dllogin.utill.GetUUIdUtil;
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


    @Override
    public CommonResult addFirm(String firmName) {
        String buyFirmName = firmMapper.findBuyFirmName(firmName);
        if (buyFirmName!=null)return CommonResult.error("该公司已经存在！！！");
      try {
          Firm firm = new Firm();
          firm.setFirmId(GetUUIdUtil.getUUId());
          firm.setFirmName(firmName);
          firmMapper.insertSelective(firm);
      }catch (Exception e){
          e.printStackTrace();
          return  CommonResult.error("添加失败");
      }
        return CommonResult.success();
    }


}
