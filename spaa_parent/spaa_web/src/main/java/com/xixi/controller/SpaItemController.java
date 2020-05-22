package com.xixi.controller;



import com.alibaba.dubbo.config.annotation.Reference;
import com.xixi.enity.PageResult;
import com.xixi.enity.QueryPageBean;
import com.xixi.enity.Result;
import com.xixi.pojo.SpaItem;
import com.xixi.pojo.SpaItemExample;
import com.xixi.service.SpaItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spaitem")
public class SpaItemController {

    @Reference
    private SpaItemService spaItemService;


    //新增美容护理项目
    @RequestMapping("/add")
    public Result add(@RequestBody SpaItem spaItem){
        // 常理 , 调用service 层
        System.out.println("跳转"+spaItem);

        if (spaItem.equals("")&&null==spaItem){
            System.out.println("空");
        }
        try {
            spaItemService.insertSelective(spaItem);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, "添加项目失败");
        }

        return new Result(true,"添加项目成功");
    }


    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){

       PageResult pageResult = spaItemService.pageQuery(queryPageBean);

       return pageResult;

    }

    //根据id查询 修改需要
    @RequestMapping("/findById")
    public Result findById(Integer id){

        try {
            SpaItem spaItem = spaItemService.selectByPrimaryKey(id);
            return new Result(true, "查询成功",spaItem);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, "查询失败");
        }
    }

//修该
    @RequestMapping("/edit")
    public Result edit(@RequestBody SpaItem spaItem){
        SpaItemExample example = new SpaItemExample();
        SpaItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(spaItem.getId());


        try {
            spaItemService.updateByExampleSelective(spaItem,example);
            return new Result(true, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, "失败");
        }
    }

    //删除
    @RequestMapping("/delete")
    public Result delete (Integer id){


        try {
            spaItemService.deleteByPrimaryKey(id);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, "删除失败");
        }
    }


    //全部查询
    @RequestMapping("/findAll")
    public Result findAll(){

        try {
            List<SpaItem> spaItems = spaItemService.selectByExample(null);
            return new Result(true, "查询成功",spaItems);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, "查询失败");
        }
    }
}
