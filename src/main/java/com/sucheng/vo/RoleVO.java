package com.sucheng.vo;

/**
 * RoleVO值对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class RoleVO extends BaseVO {

    private static final long serialVersionUID = -9223372034782994404L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private String roleName;
	/**
	*门店id
	*/
	private Integer storeId;
	/**
	*
	*/
	private String descript;
	
    public RoleVO () {}

    public RoleVO (Integer id, String roleName, Integer storeId, String descript) {
        this.id = id;
		this.roleName = roleName;
		this.storeId = storeId;
		this.descript = descript;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
        return "RoleDO{" +
                "id = " + id + 
				", roleName = " + roleName + 
				", storeId = " + storeId + 
				", descript = " + descript + 
				"}";
    }

}
