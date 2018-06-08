package com.sucheng.service;

import com.sucheng.vo.StoreVO;

/**
 * StoreService服务接口<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public interface StoreService extends BaseService {
    StoreVO getByPhonePwd(String phone, String pwd);
    Integer hasPhoneEmail(String phone, String email);
}
