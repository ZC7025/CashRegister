package com.sucheng.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AidCardVO值对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class AidCardVO extends BaseVO {

    private static final long serialVersionUID = -9223372035063287582L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private String cardNo;
	/**
	*
	*/
	private Integer vipId;
	/**
	*
	*/
	private BigDecimal money;
	/**
	*
	*/
	private String status;
	/**
	*
	*/
	private Date createdTime;
	
    public AidCardVO () {}

    public AidCardVO (Integer id, String cardNo, Integer vipId, BigDecimal money, String status, Date createdTime) {
        this.id = id;
		this.cardNo = cardNo;
		this.vipId = vipId;
		this.money = money;
		this.status = status;
		this.createdTime = createdTime;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getVipId() {
		return vipId;
	}

	public void setVipId(Integer vipId) {
		this.vipId = vipId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
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
        return "AidCardDO{" +
                "id = " + id + 
				", cardNo = " + cardNo + 
				", vipId = " + vipId + 
				", money = " + money + 
				", status = " + status + 
				", createdTime = " + createdTime + 
				"}";
    }

}
