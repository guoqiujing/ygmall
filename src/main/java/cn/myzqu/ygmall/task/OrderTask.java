package cn.myzqu.ygmall.task;

import cn.myzqu.ygmall.pojo.Order;
import cn.myzqu.ygmall.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 的川 on 2018/10/20.
 */
@Service
@Slf4j
public class OrderTask {

    @Autowired
    private OrderService orderService;

    //1分钟自动发货
    public void deliver(String  orderId){
        Timer timer = new Timer();
        timer.schedule(new DeliverTask(orderId),3000);
    }

    class DeliverTask extends TimerTask {
        private String orderId;

        public DeliverTask(String orderId) {
            this.orderId = orderId;
        }
        @Override
        public void run() {
            String userId = "系统已自动发货";
            System.out.println(userId);
            orderService.deliver(orderId,userId);
        }

    }

}
