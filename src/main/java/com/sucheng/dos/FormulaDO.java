package com.sucheng.dos;

import java.util.Date;

/**
 * FormulaDO数据对象实体类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class FormulaDO extends BaseDO {

    private static final long serialVersionUID = -9223372036367547606L;

    /**
	*
	*/
	private Integer id;
	/**
	*原材料名
	*/
	private String name;
	/**
	*单位
	*/
	private String unit;
	/**
	*商品id
	*/
	private Integer proId;
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
	
    public FormulaDO () {}

    public FormulaDO (Integer id, String name, String unit, Integer proId, String descript, String status, Date createdTime) {
        this.id = id;
		this.name = name;
		this.unit = unit;
		this.proId = proId;
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

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
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

	
    @Override
    public String toString() {
        return "FormulaDO{" +
                "id = " + id + 
				", name = " + name + 
				", unit = " + unit + 
				", proId = " + proId + 
				", descript = " + descript + 
				", status = " + status + 
				", createdTime = " + createdTime + 
				"}";
    }

}
