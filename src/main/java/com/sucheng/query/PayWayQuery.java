package com.sucheng.query;

/**
 * PayWayQuery查询对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class PayWayQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372036686950889L;

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
	
    public PayWayQuery () {}

    public PayWayQuery (Integer id, String payWay, String status) {
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
                "}";
    }

}
