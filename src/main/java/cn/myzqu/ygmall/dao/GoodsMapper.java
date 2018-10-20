package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.dto.GoodsDTO;
import cn.myzqu.ygmall.pojo.Goods;
import cn.myzqu.ygmall.vo.GoodsDetailVO;
import cn.myzqu.ygmall.vo.Goods_Img_AttributesVO;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<GoodsDetailVO> getGoodsAndSPU(String id);

    List<Goods> getIdAndAttributes(String spuId);

    List<Goods_Img_AttributesVO> getAll(Map<String, Object> map);

    Integer putOnBySpuId(String spuId);

    Integer putOffBySpuId(String spuId);
    Integer putOffById(String id);

    List<Goods> getAllPutOff(Map<String,Object> map);

    List<GoodsDTO> searchGoods(String search);

}