package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.PagerDTO;
import com.sucheng.dto.ProGiftDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.PageQuery;
import com.sucheng.query.ProGiftQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.ProGiftService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.PagerVO;
import com.sucheng.vo.ProGiftVO;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * ProGiftController控制器类<br/>
 *
 * 创建于2018-06-04<br/>
 *
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/pro-gift")
public class ProGiftController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProGiftController.class);

    private ProGiftService proGiftService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(ProGiftVO proGiftVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            proGiftService.save(getBeanMapper().map(proGiftVO, ProGiftDTO.class));
            statusVO.okStatus(200, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(ProGiftVO proGiftVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            proGiftService.remove(getBeanMapper().map(proGiftVO, ProGiftDTO.class));
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
            proGiftService.removeById(id);
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
            proGiftService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(ProGiftVO proGiftVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            proGiftService.update(getBeanMapper().map(proGiftVO, ProGiftDTO.class));
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
            proGiftService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public ProGiftVO getById(@PathVariable("id") Long id) {
        ProGiftVO proGiftVO = new ProGiftVO();
        try {
            Object obj = proGiftService.getById(id);
            if (obj != null) {
                proGiftVO = getBeanMapper().map(obj, ProGiftVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return proGiftVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<ProGiftVO> listAll() {
        List<ProGiftVO> proGiftVOList = new ArrayList<>();
        try {
            List<Object> objectList = proGiftService.listAll();
            proGiftVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, ProGiftVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return proGiftVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = proGiftService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), ProGiftVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @PostMapping("page-cond")
    @ResponseBody
    public PagerVO listPageByCondition(PageQuery pageQuery, ProGiftQuery proGiftQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = proGiftService.listPageByCondition(pageQuery, proGiftQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), ProGiftVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setProGiftService(ProGiftService proGiftService) {
        this.proGiftService = proGiftService;
    }
}
