package com.sucheng.service.impl;

import com.sucheng.common.DateFormatUtils;
import com.sucheng.common.RandomUtils;
import com.sucheng.dao.StoreOrderDAO;
import com.sucheng.dao.StoreOrderDetailDAO;
import com.sucheng.dos.StoreOrderDO;
import com.sucheng.dto.StoreOrderDTO;
import com.sucheng.enums.RandomCodeEnum;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.StoreOrderService;
import com.sucheng.vo.StoreOrderDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * StoreOrderServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "storeOrderService")
public class StoreOrderServiceImpl extends AbstractBaseService implements StoreOrderService {

    private StoreOrderDAO storeOrderDAO;
    private StoreOrderDetailDAO storeOrderDetailDAO;

    @Autowired
    public void setStoreOrderDAO(StoreOrderDAO storeOrderDAO) {
        super.setBaseDAO(storeOrderDAO);
        this.storeOrderDAO = storeOrderDAO;
    }
    @Autowired
    public void setStoreOrderDetailDAO(StoreOrderDetailDAO storeOrderDetailDAO) {
        this.storeOrderDetailDAO = storeOrderDetailDAO;
    }

    @PostConstruct
    public void init() {
        super.init(StoreOrderDO.class, StoreOrderDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(StoreOrderDTO storeOrderDTO, String details) {
        // 生成订单编号
        storeOrderDTO.setOrderNo("SC"+ DateFormatUtils.format(Calendar.getInstance().getTime(),
                "yyyyMMddHHmm")+ RandomUtils.randomCode(RandomCodeEnum.NUMBER_CODE));
        // TODO 此处应该为员工操作，所以应使用员工id
        storeOrderDTO.setEmpId(1);
        storeOrderDAO.save(storeOrderDTO);
        String[] orderCounts = details.split(",");
        for (String orderCount : orderCounts) {
            String[] pros = orderCount.split("-");
            // o为id，1为价格，2为数量
            BigDecimal totalMoney = new BigDecimal(pros[1]).multiply(new BigDecimal(pros[2]));
            // TODO giftId为空，没想好添加商品套餐怎么操作，添加标识？
            storeOrderDetailDAO.save(new StoreOrderDetailVO(storeOrderDTO.getId(),Integer.valueOf(pros[0]),
                     null,Integer.valueOf(pros[2]), totalMoney));
        }
        // TODO 订单确认后应改桌牌状态为不可用
    }
}
