package com.hofe.reservation.controller;

import com.hofe.reservation.entity.JsonResult;
import com.hofe.reservation.entity.Order;
import com.hofe.reservation.service.BusinessService;
import com.hofe.reservation.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * (Order)表控制层
 *
 * @author makejava
 * @since 2020-06-06 16:46:07
 */
@RestController
@RequestMapping("order")
public class OrderController {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;

    @Resource
    private BusinessService businessService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Order selectOne(Integer id) throws ExecutionException, InterruptedException {
        return this.orderService.queryById(id).get();
    }

    /*
    * 将用户提交的预约信息存入order，同时减小剩余号
    * */
    @PostMapping("insertOne")
    public JsonResult<Order> insertOne(Order o) throws ExecutionException, InterruptedException {
        System.out.println(o);

        String[] timeStr = o.getReserveTakeTime().split(" ");
        String ymd = timeStr[0].replace("-", "");
        String hms = timeStr[1].replace(":", "");

        int serviceId = o.getServiceId();
        char[] IDchar = new char[]{'Y', 'H', 'F', 'S'};


        o.setSerialNum(IDchar[serviceId]+ymd+hms);
//        Order temp = this.orderService.insert(o);
        if(businessService.changeNum(o.getServiceId(), 1).get()) {
            return new JsonResult<>(this.orderService.insert(o).get(), "111", "预约成功");
        }else{
            return new JsonResult<>(new Order(), "100", "预约失败");
        }
    }

    @PostMapping("selectByOrder")
    public JsonResult<Order> selectByIDcardOrService(Order order) throws ExecutionException, InterruptedException {

        // 现场取号，不用从数据库中取，办事编号由当前时间组成
        if(order.getIdcard().equals("undefined")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

            String[] timeStr = df.format(new Date()).split(" ");
            String ymd = timeStr[0].replace("-", "");
            String hms = timeStr[1].replace(":", "");
            char[] IDchar = new char[]{'Y', 'H', 'F', 'S'};
            order.setSerialNum(IDchar[order.getServiceId()]+ymd+hms);

            return new JsonResult<>(order,"22","现场取号成功");
        }

        // 预约取号，通过idcard从数据库中读
        List<Order> orderList = this.orderService.queryAll(order).get();
        Iterator<Order> iterator = orderList.iterator();
        System.out.println("传来的参数"+order.toString());
        while (iterator.hasNext()){
            Order o = iterator.next();
            System.out.println("获取的对象"+o.toString());
            if(!order.getIdcard().equals(' ') && o.getIdcard().equals(order.getIdcard())){
                this.businessService.changeNum(o.getServiceId(), -1);
                this.orderService.deleteById(o.getId());
                return new JsonResult<>(o,"21","预约取号成功");
            }
        }

        return new JsonResult<>("101", "无预约记录");
    }

    @GetMapping("/selectAll")
    public JsonResult<List<Order>> selectAll() throws ExecutionException, InterruptedException {

        List<Order> orderList = this.orderService.queryAllByLimit(0, 50).get();
        return new JsonResult<>(orderList, "31", "显示所有数据");
    }


}