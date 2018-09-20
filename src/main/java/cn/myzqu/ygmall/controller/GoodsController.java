package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.service.GoodsService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.GoodsDetailVO;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Simon on 2018/9/19.
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @PostMapping("/getCompleteGoodsById")
    public Result getGoodsAndSPU(String id){
        List<GoodsDetailVO> goodsDetailVO=goodsService.getGoodsAndSPU(id);
        if (goodsDetailVO.size() > 0) {
            return ResultVOUtil.success(goodsDetailVO);
        }
        return ResultVOUtil.error("关联查找商品与货品失败");
    }
}
