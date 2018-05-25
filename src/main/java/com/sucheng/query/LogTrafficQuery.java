package com.sucheng.query;

import java.math.BigDecimal;

/**
 * LogTrafficQuery查询对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class LogTrafficQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372035551487101L;

    /**
	*
	*/
	private Integer id;
	/**
	*销售类型
	*/
	private String type;
	/**
	*销售额
	*/
	private Integer saleCount;
	/**
	*售价
	*/
	private BigDecimal salePrice;
	/**
	*原价
	*/
	private BigDecimal originalPrice;
	/**
	*利润
	*/
	private BigDecimal profit;
	/**
	*
	*/
	private Integer proId;
	
    public LogTrafficQuery () {}

    public LogTrafficQuery (Integer id, String type, Integer saleCount, BigDecimal salePrice, BigDecimal originalPrice, BigDecimal profit, Integer proId) {
        this.id = id;
		this.type = type;
		this.saleCount = saleCount;
		this.salePrice = salePrice;
		this.originalPrice = originalPrice;
		this.profit = profit;
		this.proId = proId;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	
    @Override
    public String toString() {
        return "LogTrafficDO{" +
                "}";
    }

}
