package com.hofe.reservation.service;

import com.hofe.reservation.entity.Business;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface HelloService {

    Future<String> sayHello() throws ExecutionException, InterruptedException;

    Future<Business> queryById(Integer id) throws ExecutionException, InterruptedException;
}
