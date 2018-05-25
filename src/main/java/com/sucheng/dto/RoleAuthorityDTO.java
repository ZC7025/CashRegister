package com.sucheng.dto;

/**
 * RoleAuthorityDTO数据传输对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class RoleAuthorityDTO extends BaseDTO {

    private static final long serialVersionUID = -9223372034855094871L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private Integer roleId;
	/**
	*
	*/
	private Integer authorityId;
	
    public RoleAuthorityDTO () {}

    public RoleAuthorityDTO (Integer id, Integer roleId, Integer authorityId) {
        this.id = id;
		this.roleId = roleId;
		this.authorityId = authorityId;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	
    @Override
    public String toString() {
        return "RoleAuthorityDO{" +
                "id = " + id + 
				", roleId = " + roleId + 
				", authorityId = " + authorityId + 
				"}";
    }

}
