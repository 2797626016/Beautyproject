package com.xixi.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.xixi.dao.ItemAndGroupDAO;
import com.xixi.pojo.ItemAndGroup;
import com.xixi.pojo.ItemAndGroupExample;
import com.xixi.service.ItemAndGroupService;
import com.xixi.service.ItemGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(interfaceClass = ItemAndGroupService.class)
public class ItemAndGroupServiceImp implements ItemAndGroupService {

    @Autowired
    private  ItemAndGroupDAO itemAndGroupDAO;

    @Override
    public long countByExample(ItemAndGroupExample example) {
        return itemAndGroupDAO.countByExample(example);
    }

    @Override
    public int deleteByExample(ItemAndGroupExample example) {
        return itemAndGroupDAO.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return itemAndGroupDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ItemAndGroup record) {
        return itemAndGroupDAO.insert(record);
    }

    @Override
    public int insertSelective(ItemAndGroup record) {
        return itemAndGroupDAO.insertSelective(record);
    }

    @Override
    public List<ItemAndGroup> selectByExample(ItemAndGroupExample example) {
        return itemAndGroupDAO.selectByExample(example);
    }

    @Override
    public ItemAndGroup selectByPrimaryKey(Integer id) {
        return itemAndGroupDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(ItemAndGroup record, ItemAndGroupExample example) {
        return itemAndGroupDAO.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(ItemAndGroup record, ItemAndGroupExample example) {
        return itemAndGroupDAO.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(ItemAndGroup record) {
        return itemAndGroupDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ItemAndGroup record) {
        return itemAndGroupDAO.updateByPrimaryKey(record);
    }
}
