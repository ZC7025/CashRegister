package com.sucheng.vo;

/**
 * ProGiftVO值对象类<br/>
 *
 * 创建于2018-06-04<br/>
 *
 *
 * @version 1.0
 */
public class ProGiftVO extends BaseVO {

    private static final long serialVersionUID = -9223372036664573684L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private Integer proId;
	/**
	*
	*/
	private Integer giftId;
	/**
	*
	*/
	private Float count;
	
    public ProGiftVO () {}

    public ProGiftVO (Integer id, Integer proId, Integer giftId, Float count) {
        this.id = id;
		this.proId = proId;
		this.giftId = giftId;
		this.count = count;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public Integer getGiftId() {
		return giftId;
	}

	public void setGiftId(Integer giftId) {
		this.giftId = giftId;
	}

	public Float getCount() {
		return count;
	}

	public void setCount(Float count) {
		this.count = count;
	}

	
    @Override
    public String toString() {
        return "ProGiftDO{" +
                "id = " + id + 
				", proId = " + proId + 
				", giftId = " + giftId + 
				", count = " + count + 
				"}";
    }

}
