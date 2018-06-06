package com.sucheng.service;

import java.util.List;

/**
 * SupplierService服务接口<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public interface SupplierService extends BaseService {

    /**
     * 根据storeId来更新所有该门店下的默认供应商为非默认
     */
    void updateDefaultById(Integer storeId);

    List<Object> listAllById(Integer storeId);
}
