package com.sucheng.service;

import com.sucheng.vo.GiftVO;

/**
 * GiftService服务接口<br/>
 *
 * 创建于2018-06-04<br/>
 *
 *
 * @version 1.0
 */
public interface GiftService extends BaseService {

    void save(GiftVO giftVO, String proIds);
}
