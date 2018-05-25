package com.sucheng.service.impl;

import com.sucheng.dao.StoreOrderDAO;
import com.sucheng.dos.StoreOrderDO;
import com.sucheng.dto.StoreOrderDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.StoreOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * StoreOrderServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "storeOrderService")
public class StoreOrderServiceImpl extends AbstractBaseService implements StoreOrderService {

    private StoreOrderDAO storeOrderDAO;

    @Autowired
    public void setStoreOrderDAO(StoreOrderDAO storeOrderDAO) {
        super.setBaseDAO(storeOrderDAO);
        this.storeOrderDAO = storeOrderDAO;
    }

    @PostConstruct
    public void init() {
        super.init(StoreOrderDO.class, StoreOrderDTO.class);
    }
}
