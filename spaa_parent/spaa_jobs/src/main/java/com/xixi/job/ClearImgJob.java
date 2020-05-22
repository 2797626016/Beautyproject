package com.xixi.job;

import com.qiniu.common.QiniuException;
import com.xixi.enity.RedisConstant;
import com.xixi.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * 定时清理 七牛 图片的 任务类
 */


public class ClearImgJob {
    // 流程, 根据redis 来 清楚 七牛的图片
    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){
        // 根据redis中保存的2个set集合进行差值计算.
        // 得到垃圾图片的集合,, 然后进行删除
        Set<String> set = jedisPool.getResource().
                sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        System.out.println(set);
        if (set!=null){
            System.out.println("set 不等于 null  进来了 ");
            for (String picName : set) {
                    //删除 七牛云的 图片
                System.out.println("删除图片---"+picName);
                QiniuUtils.deleteImage(picName);
                // 从redis集合中删除图片名称
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,picName);
                System.out.println("自动任务执行完毕, 清理的图片是 - - -"+picName);
            }
        }

    }

}
