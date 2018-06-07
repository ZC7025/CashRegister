package com.sucheng.service.impl;

import com.sucheng.common.BeanCopyUtils;
import com.sucheng.dao.FormulaDAO;
import com.sucheng.dao.ProductDAO;
import com.sucheng.dos.ProductDO;
import com.sucheng.dto.ProductDTO;
import com.sucheng.poi.ProductPoiImpl;
import com.sucheng.query.ProductQuery;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.ProductService;
import com.sucheng.vo.FormulaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
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

    @Override
    public Integer proExport(Integer storeId, HttpServletRequest request) {
        try {
            List<ProductQuery> productQueryList = new ArrayList<>();
            List<Object> products = productDAO.listAllById(storeId);
            if(products.size() == 0) {
                return 0;
            }
            for(Object object : products) {
                productQueryList.add((ProductQuery) object);
            }
            ProductPoiImpl productPoi = new ProductPoiImpl();
            String[] headStr = new String[]{"商品id","商品名称", "口味", "商品类型", "单位", "价格"
                    , "图片1", "图片2", "图片3", "图片4", "优先级", "状态", "添加时间"};
            FileSystemView fsv = FileSystemView.getFileSystemView();
            //读取桌面路径的方法
            File com=fsv.getHomeDirectory();
            productPoi.writeExcel(request,com.getPath() + "/商品列表.xlsx", productQueryList, "商品列表", headStr);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
