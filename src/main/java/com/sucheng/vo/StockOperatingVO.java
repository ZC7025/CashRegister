package com.sucheng.vo;

import java.util.Date;

/**
 * StockOperatingVO值对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class StockOperatingVO extends BaseVO {

    private static final long serialVersionUID = -9223372036127688152L;

    /**
	*
	*/
	private Integer id;
	/**
	*原料id
	*/
	private Integer rawId;
	/**
	*入库数量
	*/
	private Float inStockCount;
	/**
	*出库数量
	*/
	private Float outStockCount;
	/**
	*单位
	*/
	private String unit;
	/**
	*出入库员工
	*/
	private String empName;
	/**
	*操作时间
	*/
	private Date operatingTime;
	/**
	*
	*/
	private String descript;
	
    public StockOperatingVO () {}

    public StockOperatingVO (Integer id, Integer rawId, Float inStockCount, Float outStockCount, String unit, String empName, Date operatingTime, String descript) {
        this.id = id;
		this.rawId = rawId;
		this.inStockCount = inStockCount;
		this.outStockCount = outStockCount;
		this.unit = unit;
		this.empName = empName;
		this.operatingTime = operatingTime;
		this.descript = descript;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRawId() {
		return rawId;
	}

	public void setRawId(Integer rawId) {
		this.rawId = rawId;
	}

	public Float getInStockCount() {
		return inStockCount;
	}

	public void setInStockCount(Float inStockCount) {
		this.inStockCount = inStockCount;
	}

	public Float getOutStockCount() {
		return outStockCount;
	}

	public void setOutStockCount(Float outStockCount) {
		this.outStockCount = outStockCount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Date getOperatingTime() {
		return operatingTime;
	}

	public void setOperatingTime(Date operatingTime) {
		this.operatingTime = operatingTime;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	
    @Override
    public String toString() {
        return "StockOperatingDO{" +
                "id = " + id + 
				", rawId = " + rawId + 
				", inStockCount = " + inStockCount + 
				", outStockCount = " + outStockCount + 
				", unit = " + unit + 
				", empName = " + empName + 
				", operatingTime = " + operatingTime + 
				", descript = " + descript + 
				"}";
    }

}
