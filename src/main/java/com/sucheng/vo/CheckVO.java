package com.sucheng.vo;

import java.util.Date;

/**
 * CheckVO值对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class CheckVO extends BaseVO {

    private static final long serialVersionUID = -9223372036208852158L;

    /**
	*
	*/
	private Integer id;
	/**
	*盘点编号
	*/
	private String checkNo;
	/**
	*所盘原料id
	*/
	private Integer rawId;
	/**
	*库存数
	*/
	private Float stockCount;
	/**
	*实盘数
	*/
	private Float realCount;
	/**
	*差距数
	*/
	private Float gapCount;
	/**
	*盘点员工
	*/
	private String checkEmp;
	/**
	*盘点时间
	*/
	private Date checkTime;
	/**
	*
	*/
	private String status;
	/**
	*
	*/
	private String descript;
	
    public CheckVO () {}

    public CheckVO (Integer id, String checkNo, Integer rawId, Float stockCount, Float realCount, Float gapCount, String checkEmp, Date checkTime, String status, String descript) {
        this.id = id;
		this.checkNo = checkNo;
		this.rawId = rawId;
		this.stockCount = stockCount;
		this.realCount = realCount;
		this.gapCount = gapCount;
		this.checkEmp = checkEmp;
		this.checkTime = checkTime;
		this.status = status;
		this.descript = descript;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public Integer getRawId() {
		return rawId;
	}

	public void setRawId(Integer rawId) {
		this.rawId = rawId;
	}

	public Float getStockCount() {
		return stockCount;
	}

	public void setStockCount(Float stockCount) {
		this.stockCount = stockCount;
	}

	public Float getRealCount() {
		return realCount;
	}

	public void setRealCount(Float realCount) {
		this.realCount = realCount;
	}

	public Float getGapCount() {
		return gapCount;
	}

	public void setGapCount(Float gapCount) {
		this.gapCount = gapCount;
	}

	public String getCheckEmp() {
		return checkEmp;
	}

	public void setCheckEmp(String checkEmp) {
		this.checkEmp = checkEmp;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	
    @Override
    public String toString() {
        return "CheckDO{" +
                "id = " + id + 
				", checkNo = " + checkNo + 
				", rawId = " + rawId + 
				", stockCount = " + stockCount + 
				", realCount = " + realCount + 
				", gapCount = " + gapCount + 
				", checkEmp = " + checkEmp + 
				", checkTime = " + checkTime + 
				", status = " + status + 
				", descript = " + descript + 
				"}";
    }

}
