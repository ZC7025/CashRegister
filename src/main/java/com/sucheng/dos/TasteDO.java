package com.sucheng.dos;

import java.util.Date;

/**
 * TasteDO数据对象实体类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class TasteDO extends BaseDO {

    private static final long serialVersionUID = -9223372035234043097L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private String taste;
	/**
	*
	*/
	private String descript;
	/**
	 *
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
	
    public TasteDO () {}

    public TasteDO (Integer id, String taste, String descript, String status, Date createdTime) {
        this.id = id;
		this.taste = taste;
		this.descript = descript;
		this.status = status;
		this.createdTime = createdTime;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
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
        return "TasteDO{" +
                "id = " + id + 
				", taste = " + taste + 
				", descript = " + descript + 
				", status = " + status + 
				", createdTime = " + createdTime + 
				"}";
    }

}
