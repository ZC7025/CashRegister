package com.sucheng.service.impl;

import com.sucheng.dao.RawMaterialsDAO;
import com.sucheng.dos.RawMaterialsDO;
import com.sucheng.dto.RawMaterialsDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.RawMaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * RawMaterialsServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "rawMaterialsService")
public class RawMaterialsServiceImpl extends AbstractBaseService implements RawMaterialsService {

    private RawMaterialsDAO rawMaterialsDAO;

    @Autowired
    public void setRawMaterialsDAO(RawMaterialsDAO rawMaterialsDAO) {
        super.setBaseDAO(rawMaterialsDAO);
        this.rawMaterialsDAO = rawMaterialsDAO;
    }

    @PostConstruct
    public void init() {
        super.init(RawMaterialsDO.class, RawMaterialsDTO.class);
    }
}
