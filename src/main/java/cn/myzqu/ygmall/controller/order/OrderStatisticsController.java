package cn.myzqu.ygmall.controller.order;

import cn.myzqu.ygmall.dto.StatisticsForWeekDTO;
import cn.myzqu.ygmall.exception.CustomException;
import cn.myzqu.ygmall.pojo.OrderDetail;
import cn.myzqu.ygmall.service.OrderService;
import cn.myzqu.ygmall.utils.ResultVOUtil;
import cn.myzqu.ygmall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 的川 on 2018/10/20.
 */
@RestController
@RequestMapping("/order/statistics")
public class OrderStatisticsController {

    @Autowired
    private OrderService orderService;


    /**
     * 最近一周的销售额（具体到每一天的销售额）
     * @return
     */
    @GetMapping("/saleroom")
    public Result selectSaleroomForWeek(){
        List<StatisticsForWeekDTO> statisticsForWeekDTOS = orderService.selectSaleroomForWeek();
        if(statisticsForWeekDTOS.size()>0){
            return ResultVOUtil.success(statisticsForWeekDTOS);
        }
        return ResultVOUtil.error("请求错误");
    }

    @GetMapping("/sale")
    public Result select(){
        return ResultVOUtil.success(orderService.statisticalOrder());
    }
}
