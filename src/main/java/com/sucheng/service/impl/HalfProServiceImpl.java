package com.sucheng.service.impl;

import com.sucheng.dao.HalfProDAO;
import com.sucheng.dos.HalfProDO;
import com.sucheng.dto.HalfProDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.HalfProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * HalfProServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "halfProService")
public class HalfProServiceImpl extends AbstractBaseService implements HalfProService {

    private HalfProDAO halfProDAO;

    @Autowired
    public void setHalfProDAO(HalfProDAO halfProDAO) {
        super.setBaseDAO(halfProDAO);
        this.halfProDAO = halfProDAO;
    }

    @PostConstruct
    public void init() {
        super.init(HalfProDO.class, HalfProDTO.class);
    }
}
