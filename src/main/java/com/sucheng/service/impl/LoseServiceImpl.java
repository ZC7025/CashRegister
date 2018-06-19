package com.sucheng.service.impl;

import com.sucheng.dao.LoseDAO;
import com.sucheng.dao.RawMaterialsDAO;
import com.sucheng.dao.StockDAO;
import com.sucheng.dao.StockOperatingDAO;
import com.sucheng.dos.LoseDO;
import com.sucheng.dos.RawMaterialsDO;
import com.sucheng.dos.StockDO;
import com.sucheng.dto.LoseDTO;
import com.sucheng.dto.StockDTO;
import com.sucheng.dto.StockOperatingDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.LoseService;
import com.sucheng.vo.EmployeeVO;
import com.sucheng.vo.LoseVO;
import com.sucheng.vo.StockOperatingVO;
import com.sucheng.vo.StockVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Calendar;

/**
 * LoseServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "loseService")
public class LoseServiceImpl extends AbstractBaseService implements LoseService {

    private LoseDAO loseDAO;
    private StockDAO stockDAO;
    private StockOperatingDAO stockOperatingDAO;
    private RawMaterialsDAO rawMaterialsDAO;

    @Autowired
    public void setStockDAO(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }
    @Autowired
    public void setStockOperatingDAO(StockOperatingDAO stockOperatingDAO) {
        this.stockOperatingDAO = stockOperatingDAO;
    }
    @Autowired
    public void setRawMaterialsDAO(RawMaterialsDAO rawMaterialsDAO) {
        this.rawMaterialsDAO = rawMaterialsDAO;
    }
    @Autowired
    public void setLoseDAO(LoseDAO loseDAO) {
        super.setBaseDAO(loseDAO);
        this.loseDAO = loseDAO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Object dataTransferObj) {
        try {
            LoseDTO loseVO = (LoseDTO) dataTransferObj;
            loseDAO.save(loseVO);
            if(loseVO.getRawId() != null) {
                StockDO stockDO = (StockDO) stockDAO.getById(loseVO.getRawId());
                // 执行库存修改
                StockVO stockVO = new StockVO();
                stockVO.setId(stockDO.getId());
                Float rawCount = stockDO.getCount() - loseVO.getCount();
                if(rawCount < 0) {
                    throw new Exception("输入的数有误，大于了库存剩余数量");
                }
                stockVO.setCount(rawCount);
                stockVO.setUpdateTime(Calendar.getInstance().getTime());
                synchronized (stockVO.getCount()) {
                    stockDAO.update(getBeanMapper().map(stockVO, StockDTO.class));
                }
                // 修改原料数量
                RawMaterialsDO rawMaterialsDO = new RawMaterialsDO();
                rawMaterialsDO.setId(loseVO.getRawId());
                rawMaterialsDO.setAmount(rawCount);
                rawMaterialsDAO.update(rawMaterialsDO);
                // 出库记录
                StockOperatingVO stockOperatingVO = new StockOperatingVO();
                stockOperatingVO.setOutStockCount(loseVO.getCount());
                stockOperatingVO.setRawId(loseVO.getRawId());
                stockOperatingVO.setUnitId(stockDO.getUnitId());
                EmployeeVO employeeVO = (EmployeeVO) SecurityUtils.getSubject().getSession().getAttribute("employee");
                if(employeeVO == null) {
                    stockOperatingVO.setEmpName("店长");
                } else {
                    stockOperatingVO.setEmpName(employeeVO.getRealName());
                }
                stockOperatingVO.setDescript(loseVO.getReason());
                stockOperatingDAO.save(getBeanMapper().map(stockOperatingVO, StockOperatingDTO.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() {
        super.init(LoseDO.class, LoseDTO.class);
    }
}
