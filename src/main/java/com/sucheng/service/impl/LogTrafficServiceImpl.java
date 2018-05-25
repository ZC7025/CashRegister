package com.sucheng.service.impl;

import com.sucheng.dao.LogTrafficDAO;
import com.sucheng.dos.LogTrafficDO;
import com.sucheng.dto.LogTrafficDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.LogTrafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * LogTrafficServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "logTrafficService")
public class LogTrafficServiceImpl extends AbstractBaseService implements LogTrafficService {

    private LogTrafficDAO logTrafficDAO;

    @Autowired
    public void setLogTrafficDAO(LogTrafficDAO logTrafficDAO) {
        super.setBaseDAO(logTrafficDAO);
        this.logTrafficDAO = logTrafficDAO;
    }

    @PostConstruct
    public void init() {
        super.init(LogTrafficDO.class, LogTrafficDTO.class);
    }
}
