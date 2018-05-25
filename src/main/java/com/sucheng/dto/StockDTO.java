package com.sucheng.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * StockDTO数据传输对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class StockDTO extends BaseDTO {

    private static final long serialVersionUID = -9223372035566267134L;

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
	private String unit;
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
	
    public StockDTO () {}

    public StockDTO (Integer id, Integer rawId, String type, Float count, String unit, BigDecimal price, Date createdTime, Date updateTime) {
        this.id = id;
		this.rawId = rawId;
		this.type = type;
		this.count = count;
		this.unit = unit;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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
				", unit = " + unit + 
				", price = " + price + 
				", createdTime = " + createdTime + 
				", updateTime = " + updateTime + 
				"}";
    }

}
