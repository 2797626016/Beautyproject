package com.xixi.dao;

import com.xixi.pojo.Setmeal;
import com.xixi.pojo.SetmealExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetmealDAO {
    long countByExample(SetmealExample example);

    int deleteByExample(SetmealExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Setmeal record);

    int insertSelective(Setmeal record);

    List<Setmeal> selectByExample(SetmealExample example);

    Setmeal selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Setmeal record, @Param("example") SetmealExample example);

    int updateByExample(@Param("record") Setmeal record, @Param("example") SetmealExample example);

    int updateByPrimaryKeySelective(Setmeal record);

    int updateByPrimaryKey(Setmeal record);
}