package com.sucheng.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * VipVO值对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class VipVO extends BaseVO {

    private static final long serialVersionUID = -9223372036093133082L;

    /**
	*
	*/
	private Integer id;
	/**
	*会员名
	*/
	private String name;
	/**
	*会员卡号
	*/
	private String cardNo;
	/**
	*
	*/
	private String phone;
	/**
	*
	*/
	private Date birthday;
	/**
	*积分
	*/
	private Integer integral;
	/**
	*余额
	*/
	private BigDecimal money;
	/**
	*预留金额
	*/
	private BigDecimal reserveMoney;
	/**
	*开卡门店
	*/
	private Integer storeId;
	/**
	*等级制度id
	*/
	private Integer levelId;
	/**
	*截止时间
	*/
	private Date overTime;
	/**
	*
	*/
	private String status;
	/**
	*开卡时间
	*/
	private Date createdTime;
	
    public VipVO () {}

    public VipVO (Integer id, String name, String cardNo, String phone, Date birthday, Integer integral, BigDecimal money, BigDecimal reserveMoney, Integer storeId, Integer levelId, Date overTime, String status, Date createdTime) {
        this.id = id;
		this.name = name;
		this.cardNo = cardNo;
		this.phone = phone;
		this.birthday = birthday;
		this.integral = integral;
		this.money = money;
		this.reserveMoney = reserveMoney;
		this.storeId = storeId;
		this.levelId = levelId;
		this.overTime = overTime;
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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getReserveMoney() {
		return reserveMoney;
	}

	public void setReserveMoney(BigDecimal reserveMoney) {
		this.reserveMoney = reserveMoney;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
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
        return "VipDO{" +
                "id = " + id + 
				", name = " + name + 
				", cardNo = " + cardNo + 
				", phone = " + phone + 
				", birthday = " + birthday + 
				", integral = " + integral + 
				", money = " + money + 
				", reserveMoney = " + reserveMoney + 
				", storeId = " + storeId + 
				", levelId = " + levelId + 
				", overTime = " + overTime + 
				", status = " + status + 
				", createdTime = " + createdTime + 
				"}";
    }

}
