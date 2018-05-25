package com.sucheng.service.impl;

import com.sucheng.dao.TasteDAO;
import com.sucheng.dos.TasteDO;
import com.sucheng.dto.TasteDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.TasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * TasteServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "tasteService")
public class TasteServiceImpl extends AbstractBaseService implements TasteService {

    private TasteDAO tasteDAO;

    @Autowired
    public void setTasteDAO(TasteDAO tasteDAO) {
        super.setBaseDAO(tasteDAO);
        this.tasteDAO = tasteDAO;
    }

    @PostConstruct
    public void init() {
        super.init(TasteDO.class, TasteDTO.class);
    }
}
