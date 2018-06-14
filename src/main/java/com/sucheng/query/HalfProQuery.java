package com.sucheng.query;

import java.util.Date;

/**
 * HalfProQuery查询对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class HalfProQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372036123117611L;

    /**
	*
	*/
	private Integer id;
	/**
	*半成品名
	*/
	private String name;
	/**
	*数量
	*/
	private Float count;
	/**
	*单位
	*/
	private Integer unitId;

	private Integer storeId;

	private String unit;
	/**
	*处理状态
	*/
	private String status;
	/**
	*
	*/
	private Date createdTime;
	
    public HalfProQuery () {}

	public HalfProQuery(Integer id, String name, Float count,
						Integer unitId, Integer storeId, String unit, String status, Date createdTime) {
		this.id = id;
		this.name = name;
		this.count = count;
		this.unitId = unitId;
		this.storeId = storeId;
		this.unit = unit;
		this.status = status;
		this.createdTime = createdTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getCount() {
		return count;
	}

	public void setCount(Float count) {
		this.count = count;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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
        return "HalfProDO{" +
                "}";
    }

}
