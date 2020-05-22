package com.xixi.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.xixi.enity.PageResult;
import com.xixi.enity.QueryPageBean;
import com.xixi.enity.Result;
import com.xixi.pojo.ItemAndGroup;
import com.xixi.pojo.ItemAndGroupExample;
import com.xixi.pojo.ItemGroup;
import com.xixi.service.ItemAndGroupService;
import com.xixi.service.ItemGroupService;
import com.xixi.service.SpaItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/itemgroup")
public class ItemGroupController {


    @Reference
    private ItemGroupService itemGroupService;

    @Reference
    private ItemAndGroupService itemAndGroupService;

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = itemGroupService.pageQuery(queryPageBean);
        return pageResult;

    }

    //新增项目
    @RequestMapping("/add")
    public Result add(@RequestBody ItemGroup itemGroup, Integer[] spaitemIds){
        itemGroupService.add(itemGroup,spaitemIds);
        //使用
        return new Result(true,"成功");
    }

    //根据id查找对象
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            ItemGroup itemGroup = itemGroupService.selectByPrimaryKey(id);
            return new Result(true,"成功",itemGroup);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"失败");
        }

    }

    //修改回显
    @RequestMapping("/findspaitemIdsByitemgroupId")
    public Result findspaitemIdsByitemgroupId(Integer id){
        ItemAndGroupExample example = new ItemAndGroupExample();
        ItemAndGroupExample.Criteria criteria = example.createCriteria();
        criteria.andItemGroupIdEqualTo(id);

        List<Integer> spaItemsIds = null;
        try{
            List<ItemAndGroup> items = itemAndGroupService.selectByExample(example);
            spaItemsIds = items.stream().map(ItemAndGroup::getSpaItemId).collect(Collectors.toList());

            return new Result(true,"成功",spaItemsIds);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }

    //修改
    @RequestMapping("/edit")
    public Result  edit(@RequestBody ItemGroup itemGroup, Integer[] spaitemIds){
        //修改
        //删除旧的关联
        //重新建立关系
        try{
            itemGroupService.edit(itemGroup,spaitemIds);
            return new Result(true,"成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"失败");
        }

    }

    @RequestMapping("/findAll")
    public Result  findAll(){

        try{
            List<ItemGroup> itemGroups = itemGroupService.selectByExample(null);
            return new Result(true,"成功",itemGroups);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"失败");
        }

    }

 }
