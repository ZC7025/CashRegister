package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.FormulaDTO;
import com.sucheng.dto.PagerDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.FormulaQuery;
import com.sucheng.query.PageQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.FormulaService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.FormulaVO;
import com.sucheng.vo.PagerVO;
import com.sucheng.vo.StoreVO;
import org.apache.shiro.SecurityUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * FormulaController控制器类<br/>
 *
 * 创建于2018-06-05<br/>
 *
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/data/formula")
public class FormulaController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(FormulaController.class);

    private FormulaService formulaService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(FormulaVO formulaVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            formulaService.save(getBeanMapper().map(formulaVO, FormulaDTO.class));
            statusVO.okStatus(0, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(FormulaVO formulaVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            formulaService.remove(getBeanMapper().map(formulaVO, FormulaDTO.class));
            statusVO.okStatus(0, "删除成功");
        } catch (ServiceException e) {
            logger.error("删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "删除失败");
        }
        return statusVO;
    }

    @GetMapping("remove/{id}")
    @ResponseBody
    public ControllerStatusVO removeById(@PathVariable("id") Long id) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            formulaService.removeById(id);
            statusVO.okStatus(0, "删除成功");
        } catch (ServiceException e) {
            logger.error("删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "删除失败");
        }
        return statusVO;
    }

    @PostMapping("batch-remove")
    @ResponseBody
    public ControllerStatusVO removeByIds(String ids) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            formulaService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(0, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(FormulaVO formulaVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            formulaService.update(getBeanMapper().map(formulaVO, FormulaDTO.class));
            statusVO.okStatus(0, "更新成功");
        } catch (ServiceException e) {
            logger.error("更新失败：{}", e.getMessage());
            statusVO.errorStatus(500, "更新失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public FormulaVO getById(@PathVariable("id") Long id) {
        FormulaVO formulaVO = new FormulaVO();
        try {
            Object obj = formulaService.getById(id);
            if (obj != null) {
                formulaVO = getBeanMapper().map(obj, FormulaVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return formulaVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<FormulaVO> listAll() {
        List<FormulaVO> formulaVOList = new ArrayList<>();
        try {
            List<Object> objectList = formulaService.listAll();
            formulaVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, FormulaVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return formulaVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = formulaService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), FormulaVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("formulaList")
    @ResponseBody
    public PagerVO listPageByCondition(int page, int limit, FormulaQuery formulaQuery) {
        PageQuery pageQuery = new PageQuery(page, limit);
        PagerVO pagerVO = new PagerVO();
        try {
            StoreVO storeVO = (StoreVO) SecurityUtils.getSubject().getSession().getAttribute("store");
            if(storeVO == null) {
                logger.error("session失效");
                return pagerVO;
            }
            formulaQuery.setStoreId(storeVO.getId());
            PagerDTO pagerDTO = formulaService.listPageByCondition(pageQuery, formulaQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), FormulaQuery.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setFormulaService(FormulaService formulaService) {
        this.formulaService = formulaService;
    }
}
