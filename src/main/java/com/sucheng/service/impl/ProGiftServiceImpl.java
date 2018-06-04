package com.sucheng.service.impl;

import com.sucheng.dao.ProGiftDAO;
import com.sucheng.dos.ProGiftDO;
import com.sucheng.dto.ProGiftDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.ProGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * ProGiftServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-06-04<br/>
 *
 *
 * @version 1.0
 */
@Service(value = "proGiftService")
public class ProGiftServiceImpl extends AbstractBaseService implements ProGiftService {

    private ProGiftDAO proGiftDAO;

    @Autowired
    public void setProGiftDAO(ProGiftDAO proGiftDAO) {
        super.setBaseDAO(proGiftDAO);
        this.proGiftDAO = proGiftDAO;
    }

    @PostConstruct
    public void init() {
        super.init(ProGiftDO.class, ProGiftDTO.class);
    }
}
