package com.sucheng.dos;

import java.math.BigDecimal;
import java.util.Date;

/**
 * LogMoneyDO数据对象实体类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class LogMoneyDO extends BaseDO {

    private static final long serialVersionUID = -9223372034933669440L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private Integer type;
	/**
	*
	*/
	private Integer wayId;
	/**
	*
	*/
	private Integer storeId;
	/**
	*
	*/
	private BigDecimal money;
	/**
	*
	*/
	private Date createdTime;
	/**
	*
	*/
	private String descript;
	
    public LogMoneyDO () {}

    public LogMoneyDO (Integer id, Integer type, Integer wayId, Integer storeId, BigDecimal money, Date createdTime, String descript) {
        this.id = id;
		this.type = type;
		this.wayId = wayId;
		this.storeId = storeId;
		this.money = money;
		this.createdTime = createdTime;
		this.descript = descript;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getWayId() {
		return wayId;
	}

	public void setWayId(Integer wayId) {
		this.wayId = wayId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	
    @Override
    public String toString() {
        return "LogMoneyDO{" +
                "id = " + id + 
				", type = " + type + 
				", wayId = " + wayId + 
				", storeId = " + storeId + 
				", money = " + money + 
				", createdTime = " + createdTime + 
				", descript = " + descript + 
				"}";
    }

}
