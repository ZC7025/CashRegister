package com.sucheng.query;

import java.util.Date;

/**
 * LoseQuery查询对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class LoseQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372035723596775L;

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

	private Integer storeId;

	private String name;

	private String proName;

	private String searchType;
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
	
    public LoseQuery () {}

    public LoseQuery (Integer id, Integer proId, Integer rawId, Float count, String reason, String status, Date createdTime) {
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

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
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
                "}";
    }

}
