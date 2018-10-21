package cn.myzqu.ygmall.dao;

import cn.myzqu.ygmall.dto.StatisticsDTO;
import cn.myzqu.ygmall.dto.StatisticsForWeekDTO;
import cn.myzqu.ygmall.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

public interface OrderMapper {


    int deleteById(String id);

    int insert(Order record);

    Order selectById(String id);

    int updateById(Order record);

    List<Order> selectOrderDetailByCustomerId(String customerId);

    List<Order> selectOrderDetailByCustomerIdAndStatus(@Param("customerId") String customerId, @Param("status") Byte status);

    List<Order> selectByCustomerId(@Param("customerId") String customerId, @Param("status") Byte status);

    List<StatisticsForWeekDTO>  selectSaleroomForWeek();

    /**
     * 查询订单统计分析的基础数据
     * @param period 周期
     * @return
     */
    StatisticsDTO selectStatistics(@Param("period") int period, @Param("status") Byte status);

    /**
     * 统计订单商品总成本和销售
     * @param period
     * @return
     */
    Map selectGoodsStatistics(@Param("period") int period);
}