package com.sucheng.service;

import java.util.List;

/**
 * EmployeeService服务接口<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public interface EmployeeService extends BaseService {
    Integer hasPhoneEmail(String phone, String email);
    List<Object> listAllById(Integer storeId);
}
