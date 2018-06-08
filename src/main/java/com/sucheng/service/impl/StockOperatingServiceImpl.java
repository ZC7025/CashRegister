package com.sucheng.service.impl;

import com.sucheng.dao.RawMaterialsDAO;
import com.sucheng.dao.StockDAO;
import com.sucheng.dao.StockOperatingDAO;
import com.sucheng.dos.RawMaterialsDO;
import com.sucheng.dos.StockDO;
import com.sucheng.dos.StockOperatingDO;
import com.sucheng.dto.StockOperatingDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.StockOperatingService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.RawMaterialsVO;
import com.sucheng.vo.StockOperatingVO;
import com.sucheng.vo.StockVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Calendar;

/**
 * StockOperatingServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "stockOperatingService")
public class StockOperatingServiceImpl extends AbstractBaseService implements StockOperatingService {

    private StockOperatingDAO stockOperatingDAO;
    private StockDAO stockDAO;
    private RawMaterialsDAO rawMaterialsDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ControllerStatusVO save(StockOperatingVO stockOperatingVO, String ids) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            String[] giftCount = ids.split(",");
            for (String giftCounts : giftCount) {
                String[] pros = giftCounts.split("-");
                Integer rawId = Integer.valueOf(pros[0]);
                Float outStock = Float.valueOf(pros[1]);
                Integer unitId = Integer.valueOf(pros[2]);
                StockDO stockDO = (StockDO)stockDAO.getById(rawId);
                if(outStock > stockDO.getCount() ) {
                    statusVO.errorStatus(500, "库存数量不足");
                    return statusVO;
                } else {
                    // 添加出库记录
                    StockOperatingVO stockOperatingVO1 = new StockOperatingVO(null, rawId,
                            null, outStock, unitId, stockOperatingVO.getEmpName(),
                            stockOperatingVO.getDescript());
                    stockOperatingDAO.save(stockOperatingVO1);
                    // 修改库存信息
                    StockVO stockVO = new StockVO();
                    stockVO.setId(stockDO.getId());
                    stockVO.setUpdateTime(Calendar.getInstance().getTime());
                    synchronized (stockDO.getCount()) {
                        stockVO.setCount(stockDO.getCount()-outStock);
                        stockDAO.update(stockVO);
                    }
                    //修改原料数量
                    RawMaterialsDO rawMaterialsDO = new RawMaterialsDO();
                    rawMaterialsDO.setId(rawId);
                    synchronized (stockVO.getCount()) {
                        rawMaterialsDO.setAmount(stockVO.getCount());
                        rawMaterialsDAO.update(rawMaterialsDO);
                    }
                }
            }
        } catch (Exception e) {
            statusVO.errorStatus(500, "领料失败");
            e.printStackTrace();
            return statusVO;
        }
        statusVO.okStatus(0, "领料成功");
        return statusVO;
    }

    @Autowired
    public void setStockOperatingDAO(StockOperatingDAO stockOperatingDAO) {
        super.setBaseDAO(stockOperatingDAO);
        this.stockOperatingDAO = stockOperatingDAO;
    }

    @Autowired
    public void setStockDAO(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }

    @Autowired
    public void setRawMaterialsDAO(RawMaterialsDAO rawMaterialsDAO) {
        this.rawMaterialsDAO = rawMaterialsDAO;
    }

    @PostConstruct
    public void init() {
        super.init(StockOperatingDO.class, StockOperatingDTO.class);
    }

}
