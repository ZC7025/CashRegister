package com.sucheng.service.impl;

import com.sucheng.dao.FormulaDAO;
import com.sucheng.dos.FormulaDO;
import com.sucheng.dto.FormulaDTO;
import com.sucheng.service.AbstractBaseService;
import com.sucheng.service.FormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * FormulaServiceImpl服务接口实现类<br/>
 *
 * 创建于2018-06-05<br/>
 *
 *
 * @version 1.0
 */
@Service(value = "formulaService")
public class FormulaServiceImpl extends AbstractBaseService implements FormulaService {

    private FormulaDAO formulaDAO;

    @Autowired
    public void setFormulaDAO(FormulaDAO formulaDAO) {
        super.setBaseDAO(formulaDAO);
        this.formulaDAO = formulaDAO;
    }

    @PostConstruct
    public void init() {
        super.init(FormulaDO.class, FormulaDTO.class);
    }
}
