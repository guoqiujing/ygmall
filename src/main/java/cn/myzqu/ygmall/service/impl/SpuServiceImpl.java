package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.SpuMapper;
import cn.myzqu.ygmall.pojo.Spu;
import cn.myzqu.ygmall.service.SpuService;
import cn.myzqu.ygmall.utils.KeyUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Simon on 2018/9/19.
 */
@Service
public class SpuServiceImpl implements SpuService{
    @Autowired
    private SpuMapper spuMapper;
    @Override
    public Spu selectByPrimaryKey(String id) {
        Spu spu=spuMapper.selectByPrimaryKey(id);
        return spu;
    }
    public int insert(String name,Integer categoryId,Integer brandId,String attrNamesArray[],String attrValuesArray[],String subtitle) throws ParseException {
        HashMap<String,String> hashMap=new HashMap<>();
        for(int i=0;i<attrNamesArray.length;i++){
            hashMap.put(attrNamesArray[i],attrValuesArray[i]);
        }
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String params=JSON.toJSONString(hashMap);
        Spu spu=new Spu();
        spu.setId(KeyUtil.getUUID());
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
        return spuMapper.insert(spu);
    }
}
