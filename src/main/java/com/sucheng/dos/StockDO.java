package com.sucheng.dos;

import java.math.BigDecimal;
import java.util.Date;

/**
 * StockDO数据对象实体类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class StockDO extends BaseDO {

    private static final long serialVersionUID = -9223372035310591098L;

    /**
	*
	*/
	private Integer id;
	/**
	*原料id
	*/
	private Integer rawId;
	/**
	*类别
	*/
	private String type;
	/**
	*库存量
	*/
	private Float count;
	/**
	*单位
	*/
	private Integer unitId;
	/**
	*入库价
	*/
	private BigDecimal price;
	/**
	*
	*/
	private Date createdTime;
	/**
	*更新时间
	*/
	private Date updateTime;
	
    public StockDO () {}

    public StockDO (Integer id, Integer rawId, String type, Float count, Integer unitId, BigDecimal price, Date createdTime, Date updateTime) {
        this.id = id;
		this.rawId = rawId;
		this.type = type;
		this.count = count;
		this.unitId = unitId;
		this.price = price;
		this.createdTime = createdTime;
		this.updateTime = updateTime;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRawId() {
		return rawId;
	}

	public void setRawId(Integer rawId) {
		this.rawId = rawId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
    @Override
    public String toString() {
        return "StockDO{" +
                "id = " + id + 
				", rawId = " + rawId + 
				", type = " + type + 
				", count = " + count + 
				", unitId = " + unitId +
				", price = " + price + 
				", createdTime = " + createdTime + 
				", updateTime = " + updateTime + 
				"}";
    }

}
