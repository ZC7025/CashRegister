package com.sucheng.vo;

import java.util.Date;

/**
 * OrdersVO值对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class OrdersVO extends BaseVO {

    private static final long serialVersionUID = -9223372035510137672L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private Integer storeId;
	/**
	*
	*/
	private Integer supplieId;
	/**
	*
	*/
	private String listName;
	/**
	*订单状态
	*/
	private String status;
	/**
	*
	*/
	private Date orderTime;
	
    public OrdersVO () {}

    public OrdersVO (Integer id, Integer storeId, Integer supplieId, String listName, String status, Date orderTime) {
        this.id = id;
		this.storeId = storeId;
		this.supplieId = supplieId;
		this.listName = listName;
		this.status = status;
		this.orderTime = orderTime;
		
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

	public Integer getSupplieId() {
		return supplieId;
	}

	public void setSupplieId(Integer supplieId) {
		this.supplieId = supplieId;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	
    @Override
    public String toString() {
        return "OrdersDO{" +
                "id = " + id + 
				", storeId = " + storeId + 
				", supplieId = " + supplieId + 
				", listName = " + listName + 
				", status = " + status + 
				", orderTime = " + orderTime + 
				"}";
    }

}
