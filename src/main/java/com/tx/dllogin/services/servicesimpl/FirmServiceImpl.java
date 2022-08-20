package com.tx.dllogin.services.servicesimpl;

import com.tx.dllogin.common.CommonResult;
import com.tx.dllogin.dao.FirmMapper;
import com.tx.dllogin.model.Firm;
import com.tx.dllogin.services.FirmService;
import com.tx.dllogin.utill.GetUUIdUtil;
import com.tx.dllogin.utill.LogUtill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional
@Slf4j
public class FirmServiceImpl implements FirmService {

    @Autowired
    private FirmMapper firmMapper;

    @Autowired
    private HttpServletRequest request;


    @Override
    public CommonResult findAllFirm() {
        List<Firm> allFrims = firmMapper.findAllFrims();
        return CommonResult.success(allFrims);
    }


    @Override
    public CommonResult addFirm(String firmName) {
        String buyFirmName = firmMapper.findBuyFirmName(firmName);
        if (buyFirmName==null||buyFirmName=="") return CommonResult.error("请填写公司名称！！！");
        if (buyFirmName!=null)return CommonResult.error("该公司已经存在！！！");
        String s = LogUtill.GetUserName(request);
        try {
          Firm firm = new Firm();
          firm.setFirmId(GetUUIdUtil.getUUId());
          firm.setFirmName(firmName);
          firmMapper.insertSelective(firm);

          log.info("账号{}添加公司{}成功",s,firm);
      }catch (Exception e){
          e.printStackTrace();
          log.error("账号{}添加公司失败",s);
          return  CommonResult.error("添加失败");
      }
        return CommonResult.success();
    }


}
