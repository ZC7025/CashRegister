package com.sucheng.service.impl;

import com.sucheng.dao.VipLevelDAO;
import com.sucheng.dos.VipLevelDO;
import com.sucheng.dto.VipLevelDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.VipLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * VipLevelServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "vipLevelService")
public class VipLevelServiceImpl extends AbstractBaseService implements VipLevelService {

    private VipLevelDAO vipLevelDAO;

    @Autowired
    public void setVipLevelDAO(VipLevelDAO vipLevelDAO) {
        super.setBaseDAO(vipLevelDAO);
        this.vipLevelDAO = vipLevelDAO;
    }

    @PostConstruct
    public void init() {
        super.init(VipLevelDO.class, VipLevelDTO.class);
    }
}
