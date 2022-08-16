package com.tx.dllogin.services;

import com.tx.dllogin.common.CommonResult;

public interface FirmService {

    CommonResult findAllFirm();

    CommonResult addFirm(String firmName);

}
