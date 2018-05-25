package com.sucheng.dto;

import java.util.Date;

/**
 * ProTypeDTO数据传输对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class ProTypeDTO extends BaseDTO {

    private static final long serialVersionUID = -9223372035216290077L;

    /**
	*
	*/
	private Integer id;
	/**
	*商品分类
	*/
	private String name;
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
	
    public ProTypeDTO () {}

    public ProTypeDTO (Integer id, String name, Integer storeId, String status, Date createdTime) {
        this.id = id;
		this.name = name;
		this.storeId = storeId;
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
        return "ProTypeDO{" +
                "id = " + id + 
				", name = " + name + 
				", storeId = " + storeId + 
				", status = " + status + 
				", createdTime = " + createdTime + 
				"}";
    }

}
