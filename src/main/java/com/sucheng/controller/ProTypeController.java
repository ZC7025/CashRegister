package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.PagerDTO;
import com.sucheng.dto.ProTypeDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.PageQuery;
import com.sucheng.query.ProTypeQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.ProTypeService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.PagerVO;
import com.sucheng.vo.ProTypeVO;
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
 * ProTypeController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/data/proType")
public class ProTypeController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProTypeController.class);

    private ProTypeService proTypeService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(ProTypeVO proTypeVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            proTypeService.save(getBeanMapper().map(proTypeVO, ProTypeDTO.class));
            statusVO.okStatus(0, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @GetMapping("remove/{id}")
    @ResponseBody
    public ControllerStatusVO removeById(@PathVariable("id") Long id) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            proTypeService.removeById(id);
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
            proTypeService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(ProTypeVO proTypeVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            proTypeService.update(getBeanMapper().map(proTypeVO, ProTypeDTO.class));
            statusVO.okStatus(0, "更新成功");
        } catch (ServiceException e) {
            logger.error("更新失败：{}", e.getMessage());
            statusVO.errorStatus(500, "更新失败");
        }
        return statusVO;
    }

    @PostMapping("active")
    @ResponseBody
    public ControllerStatusVO updateActiveStatus(StatusQuery statusQuery) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            proTypeService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public ProTypeVO getById(@PathVariable("id") Long id) {
        ProTypeVO proTypeVO = new ProTypeVO();
        try {
            Object obj = proTypeService.getById(id);
            if (obj != null) {
                proTypeVO = getBeanMapper().map(obj, ProTypeVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return proTypeVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<ProTypeVO> listAll() {
        List<ProTypeVO> proTypeVOList = new ArrayList<>();
        try {
            List<Object> objectList = proTypeService.listAll();
            proTypeVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, ProTypeVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return proTypeVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = proTypeService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), ProTypeVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("proTypeList")
    @ResponseBody
    public PagerVO listPageByCondition(int page, int limit, ProTypeQuery proTypeQuery) {
        PageQuery pageQuery = new PageQuery(page, limit);
        PagerVO pagerVO = new PagerVO();
        try {
            StoreVO storeVO = (StoreVO) SecurityUtils.getSubject().getSession().getAttribute("store");
            if(storeVO == null) {
                logger.error("session失效");
                return pagerVO;
            }
            proTypeQuery.setStoreId(storeVO.getId());
            PagerDTO pagerDTO = proTypeService.listPageByCondition(pageQuery, proTypeQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), ProTypeVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setProTypeService(ProTypeService proTypeService) {
        this.proTypeService = proTypeService;
    }
}
