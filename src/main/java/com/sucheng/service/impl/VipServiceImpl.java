package com.sucheng.service.impl;

import com.sucheng.dao.VipDAO;
import com.sucheng.dos.VipDO;
import com.sucheng.dto.VipDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * VipServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "vipService")
public class VipServiceImpl extends AbstractBaseService implements VipService {

    private VipDAO vipDAO;

    @Autowired
    public void setVipDAO(VipDAO vipDAO) {
        super.setBaseDAO(vipDAO);
        this.vipDAO = vipDAO;
    }

    @PostConstruct
    public void init() {
        super.init(VipDO.class, VipDTO.class);
    }
}
