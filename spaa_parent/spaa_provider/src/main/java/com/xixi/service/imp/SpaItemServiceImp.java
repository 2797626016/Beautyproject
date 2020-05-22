package com.xixi.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.container.page.PageHandler;
import com.github.pagehelper.PageHelper;
import com.xixi.dao.SpaItemDAO;
import com.xixi.enity.PageResult;
import com.xixi.enity.QueryPageBean;
import com.xixi.pojo.SpaItem;
import com.xixi.pojo.SpaItemExample;
import com.xixi.service.SpaItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

@Service(interfaceClass = SpaItemService.class)
public class SpaItemServiceImp implements SpaItemService , Serializable {
    @Autowired
    private SpaItemDAO spaItemDAO;
    public long countByExample(SpaItemExample example){
        return spaItemDAO.countByExample(example);
    }

    public int deleteByExample(SpaItemExample example){
        return spaItemDAO.deleteByExample(example);
    }

    public int deleteByPrimaryKey(Integer id){
        return spaItemDAO.deleteByPrimaryKey(id);
    }

    public int insert(SpaItem record){
        return spaItemDAO.insert(record);
    }

    public int insertSelective(SpaItem record){
        return spaItemDAO.insertSelective(record);
    }

    public List<SpaItem> selectByExample(SpaItemExample example){
        return spaItemDAO.selectByExample(example);
    }

    public SpaItem selectByPrimaryKey(Integer id){
        return spaItemDAO.selectByPrimaryKey(id);
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
        SpaItemExample spaItemExample = new SpaItemExample();
        SpaItemExample.Criteria criteria = spaItemExample.createCriteria();
        criteria.andNameLike('%'+queryString+'%');
        //调用dao方法
        List<SpaItem> spaItems = spaItemDAO.selectByExample(spaItemExample);
        //分页 中条数
        long total = spaItemDAO.countByExample(spaItemExample);

        return new PageResult(total,spaItems);
    }

    public int updateByExampleSelective(SpaItem record, SpaItemExample example){
        return spaItemDAO.updateByExampleSelective(record, example);
    }

    public int updateByExample(SpaItem record, SpaItemExample example){
        return spaItemDAO.updateByExample(record, example);
    }

    public int updateByPrimaryKeySelective(SpaItem record){
        return spaItemDAO.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SpaItem record){
        return spaItemDAO.updateByPrimaryKey(record);
    }
}
