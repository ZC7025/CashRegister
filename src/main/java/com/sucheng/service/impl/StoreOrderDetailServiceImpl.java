package com.sucheng.service.impl;

import com.sucheng.dao.StoreOrderDetailDAO;
import com.sucheng.dos.StoreOrderDetailDO;
import com.sucheng.dto.StoreOrderDetailDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.StoreOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * StoreOrderDetailServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "storeOrderDetailService")
public class StoreOrderDetailServiceImpl extends AbstractBaseService implements StoreOrderDetailService {

    private StoreOrderDetailDAO storeOrderDetailDAO;

    @Autowired
    public void setStoreOrderDetailDAO(StoreOrderDetailDAO storeOrderDetailDAO) {
        super.setBaseDAO(storeOrderDetailDAO);
        this.storeOrderDetailDAO = storeOrderDetailDAO;
    }

    @PostConstruct
    public void init() {
        super.init(StoreOrderDetailDO.class, StoreOrderDetailDTO.class);
    }
}
