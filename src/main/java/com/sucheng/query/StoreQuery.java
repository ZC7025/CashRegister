package com.sucheng.query;

import java.util.Date;

/**
 * StoreQuery查询对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class StoreQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372035458141640L;

    /**
	*
	*/
	private Integer id;
	/**
	*门店账号
	*/
	private String storeId;
	/**
	*
	*/
	private String name;
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
	private String address;
	/**
	*营业执照图片
	*/
	private String licenseImg;
	/**
	*行业id
	*/
	private Integer industryId;
	/**
	 * 行业名称
	 */
	private Integer industryName;
	/**
	*总部id
	*/
	private Integer generalId;
	/**
	 * 总部名称
	 */
	private Integer generalName;
	/**
	*
	*/
	private String status;
	/**
	*
	*/
	private Date createdTime;
	
    public StoreQuery () {}

    public StoreQuery (Integer id, String storeId, String name, String phone, String pwd, String email, String address, String licenseImg, Integer industryId, Integer generalId, String status, Date createdTime) {
        this.id = id;
		this.storeId = storeId;
		this.name = name;
		this.phone = phone;
		this.pwd = pwd;
		this.email = email;
		this.address = address;
		this.licenseImg = licenseImg;
		this.industryId = industryId;
		this.generalId = generalId;
		this.status = status;
		this.createdTime = createdTime;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLicenseImg() {
		return licenseImg;
	}

	public void setLicenseImg(String licenseImg) {
		this.licenseImg = licenseImg;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public Integer getGeneralId() {
		return generalId;
	}

	public void setGeneralId(Integer generalId) {
		this.generalId = generalId;
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

	public Integer getIndustryName() {
		return industryName;
	}

	public void setIndustryName(Integer industryName) {
		this.industryName = industryName;
	}

	public Integer getGeneralName() {
		return generalName;
	}

	public void setGeneralName(Integer generalName) {
		this.generalName = generalName;
	}

	@Override
    public String toString() {
        return "StoreDO{" +
                "}";
    }

}
