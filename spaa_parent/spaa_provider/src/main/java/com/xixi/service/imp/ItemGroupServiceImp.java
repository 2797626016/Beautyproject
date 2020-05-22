package com.xixi.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.xixi.dao.ItemAndGroupDAO;
import com.xixi.dao.ItemGroupDAO;
import com.xixi.enity.PageResult;
import com.xixi.enity.QueryPageBean;
import com.xixi.pojo.*;
import com.xixi.service.ItemGroupService;
import com.xixi.service.SpaItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = ItemGroupService.class)
@Transactional
public class ItemGroupServiceImp implements ItemGroupService {
    @Autowired
    private ItemGroupDAO itemGroupDAO;
    @Autowired
    private ItemAndGroupDAO itemAndGroupDAO;

    @Override
    public long countByExample(ItemGroupExample example) {
        return itemGroupDAO.countByExample(example);
    }

    @Override
    public int deleteByExample(ItemGroupExample example) {
        return itemGroupDAO.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return itemGroupDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ItemGroup record) {
        return itemGroupDAO.insert(record);
    }

    @Override
    public int insertSelective(ItemGroup record) {
        return itemGroupDAO.insertSelective(record);
    }

    @Override
    public List<ItemGroup> selectByExample(ItemGroupExample example) {
        return itemGroupDAO.selectByExample(example);
    }

    @Override
    public ItemGroup selectByPrimaryKey(Integer id) {
        return itemGroupDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(ItemGroup record, ItemGroupExample example) {
        return 0;
    }

    @Override
    public int updateByExample(ItemGroup record, ItemGroupExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(ItemGroup record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(ItemGroup record) {
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
        ItemGroupExample itemGroupExample = new ItemGroupExample();
        ItemGroupExample.Criteria criteria = itemGroupExample.createCriteria();
        criteria.andNameLike('%'+queryString+'%');
        //调用dao方法
        List<ItemGroup> itemGroups = itemGroupDAO.selectByExample(itemGroupExample);
        //分页 中条数
        long total = itemGroupDAO.countByExample(itemGroupExample);

        return new PageResult(total,itemGroups);
    }

    @Override
    public void add(ItemGroup itemGroup, Integer[] spaitemIds) {
        //1.增加ItemGroup
        itemGroupDAO.insert(itemGroup);
        //2.处理多对多的中间表
            //2.1拿group的id
        Integer itemGroupId =    itemGroup.getId();
            //2.2itemGroupId 和spaitemIds 的添加

        setGroupAndItem(itemGroupId,spaitemIds);
    }

    @Override
    public void edit(ItemGroup itemGroup, Integer[] spaitemIds) {
        ItemGroupExample example = new ItemGroupExample();
        ItemGroupExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemGroup.getId());

        itemGroupDAO.updateByExampleSelective(itemGroup,example);

        //删除旧的关联
        ItemAndGroupExample itemAndGroupExample = new ItemAndGroupExample();
        ItemAndGroupExample.Criteria exampleCriteria = itemAndGroupExample.createCriteria();
        exampleCriteria.andItemGroupIdEqualTo(itemGroup.getId());
        itemAndGroupDAO.deleteByExample(itemAndGroupExample);

        //重新建立新的关联关系
        Integer itemGroupId = itemGroup.getId();
        setGroupAndItem(itemGroupId,spaitemIds);
    }

    private void setGroupAndItem(Integer itemGroupId, Integer[] spaitemIds) {
            //关联第三长表的增加

        if(spaitemIds != null && spaitemIds.length>0){

                for (Integer spaitemId :spaitemIds ){
                    Map<String,Integer> map = new HashMap<>();
                    map.put("itemGroupId",itemGroupId);
                    map.put("spaitemId",spaitemId);
                    //根据map去保存我的数据的数据库
                    itemGroupDAO.setGroupAndItem(map);
                }


        }


    }


}
