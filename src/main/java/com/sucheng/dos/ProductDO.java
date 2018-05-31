package com.sucheng.dos;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ProductDO数据对象实体类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class ProductDO extends BaseDO {

    private static final long serialVersionUID = -9223372035002841660L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private String name;
	/**
	*
	*/
	private Integer storeId;
	/**
	*口味id
	*/
	private Integer tasteId;
	/**
	 * 商品类别id
	 */
	private Integer typeId;
	/**
	*
	*/
	private BigDecimal price;
	/**
	*
	*/
	private String proImg1;
	/**
	*
	*/
	private String proImg2;
	/**
	*
	*/
	private String proImg3;
	/**
	*
	*/
	private String proImg4;
	/**
	*菜单显示优先级
	*/
	private Integer priority;
	/**
	*
	*/
	private String status;
	/**
	*
	*/
	private Date createdTime;
	
    public ProductDO () {}

    public ProductDO (Integer id, String name, Integer storeId, Integer tasteId, BigDecimal price, String proImg1, String proImg2, String proImg3, String proImg4, Integer priority, String status, Date createdTime) {
        this.id = id;
		this.name = name;
		this.storeId = storeId;
		this.tasteId = tasteId;
		this.price = price;
		this.proImg1 = proImg1;
		this.proImg2 = proImg2;
		this.proImg3 = proImg3;
		this.proImg4 = proImg4;
		this.priority = priority;
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

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getTasteId() {
		return tasteId;
	}

	public void setTasteId(Integer tasteId) {
		this.tasteId = tasteId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProImg1() {
		return proImg1;
	}

	public void setProImg1(String proImg1) {
		this.proImg1 = proImg1;
	}

	public String getProImg2() {
		return proImg2;
	}

	public void setProImg2(String proImg2) {
		this.proImg2 = proImg2;
	}

	public String getProImg3() {
		return proImg3;
	}

	public void setProImg3(String proImg3) {
		this.proImg3 = proImg3;
	}

	public String getProImg4() {
		return proImg4;
	}

	public void setProImg4(String proImg4) {
		this.proImg4 = proImg4;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
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
        return "ProductDO{" +
                "id = " + id + 
				", name = " + name + 
				", storeId = " + storeId + 
				", tasteId = " + tasteId + 
				", price = " + price + 
				", proImg1 = " + proImg1 + 
				", proImg2 = " + proImg2 + 
				", proImg3 = " + proImg3 + 
				", proImg4 = " + proImg4 + 
				", priority = " + priority + 
				", status = " + status + 
				", createdTime = " + createdTime + 
				"}";
    }

}
