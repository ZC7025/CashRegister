package com.sucheng.service.impl;

import com.sucheng.dao.LogMoneyDAO;
import com.sucheng.dao.MoneyDAO;
import com.sucheng.dao.StoreDAO;
import com.sucheng.dos.StoreDO;
import com.sucheng.dto.StoreDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.StoreService;
import com.sucheng.vo.LogMoneyVO;
import com.sucheng.vo.MoneyVO;
import com.sucheng.vo.StoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * StoreServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "storeService")
public class StoreServiceImpl extends AbstractBaseService implements StoreService {

    private StoreDAO storeDAO;
    private MoneyDAO moneyDAO;
    private LogMoneyDAO logMoneyDAO;

    @Override
    public StoreVO getByPhonePwd(String phone, String pwd) {
        return storeDAO.getByPhonePwd(phone, pwd);
    }

    @Override
    public Integer hasPhoneEmail(String phone, String email) {
        return storeDAO.hasPhoneEmail(phone, email);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(StoreVO storeVO, BigDecimal money) {
        storeDAO.save(storeVO);
        MoneyVO moneyVO = new MoneyVO();
        moneyVO.setStoreId(storeVO.getId());
        moneyVO.setMoney(money);
        moneyDAO.save(moneyVO);
        LogMoneyVO logMoneyVO = new LogMoneyVO();
        logMoneyVO.setMoney(money);
        logMoneyVO.setStoreId(storeVO.getId());
        logMoneyVO.setDescript("开店预算");
        logMoneyVO.setType(1);
        logMoneyVO.setWayId(1);
        logMoneyDAO.save(logMoneyVO);
    }

    @Autowired
    public void setStoreDAO(StoreDAO storeDAO) {
        super.setBaseDAO(storeDAO);
        this.storeDAO = storeDAO;
    }

    @Autowired
    public void setMoneyDAO(MoneyDAO moneyDAO) {
        this.moneyDAO = moneyDAO;
    }
    @Autowired
    public void setLogMoneyDAO(LogMoneyDAO logMoneyDAO) {
        this.logMoneyDAO = logMoneyDAO;
    }

    @PostConstruct
    public void init() {
        super.init(StoreDO.class, StoreDTO.class);
    }

}
