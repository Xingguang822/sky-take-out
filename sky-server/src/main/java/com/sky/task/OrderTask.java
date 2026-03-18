package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 处理支付超时订单
     */
    @Scheduled(cron = "0 * * * * ?")
    public void processTimeoutOrder(){

        log.info("处理支付超时订单：{}", new Date());

        List<Orders> ordersList=orderMapper.getByStatusAndOrderTimeLT(Orders.TO_BE_CONFIRMED, LocalDateTime.now().plusMinutes(-15));

        if (ordersList!=null && ordersList.size()>0) {
            for(Orders order:ordersList){
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("支付超时，自动取消");
                order.setCancelTime(LocalDateTime.now());
                orderMapper.insert(order);
            }
        }
    }

    /**
     * 处理“派送中”状态的订单
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void processDeliveryOrder(){

        log.info("处理派送中订单：{}", new Date());

        List<Orders> ordersList=orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, LocalDateTime.now().plusMinutes(-60));

        if (ordersList!=null && ordersList.size()>0) {
            for(Orders order:ordersList){
                order.setStatus(Orders.COMPLETED);
                orderMapper.insert(order);
            }
        }
    }

}
