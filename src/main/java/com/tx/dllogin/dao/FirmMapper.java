package com.tx.dllogin.dao;

import com.tx.dllogin.model.Firm;

public interface FirmMapper {
    int deleteByPrimaryKey(String firmId);

    int insert(Firm record);

    int insertSelective(Firm record);

    Firm selectByPrimaryKey(String firmId);

    int updateByPrimaryKeySelective(Firm record);

    int updateByPrimaryKey(Firm record);
}