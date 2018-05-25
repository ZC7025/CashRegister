package com.sucheng.service.impl;

import com.sucheng.dao.AuthorityDAO;
import com.sucheng.dos.AuthorityDO;
import com.sucheng.dto.AuthorityDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * AuthorityServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "authorityService")
public class AuthorityServiceImpl extends AbstractBaseService implements AuthorityService {

    private AuthorityDAO authorityDAO;

    @Autowired
    public void setAuthorityDAO(AuthorityDAO authorityDAO) {
        super.setBaseDAO(authorityDAO);
        this.authorityDAO = authorityDAO;
    }

    @PostConstruct
    public void init() {
        super.init(AuthorityDO.class, AuthorityDTO.class);
    }
}
