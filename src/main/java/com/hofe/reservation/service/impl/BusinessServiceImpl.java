package com.hofe.reservation.service.impl;

import com.hofe.reservation.entity.Business;
import com.hofe.reservation.dao.BusinessDao;
import com.hofe.reservation.service.BusinessService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;

/**
 * (Business)表服务实现类
 *
 * @author makejava
 * @since 2020-06-06 14:37:09
 */
@Async
@Service("businessService")
public class BusinessServiceImpl implements BusinessService {
    @Resource
    private BusinessDao businessDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
//    @Override
//    public Business queryById(Integer id) {
//        return this.businessDao.queryById(id);
//    }


    @Override
    public Future<Business> queryById(Integer id){
        System.out.println(Thread.currentThread().getName()+"执行");
        return new AsyncResult<>(this.businessDao.queryById(id));
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Future<List<Business>> queryAllByLimit(int offset, int limit) {
        System.out.println(Thread.currentThread().getName()+"执行");
        return new AsyncResult<>(this.businessDao.queryAllByLimit(offset, limit));
    }

    /**
     * 新增数据
     *
     * @param business 实例对象
     * @return 实例对象
     */
    @Override
    public synchronized Future<Business> insert(Business business) {
        System.out.println(Thread.currentThread().getName()+"执行");
        this.businessDao.insert(business);
        return new AsyncResult<>(business);
    }

    /**
     * 修改数据
     *
     * @param business 实例对象
     * @return 实例对象
     */
    @Override
    public synchronized Future<Business> update(Business business){
        System.out.println(Thread.currentThread().getName()+"执行");
        this.businessDao.update(business);
        return this.queryById(business.getId());
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
        return new AsyncResult<>(this.businessDao.deleteById(id) > 0);
    }

    @Override
    public synchronized Future<Boolean> changeNum(Integer id, int num) {
        System.out.println(Thread.currentThread().getName()+"执行");
        System.out.println(id);
        Business business = this.businessDao.queryById(id);
        System.out.println(business);
        if(business.getResidualNum()<=0){
            return new AsyncResult<>(false);
        }
        if(this.businessDao.updateByNum(id, num) > 0){
            return new AsyncResult<>(true);
        }else{
            return new AsyncResult<>(false);
        }
    }
}