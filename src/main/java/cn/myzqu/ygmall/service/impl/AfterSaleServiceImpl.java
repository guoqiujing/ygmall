package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.AfterSaleMapper;
import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.pojo.AfterSale;
import cn.myzqu.ygmall.service.AfterSaleService;
import cn.myzqu.ygmall.vo.AfterSaleRefundVO;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by CC on 2018/10/15.
 */
@Service
public class AfterSaleServiceImpl implements AfterSaleService{
    @Autowired
    private AfterSaleMapper afterSaleMapper;

    //查找售后
    @Override
    public BootstrapTableVO findAfterSale(Map<String,Object> map, Integer pageIndex, Integer pageSize){
        //分页插件
        Page<AfterSale> page= PageHelper.startPage(pageIndex,pageSize);
        List<AfterSale> afterSaleList=afterSaleMapper.selectAfterSale(map);

        System.out.println(afterSaleList);
        //获取总记录数
        int total=(int)page.getTotal();
        System.out.println("总记录数："+total);
        //把总记录数和某一页的记录装入BootstrapTableVO类
        BootstrapTableVO bto=new BootstrapTableVO(total,afterSaleList);
        if(total<=0)
            return null;

        return bto;
    }

    //根据售后id查找售后数据
    @Override
    public AfterSale selectById(String id){
        return afterSaleMapper.selectByPrimaryKey(id);
    }

    //修改售后状态
    @Override
    public int updateStatus(AfterSale afterSale){
        int a=afterSaleMapper.updateByPrimaryKeySelective(afterSale);
        return a;
    }

    //添加售后
    @Override
    public int addAfterSale(AfterSale afterSale){
        int a=afterSaleMapper.insertSelective(afterSale);
        return a;
    }

    //根据用户id查询售后列表
    @Override
    public PageDTO selectAfterSale(String userId, Integer pageIndex, Integer pageSize){
        Page<AfterSaleRefundVO> page=PageHelper.startPage(pageIndex,pageSize);
        List<AfterSaleRefundVO> afterSaleRefundVOList=afterSaleMapper.selectAfterSaleByUserId(userId);
        int total=(int)page.getTotal();
        System.out.println("总记录数："+total);
        if(total<=0)
            return null;
        return new PageDTO(afterSaleRefundVOList,total,pageSize,pageIndex);
    }
}
