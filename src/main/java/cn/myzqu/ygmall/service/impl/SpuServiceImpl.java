package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.GoodsMapper;
import cn.myzqu.ygmall.dao.SpuMapper;
import cn.myzqu.ygmall.pojo.Brand;
import cn.myzqu.ygmall.pojo.Spu;
import cn.myzqu.ygmall.service.SpuService;
import cn.myzqu.ygmall.utils.KeyUtil;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Simon on 2018/9/19.
 */
@Service
public class SpuServiceImpl implements SpuService{
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public Spu selectByPrimaryKey(String id) {
        Spu spu=spuMapper.selectByPrimaryKey(id);
        return spu;
    }
    public BootstrapTableVO selectIdAndName(int pageSize,int pageIndex,String searchInput){
        Page<Spu> page = PageHelper.startPage(pageIndex,pageSize);
        Map<String,Object> map = new HashMap<>();
        if(searchInput!=null&&searchInput!="") {
            map.put("name", searchInput);
        }
        List<Spu> spuList=spuMapper.selectIdAndName(map);
        int total = (int)page.getTotal();
        System.out.println("总记录数："+total);
        //把总记录数和某一页的记录装入BootstrapTableVO类
        BootstrapTableVO bto=new BootstrapTableVO(total,spuList);
        if(total<=0)
            return null;
        return bto;
    }
    public int insert(String spuId,String name,Integer categoryId,Integer brandId,String attrNamesArray[],String attrValuesArray[],String subtitle) throws ParseException {
        HashMap<String,String> hashMap=new HashMap<>();
        for(int i=0;i<attrNamesArray.length;i++){
            hashMap.put(attrNamesArray[i],attrValuesArray[i]);
        }
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String params=JSON.toJSONString(hashMap);
        Spu spu=new Spu();
        spu.setId(spuId);
        spu.setParams(params);
        spu.setBrandId(brandId);
        spu.setCategoryId(categoryId);
        spu.setCommentCount(0);
        spu.setCreatetime(new Date());//sdf.parse(sdf.format(new Date()))
        spu.setName(name);
        spu.setSubtitle(subtitle);
        spu.setCommentCount(0);
        spu.setSaleCount(0);
        spu.setAttributesName("");
        Byte b=0;
        spu.setStatus(b);
        return spuMapper.insert(spu);
    }
    public Integer putOff(String id){
        Spu spu=new Spu();
        spu.setId(id);
        Byte b=1;
        spu.setStatus(b);
        Integer result=spuMapper.updateByPrimaryKeySelective(spu);
        goodsMapper.putOffBySpuId(id);
        return result;
    }
}
