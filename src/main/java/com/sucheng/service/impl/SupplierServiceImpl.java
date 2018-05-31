package com.sucheng.service.impl;

import com.sucheng.dao.SupplierDAO;
import com.sucheng.dos.SupplierDO;
import com.sucheng.dto.SupplierDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * SupplierServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "supplierService")
public class SupplierServiceImpl extends AbstractBaseService implements SupplierService {

    private SupplierDAO supplierDAO;

    @Autowired
    public void setSupplierDAO(SupplierDAO supplierDAO) {
        super.setBaseDAO(supplierDAO);
        this.supplierDAO = supplierDAO;
    }

    @PostConstruct
    public void init() {
        super.init(SupplierDO.class, SupplierDTO.class);
    }

    @Override
    public void updateDefaultById(Integer storeId) {
        supplierDAO.updateDefaultById(storeId);
    }
}
