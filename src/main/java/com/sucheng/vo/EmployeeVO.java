package com.sucheng.vo;

import java.util.Date;

/**
 * EmployeeVO值对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class EmployeeVO extends BaseVO {

    private static final long serialVersionUID = -9223372034869876720L;

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
	private String status;
	/**
	*
	*/
	private Date createdTime;
	
    public EmployeeVO () {}

    public EmployeeVO (Integer id, String realName, String phone, String pwd, String email, String status, Date createdTime) {
        this.id = id;
		this.realName = realName;
		this.phone = phone;
		this.pwd = pwd;
		this.email = email;
		this.status = status;
		this.createdTime = createdTime;
		
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
