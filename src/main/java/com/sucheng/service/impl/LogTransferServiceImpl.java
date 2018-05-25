package com.sucheng.service.impl;

import com.sucheng.dao.LogTransferDAO;
import com.sucheng.dos.LogTransferDO;
import com.sucheng.dto.LogTransferDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.LogTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * LogTransferServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "logTransferService")
public class LogTransferServiceImpl extends AbstractBaseService implements LogTransferService {

    private LogTransferDAO logTransferDAO;

    @Autowired
    public void setLogTransferDAO(LogTransferDAO logTransferDAO) {
        super.setBaseDAO(logTransferDAO);
        this.logTransferDAO = logTransferDAO;
    }

    @PostConstruct
    public void init() {
        super.init(LogTransferDO.class, LogTransferDTO.class);
    }
}
