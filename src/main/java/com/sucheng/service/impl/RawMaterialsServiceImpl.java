package com.sucheng.service.impl;

import com.sucheng.common.ExceptionUtils;
import com.sucheng.dao.RawMaterialsDAO;
import com.sucheng.dao.StockDAO;
import com.sucheng.dao.StockOperatingDAO;
import com.sucheng.dos.RawMaterialsDO;
import com.sucheng.dto.RawMaterialsDTO;
import com.sucheng.dto.StockDTO;
import com.sucheng.dto.StockOperatingDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.RawMaterialsService;
import com.sucheng.vo.EmployeeVO;
import com.sucheng.vo.StockOperatingVO;
import com.sucheng.vo.StockVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * RawMaterialsServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "rawMaterialsService")
public class RawMaterialsServiceImpl extends AbstractBaseService implements RawMaterialsService {

    private RawMaterialsDAO rawMaterialsDAO;
    private StockDAO stockDAO;
    private StockOperatingDAO stockOperatingDAO;

    @Autowired
    public void setRawMaterialsDAO(RawMaterialsDAO rawMaterialsDAO) {
        super.setBaseDAO(rawMaterialsDAO);
        this.rawMaterialsDAO = rawMaterialsDAO;
    }
    @Autowired
    public void setStockDAO(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }
    @Autowired
    public void setStockOperatingDAO(StockOperatingDAO stockOperatingDAO) {
        this.stockOperatingDAO = stockOperatingDAO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Object dataTransferObj) {
        RawMaterialsDTO rawMaterialsVO = (RawMaterialsDTO)dataTransferObj;
        try {
            rawMaterialsDAO.save(rawMaterialsVO);
            // 执行库存保存
            StockVO stockVO = new StockVO();
            stockVO.setCount(rawMaterialsVO.getAmount());
            stockVO.setPrice(rawMaterialsVO.getPrice());
            stockVO.setRawId(rawMaterialsVO.getId());
            stockVO.setUnitId(rawMaterialsVO.getUnitId());
            synchronized (stockVO.getCount()) {
                stockDAO.save(getBeanMapper().map(stockVO, StockDTO.class));
            }
            // 入库记录
            StockOperatingVO stockOperatingVO = new StockOperatingVO();
            stockOperatingVO.setInStockCount(rawMaterialsVO.getAmount());
            stockOperatingVO.setRawId(rawMaterialsVO.getId());
            stockOperatingVO.setUnitId(rawMaterialsVO.getUnitId());
            EmployeeVO employeeVO = (EmployeeVO) SecurityUtils.getSubject().getSession().getAttribute("employee");
            if(employeeVO == null) {
                stockOperatingVO.setEmpName("店长");
            } else {
                stockOperatingVO.setEmpName(employeeVO.getRealName());
            }
            stockOperatingDAO.save(getBeanMapper().map(stockOperatingVO, StockOperatingDTO.class));
        } catch (RuntimeException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @PostConstruct
    public void init() {
        super.init(RawMaterialsDO.class, RawMaterialsDTO.class);
    }

    @Override
    public List<Object> listAllLess(Integer storeId) {
        return rawMaterialsDAO.listAllLess(storeId);
    }
}
