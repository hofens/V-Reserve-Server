package com.hofe.reservation.service.impl;

import com.hofe.reservation.dao.BusinessDao;
import com.hofe.reservation.entity.Business;
import com.hofe.reservation.service.HelloService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.*;


@Service("HelloService")
public class HelloServiceImpl implements HelloService {

    @Resource
    private BusinessDao businessDao;

//    @Async("taskExecutor")
    @Override
    @Async
    public Future<String> sayHello() throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName()+"执行");
        Future<String> future = new AsyncResult<>("hello world");
        return future;
    }

    @Async
    @Override
    public Future<Business> queryById(Integer id) throws ExecutionException, InterruptedException{
        System.out.println(Thread.currentThread().getName()+"执行");
        return new AsyncResult<>(this.businessDao.queryById(id));
    }
}
