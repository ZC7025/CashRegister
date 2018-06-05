package com.sucheng.service.impl;

import com.sucheng.dao.FormulaDAO;
import com.sucheng.dao.ProductDAO;
import com.sucheng.dos.ProductDO;
import com.sucheng.dto.ProductDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.ProductService;
import com.sucheng.vo.FormulaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

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
    private FormulaDAO formulaDAO;

    @Autowired
    public void setFormulaDAO(FormulaDAO formulaDAO) {
        this.formulaDAO = formulaDAO;
    }

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        super.setBaseDAO(productDAO);
        this.productDAO = productDAO;
    }

    @PostConstruct
    public void init() {
        super.init(ProductDO.class, ProductDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProductDTO productDTO, String formulaIds) {
        productDAO.save(productDTO);
        String[] giftCount = formulaIds.split(",");
        for (String giftCounts : giftCount) {
            String[] pros = giftCounts.split("-");
            if(pros.length < 3) {
                formulaDAO.save(new FormulaVO(null, Integer.valueOf(pros[0]),
                        productDTO.getId(), Float.valueOf(pros[1]),""));
            } else {
                formulaDAO.save(new FormulaVO(null, Integer.valueOf(pros[0]),
                        productDTO.getId(), Float.valueOf(pros[1]),pros[2]));
            }
        }
    }

    @Override
    public List<Object> listAllById(Integer storeId) {
        return productDAO.listAllById(storeId);
    }
}
