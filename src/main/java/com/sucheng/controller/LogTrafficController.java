package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.LogTrafficDTO;
import com.sucheng.dto.PagerDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.LogTrafficQuery;
import com.sucheng.query.PageQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.LogTrafficService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.LogTrafficVO;
import com.sucheng.vo.PagerVO;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * LogTrafficController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/log-traffic")
public class LogTrafficController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LogTrafficController.class);

    private LogTrafficService logTrafficService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(LogTrafficVO logTrafficVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            logTrafficService.save(getBeanMapper().map(logTrafficVO, LogTrafficDTO.class));
            statusVO.okStatus(200, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(LogTrafficVO logTrafficVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            logTrafficService.remove(getBeanMapper().map(logTrafficVO, LogTrafficDTO.class));
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
            logTrafficService.removeById(id);
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
            logTrafficService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(LogTrafficVO logTrafficVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            logTrafficService.update(getBeanMapper().map(logTrafficVO, LogTrafficDTO.class));
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
            logTrafficService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public LogTrafficVO getById(@PathVariable("id") Long id) {
        LogTrafficVO logTrafficVO = new LogTrafficVO();
        try {
            Object obj = logTrafficService.getById(id);
            if (obj != null) {
                logTrafficVO = getBeanMapper().map(obj, LogTrafficVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return logTrafficVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<LogTrafficVO> listAll() {
        List<LogTrafficVO> logTrafficVOList = new ArrayList<>();
        try {
            List<Object> objectList = logTrafficService.listAll();
            logTrafficVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, LogTrafficVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return logTrafficVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = logTrafficService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), LogTrafficVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @PostMapping("page-cond")
    @ResponseBody
    public PagerVO listPageByCondition(PageQuery pageQuery, LogTrafficQuery logTrafficQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = logTrafficService.listPageByCondition(pageQuery, logTrafficQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), LogTrafficVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setLogTrafficService(LogTrafficService logTrafficService) {
        this.logTrafficService = logTrafficService;
    }
}
