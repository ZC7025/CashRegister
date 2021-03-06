package com.sucheng.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * RawMaterialsVO值对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class RawMaterialsVO extends BaseVO {

    private static final long serialVersionUID = -9223372036538450346L;

    /**
	*
	*/
	private Integer id;
	/**
	*商品名
	*/
	private String name;
	/**
	*进价
	*/
	private BigDecimal price;
	/**
	 * 数量
	 */
	private Float amount;
	/**
	*单位
	*/
	private Integer unitId;
	/**
	*供应商id
	*/
	private Integer supplierId;
	/**
	*生产日期
	*/
	private Date birthTime;
	/**
	*保质期
	*/
	private String shelfTime;
	/**
	*过期时间
	*/
	private Date deadTime;
	/**
	*
	*/
	private Integer minStock;
	/**
	*
	*/
	private Integer maxStock;
	/**
	*
	*/
	private Integer storeId;
	/**
	*
	*/
	private String status;
	/**
	*
	*/
	private Date createdTime;
	
    public RawMaterialsVO () {}

	public RawMaterialsVO(Integer id, String name, BigDecimal price,
						  Float amount, Integer unitId, Integer supplierId,
						  Date birthTime, String shelfTime, Date deadTime,
						  Integer minStock, Integer maxStock, Integer storeId,
						  String status, Date createdTime) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.unitId = unitId;
		this.supplierId = supplierId;
		this.birthTime = birthTime;
		this.shelfTime = shelfTime;
		this.deadTime = deadTime;
		this.minStock = minStock;
		this.maxStock = maxStock;
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

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(Date birthTime) {
		this.birthTime = birthTime;
	}

	public String getShelfTime() {
		return shelfTime;
	}

	public void setShelfTime(String shelfTime) {
		this.shelfTime = shelfTime;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getDeadTime() {
		return deadTime;
	}

	public void setDeadTime(Date deadTime) {
		this.deadTime = deadTime;
	}

	public Integer getMinStock() {
		return minStock;
	}

	public void setMinStock(Integer minStock) {
		this.minStock = minStock;
	}

	public Integer getMaxStock() {
		return maxStock;
	}

	public void setMaxStock(Integer maxStock) {
		this.maxStock = maxStock;
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
        return "RawMaterialsDO{" +
                "id = " + id + 
				", name = " + name + 
				", price = " + price + 
				", unitId = " + unitId +
				", supplierId = " + supplierId + 
				", birthTime = " + birthTime + 
				", shelfTime = " + shelfTime + 
				", deadTime = " + deadTime + 
				", minStock = " + minStock + 
				", maxStock = " + maxStock + 
				", storeId = " + storeId + 
				", status = " + status + 
				", createdTime = " + createdTime + 
				"}";
    }

}
