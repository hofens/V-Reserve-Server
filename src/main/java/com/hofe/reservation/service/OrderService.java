package com.hofe.reservation.service;

import com.hofe.reservation.entity.Order;
import java.util.List;
import java.util.concurrent.Future;

/**
 * (Order)表服务接口
 *
 * @author makejava
 * @since 2020-06-06 17:43:27
 */
public interface OrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Future<Order> queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Future<List<Order>> queryAllByLimit(int offset, int limit);

    Future<List<Order>> queryAll(Order order);
    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    Future<Order> insert(Order order);

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    Future<Order> update(Order order);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Future<Boolean> deleteById(Integer id);

}