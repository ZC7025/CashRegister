package com.sucheng.service.impl;

import com.sucheng.dao.OrdersDAO;
import com.sucheng.dos.OrdersDO;
import com.sucheng.dto.OrdersDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * OrdersServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "ordersService")
public class OrdersServiceImpl extends AbstractBaseService implements OrdersService {

    private OrdersDAO ordersDAO;

    @Autowired
    public void setOrdersDAO(OrdersDAO ordersDAO) {
        super.setBaseDAO(ordersDAO);
        this.ordersDAO = ordersDAO;
    }

    @PostConstruct
    public void init() {
        super.init(OrdersDO.class, OrdersDTO.class);
    }
}
