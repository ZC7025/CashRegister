package com.sucheng.service.impl;

import com.sucheng.dao.AdminDAO;
import com.sucheng.dos.AdminDO;
import com.sucheng.dto.AdminDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.AdminService;
import com.sucheng.vo.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * AdminServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "adminService")
public class AdminServiceImpl extends AbstractBaseService implements AdminService {

    private AdminDAO adminDAO;

    @Autowired
    public void setAdminDAO(AdminDAO adminDAO) {
        super.setBaseDAO(adminDAO);
        this.adminDAO = adminDAO;
    }

    @PostConstruct
    public void init() {
        super.init(AdminDO.class, AdminDTO.class);
    }

    @Override
    public AdminVO getByPhonePwd(String phone, String pwd) {
        return adminDAO.getByPhonePwd(phone, pwd);
    }
}
