package com.sucheng.service.impl;

import com.sucheng.dao.MoneyDAO;
import com.sucheng.dos.MoneyDO;
import com.sucheng.dto.MoneyDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * MoneyServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "moneyService")
public class MoneyServiceImpl extends AbstractBaseService implements MoneyService {

    private MoneyDAO moneyDAO;

    @Autowired
    public void setMoneyDAO(MoneyDAO moneyDAO) {
        super.setBaseDAO(moneyDAO);
        this.moneyDAO = moneyDAO;
    }

    @PostConstruct
    public void init() {
        super.init(MoneyDO.class, MoneyDTO.class);
    }
}
