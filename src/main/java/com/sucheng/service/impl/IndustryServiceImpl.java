package com.sucheng.service.impl;

import com.sucheng.dao.IndustryDAO;
import com.sucheng.dos.IndustryDO;
import com.sucheng.dto.IndustryDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * IndustryServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "industryService")
public class IndustryServiceImpl extends AbstractBaseService implements IndustryService {

    private IndustryDAO industryDAO;

    @Autowired
    public void setIndustryDAO(IndustryDAO industryDAO) {
        super.setBaseDAO(industryDAO);
        this.industryDAO = industryDAO;
    }

    @PostConstruct
    public void init() {
        super.init(IndustryDO.class, IndustryDTO.class);
    }
}
