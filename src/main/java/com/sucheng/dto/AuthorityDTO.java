package com.sucheng.dto;

/**
 * AuthorityDTO数据传输对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class AuthorityDTO extends BaseDTO {

    private static final long serialVersionUID = -9223372035792943405L;

    /**
	*
	*/
	private Integer id;
	/**
	*权限内容
	*/
	private String authorityName;
	/**
	*门店id
	*/
	private Integer storeId;
	/**
	*
	*/
	private String descript;
	
    public AuthorityDTO () {}

    public AuthorityDTO (Integer id, String authorityName, Integer storeId, String descript) {
        this.id = id;
		this.authorityName = authorityName;
		this.storeId = storeId;
		this.descript = descript;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
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

	
    @Override
    public String toString() {
        return "AuthorityDO{" +
                "id = " + id + 
				", authorityName = " + authorityName + 
				", storeId = " + storeId + 
				", descript = " + descript + 
				"}";
    }

}
