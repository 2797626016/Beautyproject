package com.xixi.dao;

import com.xixi.pojo.ItemGroup;
import com.xixi.pojo.ItemGroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemGroupDAO {
    long countByExample(ItemGroupExample example);

    int deleteByExample(ItemGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemGroup record);

    int insertSelective(ItemGroup record);

    List<ItemGroup> selectByExample(ItemGroupExample example);

    ItemGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemGroup record, @Param("example") ItemGroupExample example);

    int updateByExample(@Param("record") ItemGroup record, @Param("example") ItemGroupExample example);

    int updateByPrimaryKeySelective(ItemGroup record);

    int updateByPrimaryKey(ItemGroup record);

    //存储map
   void setGroupAndItem(Map map);
}