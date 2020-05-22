package com.xixi.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.xixi.dao.SetmealDAO;
import com.xixi.dao.SetmealGroupDAO;
import com.xixi.enity.PageResult;
import com.xixi.enity.QueryPageBean;
import com.xixi.enity.RedisConstant;
import com.xixi.pojo.*;
import com.xixi.service.ItemGroupService;
import com.xixi.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImp  implements SetmealService {

    @Autowired
    private SetmealDAO setmealDAO;

    @Autowired
    private SetmealGroupDAO setmealGroupDAO;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public long countByExample(SetmealExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(SetmealExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Setmeal record) {
        return 0;
    }

    @Override
    public int insertSelective(Setmeal record) {
        return 0;
    }

    @Override
    public List<Setmeal> selectByExample(SetmealExample example) {
        return null;
    }

    @Override
    public Setmeal selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Setmeal record, SetmealExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Setmeal record, SetmealExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Setmeal record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Setmeal record) {
        return 0;
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage =  queryPageBean.getCurrentPage();//当前页数
        Integer pageSize  =  queryPageBean.getPageSize();//pagesize
        String queryString = queryPageBean.getQueryString();//查询条件啊
        if(queryString==null)queryString="";
        //pageHelper进行分页
        PageHelper.startPage(currentPage,pageSize);
        //查询条件
     SetmealExample setmealExample = new SetmealExample();
        SetmealExample.Criteria criteria = setmealExample.createCriteria();
        criteria.andNameLike('%'+queryString+'%');
        //调用dao方法
        List<Setmeal> spaItems = setmealDAO.selectByExample(setmealExample);
        //分页 中条数
        long total = setmealDAO.countByExample(setmealExample);

        return new PageResult(total,spaItems);
    }

    @Override
    public void add(Setmeal setmeal, Integer[] itemgroupIds) {
        setmealDAO.insert(setmeal);
        Integer setmealId  = setmeal.getId();

        this.setMealAndItemGroup(setmealId,itemgroupIds);
        //存入redis
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
    }

    public void setMealAndItemGroup(Integer setmealId ,Integer[] itemgroupIds){
        if(itemgroupIds != null && itemgroupIds.length>0){
            for(Integer itemgroupId : itemgroupIds){
                SetmealGroup setmealGroup = new SetmealGroup();
                setmealGroup.setGroupId(itemgroupId);
                setmealGroup.setSetmealId(setmealId);

                setmealGroupDAO.insertSelective(setmealGroup);
            }
        }
    }
}
