package com.tx.dllogin.dao;

import com.tx.dllogin.model.Firm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FirmMapper {
    int deleteByPrimaryKey(String firmId);

    int insert(Firm record);

    int insertSelective(Firm record);

    Firm selectByPrimaryKey(String firmId);

    int updateByPrimaryKeySelective(Firm record);

    int updateByPrimaryKey(Firm record);


    //查询所有的公司
    List<Firm> findAllFrims();

    //查询公司 名称是否存在
    String  findBuyFirmName(@Param("firmName") String firmName);

    String findFirmIdByName(@Param("firmName") String firmName);

}