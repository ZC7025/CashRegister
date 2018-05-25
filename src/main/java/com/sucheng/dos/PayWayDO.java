package com.sucheng.dos;

/**
 * PayWayDO数据对象实体类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class PayWayDO extends BaseDO {

    private static final long serialVersionUID = -9223372035102920087L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private String payWay;
	/**
	*
	*/
	private String status;
	
    public PayWayDO () {}

    public PayWayDO (Integer id, String payWay, String status) {
        this.id = id;
		this.payWay = payWay;
		this.status = status;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
    @Override
    public String toString() {
        return "PayWayDO{" +
                "id = " + id + 
				", payWay = " + payWay + 
				", status = " + status + 
				"}";
    }

}
