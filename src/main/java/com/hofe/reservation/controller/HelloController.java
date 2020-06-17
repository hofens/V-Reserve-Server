package com.hofe.reservation.controller;

import com.hofe.reservation.entity.Business;
import com.hofe.reservation.service.BusinessService;
import com.hofe.reservation.service.HelloService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("hello")
public class HelloController {

    @Resource
    HelloService helloService;

    @Resource
    private BusinessService businessService;

//    @Async("taskExecutor")
    @GetMapping("sayHello")
    public String sayHello() throws ExecutionException, InterruptedException {
//        return new AsyncResult<>("hello world");
        Future<String> future = helloService.sayHello();
        return future.get();
    }

    @GetMapping("selectOne")
    public Business selectOne(@RequestParam Integer serviceId) throws ExecutionException, InterruptedException {
        System.out.println("传入参数"+serviceId);
        Future<Business> future = helloService.queryById(serviceId);
        return future.get();
//        return this.businessService.queryById(serviceId);
    }
}
