package cn.myzqu.ygmall.controller;

import cn.myzqu.ygmall.pojo.GoodsImg;
import cn.myzqu.ygmall.service.GoodsImgService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Simon on 2018/9/20.
 */
@RestController
@RequestMapping("/goodsImg")
public class GoodsImgController {
    @Autowired
    private GoodsImgService goodsImgService;
    @PostMapping("/getGoodsImgByGoodsId")  //√
        public Result getGoodsImgByGoodsId(String goodsId){
        List<GoodsImg> goodsImgs=goodsImgService.selectByGoodsId(goodsId);
        if (goodsImgs.size() > 0) {
            return ResultVOUtil.success(goodsImgs);
        }
        return ResultVOUtil.error("查找商品图片失败");
    }
}
