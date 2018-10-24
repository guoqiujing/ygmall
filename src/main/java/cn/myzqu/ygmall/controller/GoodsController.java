package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.dto.UserSessionDTO;
import cn.myzqu.ygmall.pojo.Goods;
import cn.myzqu.ygmall.service.GoodsService;
import cn.myzqu.ygmall.utils.KeyUtil;
import cn.myzqu.ygmall.utils.ResultVOUtil;
//import cn.myzqu.ygmall.utils.SessionUtil;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import cn.myzqu.ygmall.vo.GoodsDetailVO;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Simon on 2018/9/19.
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @PostMapping("/getCompleteGoodsById")
    public Result getGoodsAndSPU(String id, HttpServletRequest request){
        //设置最近浏览商品
//        SessionUtil.setLastGoodsSession(id,request);
        List<GoodsDetailVO> goodsDetailVO=goodsService.getGoodsAndSPU(id);
        if (goodsDetailVO.size() > 0) {
            return ResultVOUtil.success(goodsDetailVO);
        }
        return ResultVOUtil.error("关联查找商品与货品失败");
    }
    @PostMapping("/getAllGoods_Spu_Img")
    public Result getAllGoods_Spu_Img(int pageSize,int pageIndex,String searchInput){
        BootstrapTableVO bto=goodsService.getAllGoods_Spu_Img(pageSize,pageIndex,searchInput);
        if(bto!=null){
            return ResultVOUtil.success(bto);
        }
        return ResultVOUtil.error("获取所有商品及其关联的spu、图片信息失败");
    }
    @PostMapping("/getIdAndAttributesBySpuId")  //√
    public Result getIdAndAttributesBySpuId(String spuId){
        HashMap<String,String> hashMap=goodsService.getIdAndAttributes(spuId);
        if (hashMap.size() > 0) {
            return ResultVOUtil.success(hashMap);
        }
        return ResultVOUtil.error("查找商品属性值与ID失败");
    }

    /**
     * 根据货品id下架商品
     * @param spuId
     * @return
     */
    @PostMapping("/putOffBySpuId")
    public Result putOffBySpuId(String spuId){
        Integer result=goodsService.putOffBySpuId(spuId);
        if (result>=1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("下架货品失败");
    }
    @PostMapping("/putOffById")
    public Result putOffById(String id){
        Integer result=goodsService.putOffById(id);
        if (result==1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error("下架商品失败");
    }
    @PostMapping("/getAllPutOff")
    public Result getAllPutOff(int pageSize,int pageIndex,String searchInput){
        BootstrapTableVO bto=goodsService.getAllPutOff(pageSize,pageIndex,searchInput);if(bto!=null){
            return ResultVOUtil.success(bto);
        }
        return ResultVOUtil.error("获取商品失败");
    }
    @PostMapping("/create")
    public Result create(Goods goods){
        String goodsId= KeyUtil.getUUID();
        goods.setId(goodsId);
        Integer result=goodsService.createNew(goods);
        if(result==1)
            return ResultVOUtil.success(goodsId);
        return ResultVOUtil.error("上架商品失败");
    }
    @PostMapping("/update")
    public Result update(Goods goods){
        Integer result=goodsService.updateGoods(goods);
        if(result==1)
            return ResultVOUtil.success();
        return ResultVOUtil.error("更新商品信息失败");
    }
}
