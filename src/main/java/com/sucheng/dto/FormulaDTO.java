package com.sucheng.dto;

import java.util.Date;

/**
 * FormulaDTO数据传输对象类<br/>
 *
 * 创建于2018-06-05<br/>
 *
 *
 * @version 1.0
 */
public class FormulaDTO extends BaseDTO {

    private static final long serialVersionUID = -9223372034789151083L;

    /**
	*
	*/
	private Integer id;
	/**
	*配方名
	*/
	private Integer rawId;
	/**
	*商品id
	*/
	private Integer proId;
	/**
	*
	*/
	private Float count;
	/**
	*描述
	*/
	private String descript;
	/**
	*
	*/
	private String status;
	/**
	*
	*/
	private Date createdTime;

	private String name;
	private String unit;
	private Integer storeId;

    public FormulaDTO() {}

    public FormulaDTO(Integer id, Integer rawId, Integer proId, Float count, String descript, String status, Date createdTime) {
        this.id = id;
		this.rawId = rawId;
		this.proId = proId;
		this.count = count;
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

	public Integer getRawId() {
		return rawId;
	}

	public void setRawId(Integer rawId) {
		this.rawId = rawId;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public Float getCount() {
		return count;
	}

	public void setCount(Float count) {
		this.count = count;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	@Override
    public String toString() {
        return "FormulaDO{" +
                "id = " + id + 
				", rawId = " + rawId + 
				", proId = " + proId + 
				", count = " + count + 
				", descript = " + descript + 
				", status = " + status + 
				", createdTime = " + createdTime + 
				"}";
    }

}
