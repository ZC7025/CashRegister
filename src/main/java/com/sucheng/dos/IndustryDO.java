package com.sucheng.dos;

import java.util.Date;

/**
 * IndustryDO数据对象实体类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class IndustryDO extends BaseDO {

    private static final long serialVersionUID = -9223372035864315074L;

    /**
	*
	*/
	private Integer id;
	/**
	*行业名称
	*/
	private String name;
	/**
	*
	*/
	private String status;
	/**
	*
	*/
	private Date createdTime;
	
    public IndustryDO () {}

    public IndustryDO (Integer id, String name, String status, Date createdTime) {
        this.id = id;
		this.name = name;
		this.status = status;
		this.createdTime = createdTime;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
        return "IndustryDO{" +
                "id = " + id + 
				", name = " + name + 
				", status = " + status + 
				", createdTime = " + createdTime + 
				"}";
    }

}
