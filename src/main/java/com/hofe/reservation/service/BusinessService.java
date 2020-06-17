package com.hofe.reservation.service;

import com.hofe.reservation.entity.Business;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * (Business)表服务接口
 *
 * @author makejava
 * @since 2020-06-06 14:37:08
 */
public interface BusinessService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Future<Business> queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Future<List<Business>> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param business 实例对象
     * @return 实例对象
     */
    Future<Business> insert(Business business);

    /**
     * 修改数据
     *
     * @param business 实例对象
     * @return 实例对象
     */
    Future<Business> update(Business business) throws ExecutionException, InterruptedException;

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Future<Boolean> deleteById(Integer id);

    Future<Boolean> changeNum(Integer id, int num);
}