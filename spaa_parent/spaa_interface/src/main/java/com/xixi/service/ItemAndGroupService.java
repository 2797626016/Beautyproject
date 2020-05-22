package com.xixi.service;

import com.xixi.pojo.ItemAndGroup;
import com.xixi.pojo.ItemAndGroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemAndGroupService {
    long countByExample(ItemAndGroupExample example);

    int deleteByExample(ItemAndGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemAndGroup record);

    int insertSelective(ItemAndGroup record);

    List<ItemAndGroup> selectByExample(ItemAndGroupExample example);

    ItemAndGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemAndGroup record, @Param("example") ItemAndGroupExample example);

    int updateByExample(@Param("record") ItemAndGroup record, @Param("example") ItemAndGroupExample example);

    int updateByPrimaryKeySelective(ItemAndGroup record);

    int updateByPrimaryKey(ItemAndGroup record);
}
