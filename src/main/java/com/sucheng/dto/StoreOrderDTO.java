package com.sucheng.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * StoreOrderDTO数据传输对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class StoreOrderDTO extends BaseDTO {

    private static final long serialVersionUID = -9223372036197271598L;

    /**
	*
	*/
	private Integer id;
	/**
	*订单编号
	*/
	private String orderNo;
	/**
	*桌牌号
	*/
	private Integer gradeId;

	private String gradeNo;
	/**
	*负责员工
	*/
	private Integer empId;
	/**
	*客人个数
	*/
	private Integer peopleCount;
	/**
	*总支付金额
	*/
	private BigDecimal totalMoney;
	/**
	*
	*/
	private String status;
	/**
	*
	*/
	private Date createdTime;
	
    public StoreOrderDTO () {}

    public StoreOrderDTO (Integer id, String orderNo, Integer gradeId, Integer empId, Integer peopleCount, BigDecimal totalMoney, String status, Date createdTime) {
        this.id = id;
		this.orderNo = orderNo;
		this.gradeId = gradeId;
		this.empId = empId;
		this.peopleCount = peopleCount;
		this.totalMoney = totalMoney;
		this.status = status;
		this.createdTime = createdTime;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(String gradeNo) {
		this.gradeNo = gradeNo;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
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
        return "StoreOrderDO{" +
                "id = " + id + 
				", orderNo = " + orderNo + 
				", gradeId = " + gradeId +
				", empId = " + empId + 
				", peopleCount = " + peopleCount + 
				", totalMoney = " + totalMoney + 
				", status = " + status + 
				", createdTime = " + createdTime + 
				"}";
    }

}
