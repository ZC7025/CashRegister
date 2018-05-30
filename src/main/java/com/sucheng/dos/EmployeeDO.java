package com.sucheng.dos;

import java.util.Date;

/**
 * EmployeeDO数据对象实体类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class EmployeeDO extends BaseDO {

    private static final long serialVersionUID = -9223372035384208950L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private String realName;
	/**
	*
	*/
	private String phone;
	/**
	*
	*/
	private String pwd;
	/**
	*
	*/
	private String email;
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
	
    public EmployeeDO () {}

    public EmployeeDO (Integer id, String realName, String phone, String pwd, String email, Integer storeId, String status, Date createdTime) {
        this.id = id;
		this.realName = realName;
		this.phone = phone;
		this.pwd = pwd;
		this.email = email;
		this.status = status;
		this.createdTime = createdTime;
		this.storeId = storeId;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
        return "EmployeeDO{" +
                "id = " + id + 
				", realName = " + realName + 
				", phone = " + phone + 
				", pwd = " + pwd + 
				", email = " + email + 
				", status = " + status + 
				", createdTime = " + createdTime + 
				"}";
    }

}
