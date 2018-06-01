package com.sucheng.vo;

import java.util.Date;

public class UnitVO extends BaseVO {

    private Integer id;

    private String unit;

    private String descript;

    private Integer storeId;

    private String status;

    private Date createdTime;

    public UnitVO() {
    }

    public UnitVO(Integer id, String unit, String descript, Integer storeId,
                  String status, Date createdTime) {
        this.id = id;
        this.unit = unit;
        this.descript = descript;
        this.storeId = storeId;
        this.status = status;
        this.createdTime = createdTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "UnitVO{" +
                "id=" + id +
                ", unit='" + unit + '\'' +
                ", descript='" + descript + '\'' +
                ", storeId=" + storeId +
                ", status='" + status + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
