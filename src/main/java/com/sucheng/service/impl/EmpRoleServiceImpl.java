package com.sucheng.service.impl;

import com.sucheng.dao.EmpRoleDAO;
import com.sucheng.dos.EmpRoleDO;
import com.sucheng.dto.EmpRoleDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.EmpRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * EmpRoleServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "empRoleService")
public class EmpRoleServiceImpl extends AbstractBaseService implements EmpRoleService {

    private EmpRoleDAO empRoleDAO;

    @Autowired
    public void setEmpRoleDAO(EmpRoleDAO empRoleDAO) {
        super.setBaseDAO(empRoleDAO);
        this.empRoleDAO = empRoleDAO;
    }

    @PostConstruct
    public void init() {
        super.init(EmpRoleDO.class, EmpRoleDTO.class);
    }
}
