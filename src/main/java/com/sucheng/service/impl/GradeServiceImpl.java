package com.sucheng.service.impl;

import com.sucheng.dao.GradeDAO;
import com.sucheng.dos.GradeDO;
import com.sucheng.dto.GradeDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * GradeServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Service(value = "gradeService")
public class GradeServiceImpl extends AbstractBaseService implements GradeService {

    private GradeDAO gradeDAO;

    @Autowired
    public void setGradeDAO(GradeDAO gradeDAO) {
        super.setBaseDAO(gradeDAO);
        this.gradeDAO = gradeDAO;
    }

    @PostConstruct
    public void init() {
        super.init(GradeDO.class, GradeDTO.class);
    }

    @Override
    public List<Object> listAllById(Integer storeId) {
        return gradeDAO.listAllById(storeId);
    }
}
