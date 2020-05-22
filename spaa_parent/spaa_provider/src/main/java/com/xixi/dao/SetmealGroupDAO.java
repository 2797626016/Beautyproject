package com.xixi.dao;

import com.xixi.pojo.SetmealGroup;
import com.xixi.pojo.SetmealGroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetmealGroupDAO {
    long countByExample(SetmealGroupExample example);

    int deleteByExample(SetmealGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SetmealGroup record);

    int insertSelective(SetmealGroup record);

    List<SetmealGroup> selectByExample(SetmealGroupExample example);

    SetmealGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SetmealGroup record, @Param("example") SetmealGroupExample example);

    int updateByExample(@Param("record") SetmealGroup record, @Param("example") SetmealGroupExample example);

    int updateByPrimaryKeySelective(SetmealGroup record);

    int updateByPrimaryKey(SetmealGroup record);
}