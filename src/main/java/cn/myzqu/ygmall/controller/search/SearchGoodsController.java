package cn.myzqu.ygmall.controller.search;

import cn.myzqu.ygmall.dto.PageDTO;
import cn.myzqu.ygmall.service.GoodsService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.utils.SessionUtil;
import cn.myzqu.ygmall.vo.BootstrapTableVO;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 的川 on 2018/10/20.
 */
@RestController
@RequestMapping("/search")
public class SearchGoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/{search}")
    public Result searchGoods(@PathVariable(value = "search") String search,
                              @RequestParam(value = "page", defaultValue = "0") Integer page,
                              @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageDTO pageDTO = goodsService.searchGoods(search,size,page);
        if(pageDTO!=null){
            return ResultVOUtil.success(
                    new BootstrapTableVO(pageDTO.getTotal(),pageDTO.getRows())) ;
        }
        return ResultVOUtil.error("哎呀没有找到和 "+ search +" 相关的商品哟");
    }

    @GetMapping("/browse/recent")
    public Result RecentBrowse(HttpServletRequest request){
        SessionUtil.GetLastGoodsSession(request);
//        PageDTO pageDTO = goodsService.searchGoods(search,size,page);
//        if(pageDTO!=null){
//            return ResultVOUtil.success(
//                    new BootstrapTableVO(pageDTO.getTotal(),pageDTO.getRows())) ;
//        }
        return ResultVOUtil.error("没有数据");
    }



}
