package com.sucheng.dos;

import java.math.BigDecimal;
import java.util.Date;

/**
 * GiftDO数据对象实体类<br/>
 *
 * 创建于2018-06-04<br/>
 *
 *
 * @version 1.0
 */
public class GiftDO extends BaseDO {

    private static final long serialVersionUID = -9223372036199040757L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private Integer storeId;
	/**
	*
	*/
	private String name;
	/**
	*套餐价格
	*/
	private BigDecimal price;
	/**
	*
	*/
	private String descript;
	/**
	*
	*/
	private String status;
	/**
	*
	*/
	private Date createdTime;
	
    public GiftDO () {}

    public GiftDO (Integer id, Integer storeId, String name, BigDecimal price, String descript, String status, Date createdTime) {
        this.id = id;
		this.storeId = storeId;
		this.name = name;
		this.price = price;
		this.descript = descript;
		this.status = status;
		this.createdTime = createdTime;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
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
        return "GiftDO{" +
                "id = " + id + 
				", storeId = " + storeId + 
				", name = " + name + 
				", price = " + price + 
				", descript = " + descript + 
				", status = " + status + 
				", createdTime = " + createdTime + 
				"}";
    }

}
