package com.hofe.reservation.entity;

import java.io.Serializable;

/**
 * (Business)实体类
 *
 * @author makejava
 * @since 2020-06-06 14:37:05
 */
public class Business implements Serializable {
    private static final long serialVersionUID = -26440821771153012L;
    /**
    * 服务类别
    */
    private Integer id;
    
    private String serviceName;
    
    private Integer reserveNum;
    /**
    * 剩余数量
    */
    private Integer residualNum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getReserveNum() {
        return reserveNum;
    }

    public void setReserveNum(Integer reserveNum) {
        this.reserveNum = reserveNum;
    }

    public Integer getResidualNum() {
        return residualNum;
    }

    public void setResidualNum(Integer residualNum) {
        this.residualNum = residualNum;
    }

    @Override
    public String toString() {
        return "Business{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", reserveNum=" + reserveNum +
                ", residualNum=" + residualNum +
                '}';
    }
}