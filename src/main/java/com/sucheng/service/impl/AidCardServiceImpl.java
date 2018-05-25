package com.sucheng.service.impl;

import com.sucheng.dao.AidCardDAO;
import com.sucheng.dos.AidCardDO;
import com.sucheng.dto.AidCardDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.AidCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * AidCardServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "aidCardService")
public class AidCardServiceImpl extends AbstractBaseService implements AidCardService {

    private AidCardDAO aidCardDAO;

    @Autowired
    public void setAidCardDAO(AidCardDAO aidCardDAO) {
        super.setBaseDAO(aidCardDAO);
        this.aidCardDAO = aidCardDAO;
    }

    @PostConstruct
    public void init() {
        super.init(AidCardDO.class, AidCardDTO.class);
    }
}
