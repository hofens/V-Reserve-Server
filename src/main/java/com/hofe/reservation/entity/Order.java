package com.hofe.reservation.entity;

import java.io.Serializable;

/**
 * (Order)实体类
 *
 * @author makejava
 * @since 2020-06-06 17:43:27
 */
public class Order implements Serializable {
    private static final long serialVersionUID = -45414530606521366L;
    
    private Integer id;
    
    private String idcard;
    
    private String phone;
    
    private Integer serviceId;
    
    private String reserveTakeTime;
    
    private String serialNum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getReserveTakeTime() {
        return reserveTakeTime;
    }

    public void setReserveTakeTime(String reserveTakeTime) {
        this.reserveTakeTime = reserveTakeTime;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", idcard='" + idcard + '\'' +
                ", phone='" + phone + '\'' +
                ", serviceId=" + serviceId +
                ", reserveTakeTime='" + reserveTakeTime + '\'' +
                ", serialNum='" + serialNum + '\'' +
                '}';
    }
}