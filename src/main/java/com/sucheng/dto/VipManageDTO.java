package com.sucheng.dto;

import java.util.Date;

/**
 * VipManageDTO数据传输对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class VipManageDTO extends BaseDTO {

    private static final long serialVersionUID = -9223372036345983429L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private Date vipDay;
	/**
	*
	*/
	private String discountWay;
	/**
	*
	*/
	private Float discount;
	/**
	*积分活动
	*/
	private Integer integral;
	/**
	*会员初始积分
	*/
	private Integer defaultIntegral;
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
	
    public VipManageDTO () {}

    public VipManageDTO (Integer id, Date vipDay, String discountWay, Float discount, Integer integral, Integer defaultIntegral, Integer storeId, String status, Date createdTime) {
        this.id = id;
		this.vipDay = vipDay;
		this.discountWay = discountWay;
		this.discount = discount;
		this.integral = integral;
		this.defaultIntegral = defaultIntegral;
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

	public Date getVipDay() {
		return vipDay;
	}

	public void setVipDay(Date vipDay) {
		this.vipDay = vipDay;
	}

	public String getDiscountWay() {
		return discountWay;
	}

	public void setDiscountWay(String discountWay) {
		this.discountWay = discountWay;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Integer getDefaultIntegral() {
		return defaultIntegral;
	}

	public void setDefaultIntegral(Integer defaultIntegral) {
		this.defaultIntegral = defaultIntegral;
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
        return "VipManageDO{" +
                "id = " + id + 
				", vipDay = " + vipDay + 
				", discountWay = " + discountWay + 
				", discount = " + discount + 
				", integral = " + integral + 
				", defaultIntegral = " + defaultIntegral + 
				", storeId = " + storeId + 
				", status = " + status + 
				", createdTime = " + createdTime + 
				"}";
    }

}
