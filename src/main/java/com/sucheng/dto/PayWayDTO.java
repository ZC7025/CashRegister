package com.sucheng.dto;

/**
 * PayWayDTO数据传输对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class PayWayDTO extends BaseDTO {

    private static final long serialVersionUID = -9223372036689119137L;

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
	
    public PayWayDTO () {}

    public PayWayDTO (Integer id, String payWay, String status) {
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
