package com.sucheng.service.impl;

import com.sucheng.dao.ProTypeDAO;
import com.sucheng.dos.ProTypeDO;
import com.sucheng.dto.ProTypeDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.ProTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * ProTypeServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "proTypeService")
public class ProTypeServiceImpl extends AbstractBaseService implements ProTypeService {

    private ProTypeDAO proTypeDAO;

    @Autowired
    public void setProTypeDAO(ProTypeDAO proTypeDAO) {
        super.setBaseDAO(proTypeDAO);
        this.proTypeDAO = proTypeDAO;
    }

    @PostConstruct
    public void init() {
        super.init(ProTypeDO.class, ProTypeDTO.class);
    }

    @Override
    public List<Object> listAllById(Integer storeId) {
        return proTypeDAO.listAllById(storeId);
    }
}
