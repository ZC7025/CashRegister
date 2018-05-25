package com.sucheng.query;

/**
 * AuthorityQuery查询对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class AuthorityQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372035936026275L;

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
	
    public AuthorityQuery () {}

    public AuthorityQuery (Integer id, String authorityName, Integer storeId, String descript) {
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
                "}";
    }

}
