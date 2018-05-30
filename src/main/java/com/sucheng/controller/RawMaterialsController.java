package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.PagerDTO;
import com.sucheng.dto.RawMaterialsDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.PageQuery;
import com.sucheng.query.RawMaterialsQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.RawMaterialsService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.PagerVO;
import com.sucheng.vo.RawMaterialsVO;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * RawMaterialsController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/raw-materials")
public class RawMaterialsController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(RawMaterialsController.class);

    private RawMaterialsService rawMaterialsService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(RawMaterialsVO rawMaterialsVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            rawMaterialsService.save(getBeanMapper().map(rawMaterialsVO, RawMaterialsDTO.class));
            statusVO.okStatus(200, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(RawMaterialsVO rawMaterialsVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            rawMaterialsService.remove(getBeanMapper().map(rawMaterialsVO, RawMaterialsDTO.class));
            statusVO.okStatus(200, "删除成功");
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
            rawMaterialsService.removeById(id);
            statusVO.okStatus(200, "删除成功");
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
            rawMaterialsService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(RawMaterialsVO rawMaterialsVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            rawMaterialsService.update(getBeanMapper().map(rawMaterialsVO, RawMaterialsDTO.class));
            statusVO.okStatus(200, "更新成功");
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
            rawMaterialsService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public RawMaterialsVO getById(@PathVariable("id") Long id) {
        RawMaterialsVO rawMaterialsVO = new RawMaterialsVO();
        try {
            Object obj = rawMaterialsService.getById(id);
            if (obj != null) {
                rawMaterialsVO = getBeanMapper().map(obj, RawMaterialsVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return rawMaterialsVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<RawMaterialsVO> listAll() {
        List<RawMaterialsVO> rawMaterialsVOList = new ArrayList<>();
        try {
            List<Object> objectList = rawMaterialsService.listAll();
            rawMaterialsVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, RawMaterialsVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return rawMaterialsVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = rawMaterialsService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), RawMaterialsVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("pageList")
    @ResponseBody
    public PagerVO listPageByCondition(PageQuery pageQuery, RawMaterialsQuery rawMaterialsQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = rawMaterialsService.listPageByCondition(pageQuery, rawMaterialsQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), RawMaterialsVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setRawMaterialsService(RawMaterialsService rawMaterialsService) {
        this.rawMaterialsService = rawMaterialsService;
    }
}
