package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.dto.PagerDTO;
import com.sucheng.dto.StoreOrderDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.PageQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.query.StoreOrderQuery;
import com.sucheng.service.StoreOrderService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.PagerVO;
import com.sucheng.vo.StoreOrderVO;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * StoreOrderController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/data/storeOrder")
public class StoreOrderController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(StoreOrderController.class);

    private StoreOrderService storeOrderService;

    @RequestMapping("save")
    @ResponseBody
    public ControllerStatusVO save(StoreOrderDTO storeOrderDTO, String details) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            storeOrderService.save(storeOrderDTO, details);
            statusVO.okStatus(0, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(StoreOrderVO storeOrderVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            storeOrderService.remove(getBeanMapper().map(storeOrderVO, StoreOrderDTO.class));
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
            storeOrderService.removeById(id);
            statusVO.okStatus(0, "删除成功");
        } catch (ServiceException e) {
            logger.error("删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(StoreOrderVO storeOrderVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            storeOrderService.update(getBeanMapper().map(storeOrderVO, StoreOrderDTO.class));
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
            storeOrderService.updateActiveStatus(statusQuery);
            statusVO.okStatus(0, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public StoreOrderVO getById(@PathVariable("id") Long id) {
        StoreOrderVO storeOrderVO = new StoreOrderVO();
        try {
            Object obj = storeOrderService.getById(id);
            if (obj != null) {
                storeOrderVO = getBeanMapper().map(obj, StoreOrderVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return storeOrderVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<StoreOrderVO> listAll() {
        List<StoreOrderVO> storeOrderVOList = new ArrayList<>();
        try {
            List<Object> objectList = storeOrderService.listAll();
            storeOrderVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, StoreOrderVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return storeOrderVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = storeOrderService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), StoreOrderVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("orderList")
    @ResponseBody
    public PagerVO listPageByCondition(int page, int limit, StoreOrderQuery storeOrderQuery) {
        PageQuery pageQuery = new PageQuery(page, limit);
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = storeOrderService.listPageByCondition(pageQuery, storeOrderQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), StoreOrderQuery.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setStoreOrderService(StoreOrderService storeOrderService) {
        this.storeOrderService = storeOrderService;
    }
}
