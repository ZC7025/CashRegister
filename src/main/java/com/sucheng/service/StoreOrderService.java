package com.sucheng.service;

import com.sucheng.dto.StoreOrderDTO;

/**
 * StoreOrderService服务接口<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public interface StoreOrderService extends BaseService {

    void save(StoreOrderDTO storeOrderDTO, String details);
}
