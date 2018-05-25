package com.sucheng.service.impl;

import com.sucheng.dao.VipManageDAO;
import com.sucheng.dos.VipManageDO;
import com.sucheng.dto.VipManageDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.VipManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * VipManageServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "vipManageService")
public class VipManageServiceImpl extends AbstractBaseService implements VipManageService {

    private VipManageDAO vipManageDAO;

    @Autowired
    public void setVipManageDAO(VipManageDAO vipManageDAO) {
        super.setBaseDAO(vipManageDAO);
        this.vipManageDAO = vipManageDAO;
    }

    @PostConstruct
    public void init() {
        super.init(VipManageDO.class, VipManageDTO.class);
    }
}
