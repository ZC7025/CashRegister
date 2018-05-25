package com.sucheng.service.impl;

import com.sucheng.dao.CheckDAO;
import com.sucheng.dos.CheckDO;
import com.sucheng.dto.CheckDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * CheckServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "checkService")
public class CheckServiceImpl extends AbstractBaseService implements CheckService {

    private CheckDAO checkDAO;

    @Autowired
    public void setCheckDAO(CheckDAO checkDAO) {
        super.setBaseDAO(checkDAO);
        this.checkDAO = checkDAO;
    }

    @PostConstruct
    public void init() {
        super.init(CheckDO.class, CheckDTO.class);
    }
}
