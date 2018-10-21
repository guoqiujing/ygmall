package cn.myzqu.ygmall.service.impl;

import cn.myzqu.ygmall.dao.AttributeMapper;
import cn.myzqu.ygmall.dao.CategoriesMapper;
import cn.myzqu.ygmall.dao.GoodsMapper;
import cn.myzqu.ygmall.dao.SpuMapper;
import cn.myzqu.ygmall.dto.GoodsDTO;
import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.pojo.Goods;
import cn.myzqu.ygmall.pojo.GoodsImg;
import cn.myzqu.ygmall.pojo.Spu;
import cn.myzqu.ygmall.service.AttributeService;
import cn.myzqu.ygmall.service.GoodsService;
import cn.myzqu.ygmall.utils.KeyUtil;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import cn.myzqu.ygmall.vo.CategoriesAttributeVO;
import cn.myzqu.ygmall.vo.GoodsDetailVO;
import cn.myzqu.ygmall.vo.Goods_Img_AttributesVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Simon on 2018/9/19.
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private AttributeService attributeService;



    public BootstrapTableVO getAllGoods_Spu_Img(int pageSize,int pageIndex,String searchInput){
        Page<Goods> page = PageHelper.startPage(pageIndex,pageSize);
        Map<String,Object> map = new HashMap<>();
        if(searchInput!=null&&searchInput!="") {
            map.put("name", searchInput);
        }
        List<Goods_Img_AttributesVO> goodsList=goodsMapper.getAll(map);
        for(int i=0;i<goodsList.size();i++){
            goodsList.get(i).setGoodsImgList(goodsList.get(i).getGoodsImgList());
            goodsList.get(i).setCategoriesName(goodsList.get(i).getCategoriesName());
//            goodsList.get(i).setCategoriesAttributeVO(attributeService.getCategoriesAttributeByCategoryId(goodsList.get(i).getCategoryId()));
        }
        int total = (int)page.getTotal();
        System.out.println("总记录数："+total);
        //把总记录数和某一页的记录装入BootstrapTableVO类
        BootstrapTableVO bto=new BootstrapTableVO(total,goodsList);
        if(total<=0)
            return null;
        return bto;
    }
    /**
     * 修改商品信息（商品信息管理页面调用）
     */
    public Integer updateGoods(Goods goods){
        Integer integer=goodsMapper.updateByPrimaryKeySelective(goods);
        return integer;
    }


    /**
         * 创建新商品（商品上架时调用）
         */
    public Integer createNew(Goods goods){
        Integer integer=goodsMapper.insertSelective(goods);
        Spu spu=spuMapper.selectByPrimaryKey(goods.getSpuId());
       JSONObject jsonObject=JSON.parseObject(spu.getAttributesName());
        String array[]=goods.getAttributes().split("-");
        for(String f:array){
            String format[]=f.split(";");
            String tempName=format[1].substring(4);
            String tempVal=jsonObject.get(tempName).toString().length()>0?jsonObject.get(tempName)+";"+format[0]:format[0];
            jsonObject.put(tempName,tempVal);
        }
        spu.setAttributesName(JSON.toJSONString(jsonObject));
        spuMapper.updateByPrimaryKeySelective(spu);
        return integer;
    }
    /**
     * 获取商品信息并关联查询货品信息
     * @param id
     * @return
     */
    @Override
    public List<GoodsDetailVO> getGoodsAndSPU(String id) {
        List<GoodsDetailVO> goodsDetailVO=goodsMapper.getGoodsAndSPU(id);
        if(goodsDetailVO.size()>0){
            Spu spu=goodsDetailVO.get(0).getSpu();
            goodsDetailVO.get(0).setSpu(spu);
        }
        return goodsDetailVO;
    }
    /**
     * 获取所有未下架的商品/搜索未下架商品
     * @param pageSize
     * @param pageIndex
     * @param searchInput
     * @return
     */
    public BootstrapTableVO getAllPutOff(int pageSize, int pageIndex, String searchInput) {
        Page<Goods> page = PageHelper.startPage(pageIndex,pageSize);
        Map<String,Object> map = new HashMap<>();
        if(searchInput!=null&&searchInput!="") {
            map.put("name", searchInput);
        }
        List<Goods> goodsList=goodsMapper.getAllPutOff(map);
        int total = (int)page.getTotal();
        System.out.println("总记录数："+total);
        //把总记录数和某一页的记录装入BootstrapTableVO类
        BootstrapTableVO bto=new BootstrapTableVO(total,goodsList);
        if(total<=0)
            return null;
        return bto;
    }
    /**
     * 根据货品id获取其囊括的商品的id及规格字符串
     * @param spuId
     * @return
     */
    public HashMap<String,String> getIdAndAttributes(String spuId){
        HashMap<String,String> hashMap=new HashMap<>();
        List<Goods> goodsList=goodsMapper.getIdAndAttributes(spuId);
        for(int i=0;i<goodsList.size();i++){
            String attr[]=goodsList.get(i).getAttributes().split("-");
            String targetAttr="";
            for(String a:attr){
                String attr2[]=a.split(";");
                targetAttr+=attr2[0]+"-";
            }
            targetAttr=targetAttr.substring(0,targetAttr.length()-1);
            hashMap.put(targetAttr,goodsList.get(i).getId());
        }
        return hashMap;
    }
    /**
     * 根据货品id下架商品
     * @param spuId
     * @return
     */
    public Integer putOffBySpuId(String spuId){
        Integer result=goodsMapper.putOffBySpuId(spuId);
        return result;
    }
    /**
     * 根据货品id修改商品状态为上架
     * @param spuId
     * @return
     */
    public Integer putOnBySpuId(String spuId){
        Integer result=goodsMapper.putOnBySpuId(spuId);
        return result;
    }

    /**
     * 根据商品id下架商品
     * @param id
     * @return
     */
    public Integer putOffById(String id){
        Integer result=goodsMapper.putOffById(id);
        return result;
    }


    @Override
    public PageDTO searchGoods(String search, int pageSize, int pageIndex) {
        Page<Goods> page = PageHelper.startPage(pageIndex,pageSize);
        List<GoodsDTO> goodsList = goodsMapper.searchGoods(search);
        int total = (int)page.getTotal();
        System.out.println("总记录数："+total);
        PageDTO pageDTO = new PageDTO(goodsList,total,pageSize,pageIndex);
        if(total<=0)
            return null;
        return pageDTO;
    }

}
