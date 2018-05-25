package com.sucheng.service.impl;

import com.sucheng.dao.LoseDAO;
import com.sucheng.dos.LoseDO;
import com.sucheng.dto.LoseDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.LoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * LoseServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "loseService")
public class LoseServiceImpl extends AbstractBaseService implements LoseService {

    private LoseDAO loseDAO;

    @Autowired
    public void setLoseDAO(LoseDAO loseDAO) {
        super.setBaseDAO(loseDAO);
        this.loseDAO = loseDAO;
    }

    @PostConstruct
    public void init() {
        super.init(LoseDO.class, LoseDTO.class);
    }
}
