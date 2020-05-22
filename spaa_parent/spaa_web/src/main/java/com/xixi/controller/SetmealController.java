package com.xixi.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xixi.enity.PageResult;
import com.xixi.enity.QueryPageBean;
import com.xixi.enity.RedisConstant;
import com.xixi.enity.Result;
import com.xixi.pojo.Setmeal;
import com.xixi.service.SetmealService;
import com.xixi.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = setmealService.pageQuery(queryPageBean);
        return pageResult;
    }


    //上传图片
    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile){

        //上传图片 需要xml配置 大小 参数

        //2.需要参数MultipartFile
        System.out.println(imgFile);

        //获取原始文件名
          String originalFilename  = imgFile.getOriginalFilename();//改名字
        //找到xxx.jpg 的点
         int index =  originalFilename.lastIndexOf(".");
        String extention = originalFilename.substring(index - 1);//jpg
        //把xxx改为uuid
       String fileName =  UUID.randomUUID().toString() + extention;

        System.out.println("改过的名字"+fileName);
        //上传到七牛云
        try {
            QiniuUtils.uploadToQiNiu(imgFile.getBytes(),fileName);
            System.out.println("上传成功。。。。放入redis中");
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);

            //回显图片

        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
        return new Result(true,"成功",fileName);
    }


    @RequestMapping("/add")
    public  Result add(@RequestBody Setmeal setmeal,Integer[] itemgroupIds){

        try {
            setmealService.add(setmeal,itemgroupIds);
            return new Result(true,"成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }
}
