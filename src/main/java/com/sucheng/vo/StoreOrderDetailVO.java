package com.sucheng.vo;

import java.math.BigDecimal;

/**
 * StoreOrderDetailVO值对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class StoreOrderDetailVO extends BaseVO {

    private static final long serialVersionUID = -9223372035398873394L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private Integer orderId;
	private Integer giftId;
	/**
	*
	*/
	private Integer proId;
	/**
	*
	*/
	private Integer count;
	/**
	*
	*/
	private BigDecimal totalMoney;
	
    public StoreOrderDetailVO () {}

    public StoreOrderDetailVO (Integer orderId, Integer proId, Integer count, BigDecimal totalMoney) {
        this.id = id;
		this.orderId = orderId;
		this.proId = proId;
		this.count = count;
		this.totalMoney = totalMoney;
    }

	public StoreOrderDetailVO (Integer orderId, Integer proId, Integer giftId, Integer count, BigDecimal totalMoney) {
		this.giftId = giftId;
		this.orderId = orderId;
		this.proId = proId;
		this.count = count;
		this.totalMoney = totalMoney;
	}

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getGiftId() {
		return giftId;
	}

	public void setGiftId(Integer giftId) {
		this.giftId = giftId;
	}

	@Override
    public String toString() {
        return "StoreOrderDetailDO{" +
                "id = " + id + 
				", orderId = " + orderId + 
				", proId = " + proId + 
				", count = " + count + 
				", totalMoney = " + totalMoney + 
				"}";
    }

}
