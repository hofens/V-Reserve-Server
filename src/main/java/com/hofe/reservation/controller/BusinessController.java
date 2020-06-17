package com.hofe.reservation.controller;

import com.hofe.reservation.entity.Business;
import com.hofe.reservation.entity.JsonResult;
import com.hofe.reservation.service.BusinessService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * (Business)表控制层
 *
 * @author makejava
 * @since 2020-06-06 14:37:13
 */
@RestController
@RequestMapping("business")
public class BusinessController {
    /**
     * 服务对象
     */
    @Resource
    private BusinessService businessService;

    /**
     * 通过主键查询单条数据
     *
     * @param serviceId 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Business selectOne(@RequestParam Integer serviceId) throws ExecutionException, InterruptedException {
        System.out.println("传入参数"+serviceId);
//        Future<Business> future = businessService.queryById(serviceId);
//        return future.get();
        return this.businessService.queryById(serviceId).get();
    }

    @PostMapping("updateOne")
    public JsonResult<Business> updateOne( Integer id, int num) throws ExecutionException, InterruptedException {
        Business business = this.businessService.queryById(id).get();
        if(this.businessService.changeNum(id, num).get()){
            return new JsonResult<>(business,"11","修改余量成功");
        }else{
            return new JsonResult<>(business,"10","修改余量失败");
        }
    }

}