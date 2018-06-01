package com.sucheng.service.impl;

import com.sucheng.dao.UnitDAO;
import com.sucheng.dos.UnitDO;
import com.sucheng.dto.UnitDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * TasteServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "unitService")
public class UnitServiceImpl extends AbstractBaseService implements UnitService {

    private UnitDAO unitDAO;

    @Autowired
    public void setUnitDAO(UnitDAO unitDAO) {
        super.setBaseDAO(unitDAO);
        this.unitDAO = unitDAO;
    }

    @PostConstruct
    public void init() {
        super.init(UnitDO.class, UnitDTO.class);
    }
}
