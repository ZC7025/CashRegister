package com.sucheng.service.impl;

import com.sucheng.dao.StockOperatingDAO;
import com.sucheng.dos.StockOperatingDO;
import com.sucheng.dto.StockOperatingDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.StockOperatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * StockOperatingServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "stockOperatingService")
public class StockOperatingServiceImpl extends AbstractBaseService implements StockOperatingService {

    private StockOperatingDAO stockOperatingDAO;

    @Autowired
    public void setStockOperatingDAO(StockOperatingDAO stockOperatingDAO) {
        super.setBaseDAO(stockOperatingDAO);
        this.stockOperatingDAO = stockOperatingDAO;
    }

    @PostConstruct
    public void init() {
        super.init(StockOperatingDO.class, StockOperatingDTO.class);
    }
}
