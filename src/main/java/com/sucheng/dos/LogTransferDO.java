package com.sucheng.dos;

import java.math.BigDecimal;
import java.util.Date;

/**
 * LogTransferDO数据对象实体类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class LogTransferDO extends BaseDO {

    private static final long serialVersionUID = -9223372036502573117L;

    /**
	*
	*/
	private Integer id;
	/**
	*开始时间
	*/
	private Date startTime;
	/**
	*结束时间
	*/
	private Date endTime;
	/**
	*
	*/
	private Integer empId;
	/**
	*
	*/
	private Integer storeId;
	/**
	*收银总额
	*/
	private BigDecimal cashCount;
	/**
	*现金支付
	*/
	private BigDecimal cashPay;
	/**
	*微信支付
	*/
	private BigDecimal wechatPay;
	/**
	*支付宝支付
	*/
	private BigDecimal aliPay;
	/**
	*银联支付
	*/
	private BigDecimal bankcardPay;
	/**
	*会员卡支付
	*/
	private BigDecimal vipCardPay;
	/**
	*次卡支付
	*/
	private BigDecimal aidCardPay;
	/**
	*其他
	*/
	private BigDecimal otherPay;
	/**
	*现金结余
	*/
	private BigDecimal cashRemaining;
	
    public LogTransferDO () {}

    public LogTransferDO (Integer id, Date startTime, Date endTime, Integer empId, Integer storeId, BigDecimal cashCount, BigDecimal cashPay, BigDecimal wechatPay, BigDecimal aliPay, BigDecimal bankcardPay, BigDecimal vipCardPay, BigDecimal aidCardPay, BigDecimal otherPay, BigDecimal cashRemaining) {
        this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.empId = empId;
		this.storeId = storeId;
		this.cashCount = cashCount;
		this.cashPay = cashPay;
		this.wechatPay = wechatPay;
		this.aliPay = aliPay;
		this.bankcardPay = bankcardPay;
		this.vipCardPay = vipCardPay;
		this.aidCardPay = aidCardPay;
		this.otherPay = otherPay;
		this.cashRemaining = cashRemaining;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public BigDecimal getCashCount() {
		return cashCount;
	}

	public void setCashCount(BigDecimal cashCount) {
		this.cashCount = cashCount;
	}

	public BigDecimal getCashPay() {
		return cashPay;
	}

	public void setCashPay(BigDecimal cashPay) {
		this.cashPay = cashPay;
	}

	public BigDecimal getWechatPay() {
		return wechatPay;
	}

	public void setWechatPay(BigDecimal wechatPay) {
		this.wechatPay = wechatPay;
	}

	public BigDecimal getAliPay() {
		return aliPay;
	}

	public void setAliPay(BigDecimal aliPay) {
		this.aliPay = aliPay;
	}

	public BigDecimal getBankcardPay() {
		return bankcardPay;
	}

	public void setBankcardPay(BigDecimal bankcardPay) {
		this.bankcardPay = bankcardPay;
	}

	public BigDecimal getVipCardPay() {
		return vipCardPay;
	}

	public void setVipCardPay(BigDecimal vipCardPay) {
		this.vipCardPay = vipCardPay;
	}

	public BigDecimal getAidCardPay() {
		return aidCardPay;
	}

	public void setAidCardPay(BigDecimal aidCardPay) {
		this.aidCardPay = aidCardPay;
	}

	public BigDecimal getOtherPay() {
		return otherPay;
	}

	public void setOtherPay(BigDecimal otherPay) {
		this.otherPay = otherPay;
	}

	public BigDecimal getCashRemaining() {
		return cashRemaining;
	}

	public void setCashRemaining(BigDecimal cashRemaining) {
		this.cashRemaining = cashRemaining;
	}

	
    @Override
    public String toString() {
        return "LogTransferDO{" +
                "id = " + id + 
				", startTime = " + startTime + 
				", endTime = " + endTime + 
				", empId = " + empId + 
				", storeId = " + storeId + 
				", cashCount = " + cashCount + 
				", cashPay = " + cashPay + 
				", wechatPay = " + wechatPay + 
				", aliPay = " + aliPay + 
				", bankcardPay = " + bankcardPay + 
				", vipCardPay = " + vipCardPay + 
				", aidCardPay = " + aidCardPay + 
				", otherPay = " + otherPay + 
				", cashRemaining = " + cashRemaining + 
				"}";
    }

}
