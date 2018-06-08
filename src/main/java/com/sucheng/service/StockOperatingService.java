package com.sucheng.service;

import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.StockOperatingVO;

/**
 * StockOperatingService服务接口<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
public interface StockOperatingService extends BaseService {

    ControllerStatusVO save(StockOperatingVO stockOperatingVO, String ids);
}
