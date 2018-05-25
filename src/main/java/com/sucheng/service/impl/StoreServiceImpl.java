package com.sucheng.service.impl;

import com.sucheng.dao.StoreDAO;
import com.sucheng.dos.StoreDO;
import com.sucheng.dto.StoreDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * StoreServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "storeService")
public class StoreServiceImpl extends AbstractBaseService implements StoreService {

    private StoreDAO storeDAO;

    @Autowired
    public void setStoreDAO(StoreDAO storeDAO) {
        super.setBaseDAO(storeDAO);
        this.storeDAO = storeDAO;
    }

    @PostConstruct
    public void init() {
        super.init(StoreDO.class, StoreDTO.class);
    }
}
