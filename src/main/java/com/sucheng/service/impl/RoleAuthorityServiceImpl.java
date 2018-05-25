package com.sucheng.service.impl;

import com.sucheng.dao.RoleAuthorityDAO;
import com.sucheng.dos.RoleAuthorityDO;
import com.sucheng.dto.RoleAuthorityDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.RoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * RoleAuthorityServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "roleAuthorityService")
public class RoleAuthorityServiceImpl extends AbstractBaseService implements RoleAuthorityService {

    private RoleAuthorityDAO roleAuthorityDAO;

    @Autowired
    public void setRoleAuthorityDAO(RoleAuthorityDAO roleAuthorityDAO) {
        super.setBaseDAO(roleAuthorityDAO);
        this.roleAuthorityDAO = roleAuthorityDAO;
    }

    @PostConstruct
    public void init() {
        super.init(RoleAuthorityDO.class, RoleAuthorityDTO.class);
    }
}
