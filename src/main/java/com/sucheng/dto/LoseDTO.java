package com.sucheng.dto;

import java.util.Date;

/**
 * LoseDTO数据传输对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class LoseDTO extends BaseDTO {

    private static final long serialVersionUID = -9223372036238345513L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private Integer proId;
	/**
	*
	*/
	private Integer rawId;
	/**
	*损失数量
	*/
	private Float count;
	/**
	*原因
	*/
	private String reason;
	/**
	*处理状态
	*/
	private String status;
	/**
	*
	*/
	private Date createdTime;
	
    public LoseDTO () {}

    public LoseDTO (Integer id, Integer proId, Integer rawId, Float count, String reason, String status, Date createdTime) {
        this.id = id;
		this.proId = proId;
		this.rawId = rawId;
		this.count = count;
		this.reason = reason;
		this.status = status;
		this.createdTime = createdTime;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public Integer getRawId() {
		return rawId;
	}

	public void setRawId(Integer rawId) {
		this.rawId = rawId;
	}

	public Float getCount() {
		return count;
	}

	public void setCount(Float count) {
		this.count = count;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
        return "LoseDO{" +
                "id = " + id + 
				", proId = " + proId + 
				", rawId = " + rawId + 
				", count = " + count + 
				", reason = " + reason + 
				", status = " + status + 
				", createdTime = " + createdTime + 
				"}";
    }

}
