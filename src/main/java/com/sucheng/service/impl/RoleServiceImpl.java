package com.sucheng.service.impl;

import com.sucheng.dao.RoleDAO;
import com.sucheng.dos.RoleDO;
import com.sucheng.dto.RoleDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * RoleServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "roleService")
public class RoleServiceImpl extends AbstractBaseService implements RoleService {

    private RoleDAO roleDAO;

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        super.setBaseDAO(roleDAO);
        this.roleDAO = roleDAO;
    }

    @PostConstruct
    public void init() {
        super.init(RoleDO.class, RoleDTO.class);
    }
}
