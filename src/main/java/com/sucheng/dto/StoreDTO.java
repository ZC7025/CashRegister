package com.sucheng.dto;

import java.util.Date;

/**
 * StoreDTO数据传输对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class StoreDTO extends BaseDTO {

    private static final long serialVersionUID = -9223372034876094954L;

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
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 县
	 */
	private String county;

	/**
	 *详细地址
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
	 *总部id
	 */
	private Integer generalId;
	/**
	 * 行业名称
	 */
	private String industryName;
	/**
	 * 总部名称
	 */
	private String generalName;
	/**
	 *
	 */
	private String status;
	/**
	 *
	 */
	private Date createdTime;

	public StoreDTO () {}

	public StoreDTO (Integer id, String storeId, String name, String phone, String pwd, String email, String province, String city, String county, String address, String licenseImg, Integer industryId, Integer generalId, String status, Date createdTime) {
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
		this.city = city;
		this.province = province;
		this.county = county;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getGeneralName() {
		return generalName;
	}

	public void setGeneralName(String generalName) {
		this.generalName = generalName;
	}

	@Override
	public String toString() {
		return "StoreDO{" +
				"id = " + id +
				", storeId = " + storeId +
				", name = " + name +
				", phone = " + phone +
				", pwd = " + pwd +
				", email = " + email +
				", address = " + address +
				", licenseImg = " + licenseImg +
				", industryId = " + industryId +
				", generalId = " + generalId +
				", status = " + status +
				", createdTime = " + createdTime +
				"}";
	}

}
