package com.sucheng.vo;

import java.util.Date;

/**
 * FormulaVO值对象类<br/>
 *
 * 创建于2018-06-05<br/>
 *
 *
 * @version 1.0
 */
public class FormulaVO extends BaseVO {

    private static final long serialVersionUID = -9223372036445828772L;

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
	
    public FormulaVO() {}

    public FormulaVO(Integer id, Integer rawId, Integer proId, Float count, String descript) {
        this.id = id;
		this.rawId = rawId;
		this.proId = proId;
		this.count = count;
		this.descript = descript;
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
