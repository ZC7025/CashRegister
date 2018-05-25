package com.sucheng.query;

/**
 * EmpRoleQuery查询对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class EmpRoleQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372036342591279L;

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
	
    public EmpRoleQuery () {}

    public EmpRoleQuery (Integer id, Integer empId, Integer roleId) {
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
                "}";
    }

}
