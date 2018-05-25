package com.sucheng.dto;

import java.util.Date;

/**
 * SupplierDTO数据传输对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class SupplierDTO extends BaseDTO {

    private static final long serialVersionUID = -9223372036069585874L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private String name;
	/**
	*供应商电话
	*/
	private String phone;
	/**
	*
	*/
	private String address;
	/**
	*是否为默认供应商
	*/
	private String defaults;
	/**
	*
	*/
	private String status;
	/**
	*
	*/
	private Date createdTime;
	
    public SupplierDTO () {}

    public SupplierDTO (Integer id, String name, String phone, String address, String defaults, String status, Date createdTime) {
        this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.defaults = defaults;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDefaults() {
		return defaults;
	}

	public void setDefaults(String defaults) {
		this.defaults = defaults;
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
        return "SupplierDO{" +
                "id = " + id + 
				", name = " + name + 
				", phone = " + phone + 
				", address = " + address + 
				", defaults = " + defaults + 
				", status = " + status + 
				", createdTime = " + createdTime + 
				"}";
    }

}
