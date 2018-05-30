package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.LogTransferDTO;
import com.sucheng.dto.PagerDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.LogTransferQuery;
import com.sucheng.query.PageQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.LogTransferService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.LogTransferVO;
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
 * LogTransferController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/log-transfer")
public class LogTransferController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LogTransferController.class);

    private LogTransferService logTransferService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(LogTransferVO logTransferVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            logTransferService.save(getBeanMapper().map(logTransferVO, LogTransferDTO.class));
            statusVO.okStatus(200, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(LogTransferVO logTransferVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            logTransferService.remove(getBeanMapper().map(logTransferVO, LogTransferDTO.class));
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
            logTransferService.removeById(id);
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
            logTransferService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(LogTransferVO logTransferVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            logTransferService.update(getBeanMapper().map(logTransferVO, LogTransferDTO.class));
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
            logTransferService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public LogTransferVO getById(@PathVariable("id") Long id) {
        LogTransferVO logTransferVO = new LogTransferVO();
        try {
            Object obj = logTransferService.getById(id);
            if (obj != null) {
                logTransferVO = getBeanMapper().map(obj, LogTransferVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return logTransferVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<LogTransferVO> listAll() {
        List<LogTransferVO> logTransferVOList = new ArrayList<>();
        try {
            List<Object> objectList = logTransferService.listAll();
            logTransferVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, LogTransferVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return logTransferVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = logTransferService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), LogTransferVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("pageList")
    @ResponseBody
    public PagerVO listPageByCondition(PageQuery pageQuery, LogTransferQuery logTransferQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = logTransferService.listPageByCondition(pageQuery, logTransferQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), LogTransferVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setLogTransferService(LogTransferService logTransferService) {
        this.logTransferService = logTransferService;
    }
}
