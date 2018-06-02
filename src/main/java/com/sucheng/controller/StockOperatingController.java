package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.PagerDTO;
import com.sucheng.dto.StockOperatingDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.PageQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.query.StockOperatingQuery;
import com.sucheng.service.StockOperatingService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.PagerVO;
import com.sucheng.vo.StockOperatingVO;
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
 * StockOperatingController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/data/stockLog")
public class StockOperatingController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(StockOperatingController.class);

    private StockOperatingService stockOperatingService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(StockOperatingVO stockOperatingVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            stockOperatingService.save(getBeanMapper().map(stockOperatingVO, StockOperatingDTO.class));
            statusVO.okStatus(200, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(StockOperatingVO stockOperatingVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            stockOperatingService.remove(getBeanMapper().map(stockOperatingVO, StockOperatingDTO.class));
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
            stockOperatingService.removeById(id);
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
            stockOperatingService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(StockOperatingVO stockOperatingVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            stockOperatingService.update(getBeanMapper().map(stockOperatingVO, StockOperatingDTO.class));
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
            stockOperatingService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public StockOperatingVO getById(@PathVariable("id") Long id) {
        StockOperatingVO stockOperatingVO = new StockOperatingVO();
        try {
            Object obj = stockOperatingService.getById(id);
            if (obj != null) {
                stockOperatingVO = getBeanMapper().map(obj, StockOperatingVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return stockOperatingVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<StockOperatingVO> listAll() {
        List<StockOperatingVO> stockOperatingVOList = new ArrayList<>();
        try {
            List<Object> objectList = stockOperatingService.listAll();
            stockOperatingVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, StockOperatingVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return stockOperatingVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = stockOperatingService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), StockOperatingVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("stockList")
    @ResponseBody
    public PagerVO listPageByCondition(int page, int limit, StockOperatingQuery stockOperatingQuery) {
        PageQuery pageQuery = new PageQuery(page, limit);
        PagerVO pagerVO = new PagerVO();
        try {
            StoreVO storeVO = (StoreVO) SecurityUtils.getSubject().getSession().getAttribute("store");
            if(storeVO == null) {
                logger.error("session失效");
                return pagerVO;
            }
            stockOperatingQuery.setStoreId(storeVO.getId());
            PagerDTO pagerDTO = stockOperatingService.listPageByCondition(pageQuery, stockOperatingQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), StockOperatingQuery.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setStockOperatingService(StockOperatingService stockOperatingService) {
        this.stockOperatingService = stockOperatingService;
    }
}
