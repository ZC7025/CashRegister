package com.sucheng.service.impl;

import com.sucheng.dao.PayWayDAO;
import com.sucheng.dos.PayWayDO;
import com.sucheng.dto.PayWayDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.PayWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * PayWayServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "payWayService")
public class PayWayServiceImpl extends AbstractBaseService implements PayWayService {

    private PayWayDAO payWayDAO;

    @Autowired
    public void setPayWayDAO(PayWayDAO payWayDAO) {
        super.setBaseDAO(payWayDAO);
        this.payWayDAO = payWayDAO;
    }

    @PostConstruct
    public void init() {
        super.init(PayWayDO.class, PayWayDTO.class);
    }
}
