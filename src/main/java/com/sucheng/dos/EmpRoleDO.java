package com.sucheng.dos;

/**
 * EmpRoleDO数据对象实体类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class EmpRoleDO extends BaseDO {

    private static final long serialVersionUID = -9223372035181937137L;

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
	
    public EmpRoleDO () {}

    public EmpRoleDO (Integer id, Integer empId, Integer roleId) {
        this.id = id;
		this.empId = empId;
		this.roleId = roleId;
		
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

	
    @Override
    public String toString() {
        return "EmpRoleDO{" +
                "id = " + id + 
				", empId = " + empId + 
				", roleId = " + roleId + 
				"}";
    }

}
