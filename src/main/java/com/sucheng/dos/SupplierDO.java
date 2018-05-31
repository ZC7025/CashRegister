package com.sucheng.dos;

import java.util.Date;

/**
 * SupplierDO数据对象实体类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class SupplierDO extends BaseDO {

    private static final long serialVersionUID = -9223372036215037930L;

    /**
	*
	*/
	private Integer id;
	/**
	 * 所属门店
	 */
	private Integer storeId;
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
	
    public SupplierDO () {}

    public SupplierDO (Integer id, String name, String phone, String address, String defaults, String status, Date createdTime) {
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
