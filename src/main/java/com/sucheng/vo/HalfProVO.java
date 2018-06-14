package com.sucheng.vo;

import java.util.Date;

/**
 * HalfProVO值对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class HalfProVO extends BaseVO {

    private static final long serialVersionUID = -9223372034929070430L;

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

	/**
	*处理状态
	*/
	private String status;
	/**
	*
	*/
	private Date createdTime;
	
    public HalfProVO () {}

	public HalfProVO(Integer id, String name, Float count,
					 Integer unitId, Integer storeId, String status, Date createdTime) {
		this.id = id;
		this.name = name;
		this.count = count;
		this.unitId = unitId;
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
                "id = " + id + 
				", name = " + name + 
				", count = " + count + 
				", status = " + status +
				", createdTime = " + createdTime + 
				"}";
    }

}
