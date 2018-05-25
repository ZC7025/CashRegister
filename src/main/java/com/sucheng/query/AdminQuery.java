package com.sucheng.query;

import java.util.Date;

/**
 * AdminQuery查询对象类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public class AdminQuery extends BaseQuery {

    private static final long serialVersionUID = -9223372036612909443L;

    /**
	*
	*/
	private Integer id;
	/**
	*
	*/
	private String phone;
	/**
	*
	*/
	private String nickname;
	/**
	*
	*/
	private String pwd;
	/**
	*
	*/
	private String type;
	/**
	*
	*/
	private String status;
	/**
	*
	*/
	private Date createdTime;
	
    public AdminQuery () {}

    public AdminQuery (Integer id, String phone, String nickname, String pwd, String type, String status, Date createdTime) {
        this.id = id;
		this.phone = phone;
		this.nickname = nickname;
		this.pwd = pwd;
		this.type = type;
		this.status = status;
		this.createdTime = createdTime;
		
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	
    @Override
    public String toString() {
        return "AdminDO{" +
                "}";
    }

}
