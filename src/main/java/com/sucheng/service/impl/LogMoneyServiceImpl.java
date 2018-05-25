package com.sucheng.service.impl;

import com.sucheng.dao.LogMoneyDAO;
import com.sucheng.dos.LogMoneyDO;
import com.sucheng.dto.LogMoneyDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.LogMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * LogMoneyServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "logMoneyService")
public class LogMoneyServiceImpl extends AbstractBaseService implements LogMoneyService {

    private LogMoneyDAO logMoneyDAO;

    @Autowired
    public void setLogMoneyDAO(LogMoneyDAO logMoneyDAO) {
        super.setBaseDAO(logMoneyDAO);
        this.logMoneyDAO = logMoneyDAO;
    }

    @PostConstruct
    public void init() {
        super.init(LogMoneyDO.class, LogMoneyDTO.class);
    }
}
