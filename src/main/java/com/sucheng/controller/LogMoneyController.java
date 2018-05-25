package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.LogMoneyDTO;
import com.sucheng.dto.PagerDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.LogMoneyQuery;
import com.sucheng.query.PageQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.LogMoneyService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.LogMoneyVO;
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
 * LogMoneyController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/log-money")
public class LogMoneyController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LogMoneyController.class);

    private LogMoneyService logMoneyService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(LogMoneyVO logMoneyVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            logMoneyService.save(getBeanMapper().map(logMoneyVO, LogMoneyDTO.class));
            statusVO.okStatus(200, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(LogMoneyVO logMoneyVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            logMoneyService.remove(getBeanMapper().map(logMoneyVO, LogMoneyDTO.class));
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
            logMoneyService.removeById(id);
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
            logMoneyService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(LogMoneyVO logMoneyVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            logMoneyService.update(getBeanMapper().map(logMoneyVO, LogMoneyDTO.class));
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
            logMoneyService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public LogMoneyVO getById(@PathVariable("id") Long id) {
        LogMoneyVO logMoneyVO = new LogMoneyVO();
        try {
            Object obj = logMoneyService.getById(id);
            if (obj != null) {
                logMoneyVO = getBeanMapper().map(obj, LogMoneyVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return logMoneyVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<LogMoneyVO> listAll() {
        List<LogMoneyVO> logMoneyVOList = new ArrayList<>();
        try {
            List<Object> objectList = logMoneyService.listAll();
            logMoneyVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, LogMoneyVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return logMoneyVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = logMoneyService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), LogMoneyVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @PostMapping("page-cond")
    @ResponseBody
    public PagerVO listPageByCondition(PageQuery pageQuery, LogMoneyQuery logMoneyQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = logMoneyService.listPageByCondition(pageQuery, logMoneyQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), LogMoneyVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setLogMoneyService(LogMoneyService logMoneyService) {
        this.logMoneyService = logMoneyService;
    }
}
