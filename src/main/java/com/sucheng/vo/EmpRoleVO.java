package com.sucheng.vo;

/**
 * EmpRoleVO值对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class EmpRoleVO extends BaseVO {

    private static final long serialVersionUID = -9223372035746253689L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private Integer empId;
	/**
	*
	*/
	private Integer roleId;

	private Integer adminId;
	
    public EmpRoleVO () {}

    public EmpRoleVO (Integer id, Integer empId, Integer roleId, Integer adminId) {
        this.id = id;
		this.empId = empId;
		this.roleId = roleId;
		this.adminId = adminId;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	@Override
    public String toString() {
        return "EmpRoleDO{" +
                "id = " + id + 
				", empId = " + empId + 
				", roleId = " + roleId +
				", adminId = " + adminId +
				"}";
    }

}
