package com.sucheng.service.impl;

import com.sucheng.dao.EmployeeDAO;
import com.sucheng.dos.EmployeeDO;
import com.sucheng.dto.EmployeeDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * EmployeeServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "employeeService")
public class EmployeeServiceImpl extends AbstractBaseService implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        super.setBaseDAO(employeeDAO);
        this.employeeDAO = employeeDAO;
    }

    @PostConstruct
    public void init() {
        super.init(EmployeeDO.class, EmployeeDTO.class);
    }

    @Override
    public Integer hasPhoneEmail(String phone, String email) {
        return employeeDAO.hasPhoneEmail(phone, email);
    }

    @Override
    public List<Object> listAllById(Integer storeId) {
        return employeeDAO.listAllById(storeId);
    }


}
