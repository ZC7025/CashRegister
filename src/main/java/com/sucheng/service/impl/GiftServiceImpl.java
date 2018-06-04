package com.sucheng.service.impl;

import com.sucheng.dao.GiftDAO;
import com.sucheng.dos.GiftDO;
import com.sucheng.dto.GiftDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * GiftServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-06-04<br/>
 *
 *
 * @version 1.0
 */
@Service(value = "giftService")
public class GiftServiceImpl extends AbstractBaseService implements GiftService {

    private GiftDAO giftDAO;

    @Autowired
    public void setGiftDAO(GiftDAO giftDAO) {
        super.setBaseDAO(giftDAO);
        this.giftDAO = giftDAO;
    }

    @PostConstruct
    public void init() {
        super.init(GiftDO.class, GiftDTO.class);
    }
}
