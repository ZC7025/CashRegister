package com.sucheng.query;

import java.util.Date;

/**
 * GradeQuery查询对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class GradeQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372036016400569L;

    /**
	*
	*/
	private Integer id;
	/**
	*牌号名称
	*/
	private String gradeName;
	/**
	*座位数
	*/
	private Integer seat;
	/**
	*门店id
	*/
	private Integer storeId;
	/**
	*
	*/
	private String status;
	/**
	*
	*/
	private Date createdTime;
	
    public GradeQuery () {}

    public GradeQuery (Integer id, String gradeName, Integer seat, Integer storeId, String status, Date createdTime) {
        this.id = id;
		this.gradeName = gradeName;
		this.seat = seat;
		this.storeId = storeId;
		this.status = status;
		this.createdTime = createdTime;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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
        return "GradeDO{" +
                "}";
    }

}
