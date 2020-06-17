package com.hofe.reservation.service.impl;

import com.hofe.reservation.entity.Order;
import com.hofe.reservation.dao.OrderDao;
import com.hofe.reservation.service.OrderService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2020-06-06 17:43:27
 */
@Service("orderService")
@Async
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Future<Order> queryById(Integer id) {
        System.out.println(Thread.currentThread().getName()+"执行");
        return new AsyncResult<>(this.orderDao.queryById(id));
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Future<List<Order>> queryAllByLimit(int offset, int limit) {
        System.out.println(Thread.currentThread().getName()+"执行");
        return new AsyncResult<>(this.orderDao.queryAllByLimit(offset, limit));
    }

    @Override
    public Future<List<Order>> queryAll(Order order) {
        System.out.println(Thread.currentThread().getName()+"执行");
        return new AsyncResult<>(this.orderDao.queryAll(order));
    }

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public synchronized Future<Order> insert(Order order) {
        System.out.println(Thread.currentThread().getName()+"执行");
        this.orderDao.insert(order);
        return new AsyncResult<>(order);
    }

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public synchronized Future<Order> update(Order order) {
        System.out.println(Thread.currentThread().getName()+"执行");
        this.orderDao.update(order);
        return this.queryById(order.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public synchronized Future<Boolean> deleteById(Integer id) {
        System.out.println(Thread.currentThread().getName()+"执行");
        return new AsyncResult<>(this.orderDao.deleteById(id) > 0);
    }


}