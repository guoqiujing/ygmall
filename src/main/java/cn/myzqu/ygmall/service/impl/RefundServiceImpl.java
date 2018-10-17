package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.RefundMapper;
import cn.myzqu.ygmall.pojo.Refund;
import cn.myzqu.ygmall.service.RefundService;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;
import java.util.Map;

/**
 * Created by CC on 2018/10/16.
 */
@Service
public class RefundServiceImpl implements RefundService {
    @Autowired
    private RefundMapper refundMapper;

    @Override
    public int addRefund(Refund refund){
        int r=refundMapper.insert(refund);
        return r;
    }

    @Override
    public BootstrapTableVO findRefund(Map<String, Object> map, Integer pageIndex, Integer pageSize){
        Page<Refund> page= PageHelper.startPage(pageIndex,pageSize);
        List<Refund> refundList=refundMapper.selectRefund(map);

        int total = (int)page.getTotal();
        System.out.println("总记录数："+total);
        //把总记录数和某一页的记录装入BootstrapTableVO类
        BootstrapTableVO bto=new BootstrapTableVO(total,refundList);
        if(total<=0)
            return null;
        return bto;
    }

    @Override
    public int updateStep(Refund refund){
        int r=refundMapper.updateByPrimaryKeySelective(refund);
        return r;
    }
}
