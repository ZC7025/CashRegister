package com.sucheng.service.impl;

import com.sucheng.dao.StockDAO;
import com.sucheng.dos.StockDO;
import com.sucheng.dto.StockDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * StockServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "stockService")
public class StockServiceImpl extends AbstractBaseService implements StockService {

    private StockDAO stockDAO;

    @Autowired
    public void setStockDAO(StockDAO stockDAO) {
        super.setBaseDAO(stockDAO);
        this.stockDAO = stockDAO;
    }

    @PostConstruct
    public void init() {
        super.init(StockDO.class, StockDTO.class);
    }
}
