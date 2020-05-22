package com.xixi.service;

import com.xixi.enity.PageResult;
import com.xixi.enity.QueryPageBean;
import com.xixi.pojo.SpaItem;
import com.xixi.pojo.SpaItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpaItemService  {

    long countByExample(SpaItemExample example);

    int deleteByExample(SpaItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpaItem record);

    int insertSelective(SpaItem record);

    List<SpaItem> selectByExample(SpaItemExample example);

    SpaItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpaItem record, @Param("example") SpaItemExample example);

    int updateByExample(@Param("record") SpaItem record, @Param("example") SpaItemExample example);

    int updateByPrimaryKeySelective(SpaItem record);

    int updateByPrimaryKey(SpaItem record);

    //分页查询
    PageResult pageQuery(QueryPageBean queryPageBean);
}
