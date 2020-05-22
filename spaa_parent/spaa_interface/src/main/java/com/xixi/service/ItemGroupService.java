package com.xixi.service;

import com.xixi.enity.PageResult;
import com.xixi.enity.QueryPageBean;
import com.xixi.pojo.ItemGroup;
import com.xixi.pojo.ItemGroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemGroupService {

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

    //分页查询
    PageResult pageQuery(QueryPageBean queryPageBean);

    //增加 一个对象 一个数组
     void add(ItemGroup itemGroup,Integer[] spaitemIds);

     //修改
    void edit(ItemGroup itemGroup,Integer[] spaitemIds);

}
