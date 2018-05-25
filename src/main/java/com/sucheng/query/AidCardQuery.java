package com.sucheng.query;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AidCardQuery查询对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class AidCardQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372036127232010L;

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
	
    public AidCardQuery () {}

    public AidCardQuery (Integer id, String cardNo, Integer vipId, BigDecimal money, String status, Date createdTime) {
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
                "}";
    }

}
