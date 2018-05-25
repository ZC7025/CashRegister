package com.sucheng.service.impl;

import com.sucheng.dao.ProductDAO;
import com.sucheng.dos.ProductDO;
import com.sucheng.dto.ProductDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * ProductServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "productService")
public class ProductServiceImpl extends AbstractBaseService implements ProductService {

    private ProductDAO productDAO;

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        super.setBaseDAO(productDAO);
        this.productDAO = productDAO;
    }

    @PostConstruct
    public void init() {
        super.init(ProductDO.class, ProductDTO.class);
    }
}
