package com.sucheng.dto;

import java.math.BigDecimal;

/**
 * ProGiftDTO数据传输对象类<br/>
 *
 * 创建于2018-06-04<br/>
 *
 *
 * @version 1.0
 */
public class ProGiftDTO extends BaseDTO {

    private static final long serialVersionUID = -9223372036307753098L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private Integer proId;
	/**
	*
	*/
	private Integer giftId;
	/**
	*
	*/
	private Float count;

	private String name;
	private BigDecimal price;
	private Integer storeId;
	
    public ProGiftDTO () {}

    public ProGiftDTO (Integer id, Integer proId, Integer giftId, Float count) {
        this.id = id;
		this.proId = proId;
		this.giftId = giftId;
		this.count = count;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public Integer getGiftId() {
		return giftId;
	}

	public void setGiftId(Integer giftId) {
		this.giftId = giftId;
	}

	public Float getCount() {
		return count;
	}

	public void setCount(Float count) {
		this.count = count;
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

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	@Override
    public String toString() {
        return "ProGiftDO{" +
                "id = " + id + 
				", proId = " + proId + 
				", giftId = " + giftId + 
				", count = " + count + 
				"}";
    }

}
