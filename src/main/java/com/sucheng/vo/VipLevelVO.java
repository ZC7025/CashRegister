package com.sucheng.vo;

/**
 * VipLevelVO值对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class VipLevelVO extends BaseVO {

    private static final long serialVersionUID = -9223372036399210511L;

    /**
	*
	*/
	private Integer id;
	/**
	*会员等级
	*/
	private Integer level;
	/**
	*折扣
	*/
	private Float discount;
	/**
	*是否使用积分
	*/
	private String useIntegral;
	/**
	*自动升等级积分
	*/
	private Integer autoLevelup;
	/**
	*门店id
	*/
	private Integer storeId;
	/**
	*
	*/
	private String descript;
	/**
	*
	*/
	private String status;
	
    public VipLevelVO () {}

    public VipLevelVO (Integer id, Integer level, Float discount, String useIntegral, Integer autoLevelup, Integer storeId, String descript, String status) {
        this.id = id;
		this.level = level;
		this.discount = discount;
		this.useIntegral = useIntegral;
		this.autoLevelup = autoLevelup;
		this.storeId = storeId;
		this.descript = descript;
		this.status = status;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public String getUseIntegral() {
		return useIntegral;
	}

	public void setUseIntegral(String useIntegral) {
		this.useIntegral = useIntegral;
	}

	public Integer getAutoLevelup() {
		return autoLevelup;
	}

	public void setAutoLevelup(Integer autoLevelup) {
		this.autoLevelup = autoLevelup;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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

	
    @Override
    public String toString() {
        return "VipLevelDO{" +
                "id = " + id + 
				", level = " + level + 
				", discount = " + discount + 
				", useIntegral = " + useIntegral + 
				", autoLevelup = " + autoLevelup + 
				", storeId = " + storeId + 
				", descript = " + descript + 
				", status = " + status + 
				"}";
    }

}
