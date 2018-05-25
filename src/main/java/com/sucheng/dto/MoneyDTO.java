package com.sucheng.dto;

import java.math.BigDecimal;

/**
 * MoneyDTO数据传输对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class MoneyDTO extends BaseDTO {

    private static final long serialVersionUID = -9223372034773983001L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private BigDecimal money;
	/**
	*
	*/
	private Integer storeId;
	
    public MoneyDTO () {}

    public MoneyDTO (Integer id, BigDecimal money, Integer storeId) {
        this.id = id;
		this.money = money;
		this.storeId = storeId;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	
    @Override
    public String toString() {
        return "MoneyDO{" +
                "id = " + id + 
				", money = " + money + 
				", storeId = " + storeId + 
				"}";
    }

}
