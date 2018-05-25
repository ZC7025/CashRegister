package com.sucheng.service;

import com.sucheng.vo.AdminVO;

/**
 * AdminService服务接口<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public interface AdminService extends BaseService {

    AdminVO getByPhonePwd(String phone, String pwd);
}
