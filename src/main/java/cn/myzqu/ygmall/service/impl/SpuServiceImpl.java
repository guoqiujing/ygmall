package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.GoodsMapper;
import cn.myzqu.ygmall.dao.SpuMapper;
import cn.myzqu.ygmall.pojo.Attribute;
import cn.myzqu.ygmall.pojo.Spu;
import cn.myzqu.ygmall.service.*;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import cn.myzqu.ygmall.vo.SpuDetailVO;
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
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private SpuDetailService spuDetailService;
    @Autowired
    private BrandService brandService;
    @Override
    public Spu selectByPrimaryKey(String id) {
        Spu spu=spuMapper.selectByPrimaryKey(id);
        return spu;
    }
    public Integer updateByPrimaryKeySelective(Spu spu){
        Integer result=spuMapper.updateByPrimaryKeySelective(spu);
        return result;
    }
    public Integer updateSPU_Attr(String oSpuId,String FName0,String oFVal0,String nFval0,String FName1,String oFVal1,String nFval1,String FName2,String oFVal2,String nFval2){
        Spu spu=new Spu();
        spu.setId(oSpuId);
        String oldAttr=selectByPrimaryKey(oSpuId).getAttributesName();
        HashMap<String,String> hashMap=JSON.parseObject(oldAttr, HashMap.class);
        if(hashMap.containsKey(FName0))
            hashMap.put(FName0,hashMap.get(FName0).replace(oFVal0,nFval0));
        if(hashMap.containsKey(FName1))
            hashMap.put(FName1,hashMap.get(FName1).replace(oFVal1,nFval1));
        if(hashMap.containsKey(FName2))
            hashMap.put(FName2,hashMap.get(FName2).replace(oFVal2,nFval2));
        spu.setAttributesName(JSON.toJSONString(hashMap));
        Integer result= updateByPrimaryKeySelective(spu);
        return result;
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
//    查找所有spu信息及其详情图信息
    public BootstrapTableVO selectAllSpu_Img(int pageSize,int pageIndex,String searchInput){
        Page<Spu> page = PageHelper.startPage(pageIndex,pageSize);
        Map<String,Object> map = new HashMap<>();
        if(searchInput!=null&&searchInput!="") {
            map.put("name", searchInput);
        }
//        List<Spu> spuList=spuMapper.selectAll(map);
//        List<SpuDetailVO> spuDetailList=new ArrayList<>();
//        for(Spu spu:spuList){
//            SpuDetailVO spuDetailVO=new SpuDetailVO();
//            spuDetailVO.setSpu(spu);
//            spuDetailVO.setSpuImgList(spuDetailService.selectBySPUId(spu.getId()));
//            spuDetailVO.setBrandName(brandService.findBrandById(spu.getBrandId()).getName());
//            spuDetailList.add(spuDetailVO);
//        }
        List<SpuDetailVO> spuList=spuMapper.selectAll(map);
        for(int i=0;i<spuList.size();i++){
            spuList.get(i).setSpuImgList(spuList.get(i).getSpuImgList());
            spuList.get(i).setCategoriesName(spuList.get(i).getCategoriesName());
            spuList.get(i).setBrandName(brandService.findBrandById(spuList.get(i).getSpu().getBrandId()).getName());
        }
        int total = (int)page.getTotal();
        System.out.println("总记录数："+total);
        //把总记录数和某一页的记录装入BootstrapTableVO类
        BootstrapTableVO bto=new BootstrapTableVO(total,spuList);
        if(total<=0)
            return null;
        return bto;
    }
    //    查找某个spu的信息及其详情图信息
    public SpuDetailVO selectSpu_ImgById(String id){
        Spu spu=spuMapper.selectByPrimaryKey(id);
        SpuDetailVO spuDetailVO=new SpuDetailVO();
        spuDetailVO.setSpu(spu);
        spuDetailVO.setSpuImgList(spuDetailService.selectBySPUId(spu.getId()));
        spuDetailVO.setBrandName(brandService.findBrandById(spu.getBrandId()).getName());
        spuDetailVO.setCategoriesName(attributeService.getCategoriesAttributeByCategoryId(spu.getCategoryId()).getCategoriesName());
        return spuDetailVO;
    }
    public int insert(String spuId,String name,Integer categoryId,Integer brandId,String attrNamesArray[],String attrValuesArray[],String subtitle) throws ParseException {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap<String,String> hashMap=new HashMap<>();
        for(int i=0;i<attrNamesArray.length;i++){
            hashMap.put(attrNamesArray[i],attrValuesArray[i]);
        }
        String params=JSON.toJSONString(hashMap);
        List<Attribute> attributeList=attributeService.selectByCategoryId(categoryId);
        HashMap<String,Object> attributesName=new HashMap<>();
        for(Attribute attribute : attributeList){
            attributesName.put(attribute.getName(),"");
        }
        Spu spu=new Spu();
        spu.setAttributesName(JSON.toJSONString(attributesName));
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
    public Integer putOn(String id){
        Spu spu=new Spu();
        spu.setId(id);
        Byte b=0;
        spu.setStatus(b);
        Integer result=spuMapper.updateByPrimaryKeySelective(spu);
        goodsMapper.putOnBySpuId(id);
        return result;
    }

    /**
     * 货品信息管理页面，修改货品信息
     * @param id
     * @param name
     * @param subtitle
     * @param saleCount
     * @param commentCount
     * @param attrNamesArray
     * @param attrValuesArray
     * @return
     */
    public Integer updateSPUSelective(String id, String name, String subtitle, Integer saleCount, Integer commentCount,String attrNamesArray[], String attrValuesArray[]){
        HashMap<String,String> hashMap=new HashMap<>();
        for(int i=0;i<attrNamesArray.length;i++){
            hashMap.put(attrNamesArray[i],attrValuesArray[i]);
        }
        String params=JSON.toJSONString(hashMap);
        Spu spu=new Spu();
        spu.setId(id);
        spu.setName(name);
        spu.setSubtitle(subtitle);
        spu.setSaleCount(saleCount);
        spu.setCommentCount(commentCount);
        spu.setParams(params);
        Integer result=updateByPrimaryKeySelective(spu);
        return result;
    }
}
