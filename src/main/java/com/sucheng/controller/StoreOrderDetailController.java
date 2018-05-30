package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.PagerDTO;
import com.sucheng.dto.StoreOrderDetailDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.PageQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.query.StoreOrderDetailQuery;
import com.sucheng.service.StoreOrderDetailService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.PagerVO;
import com.sucheng.vo.StoreOrderDetailVO;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * StoreOrderDetailController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/store-order-detail")
public class StoreOrderDetailController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(StoreOrderDetailController.class);

    private StoreOrderDetailService storeOrderDetailService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(StoreOrderDetailVO storeOrderDetailVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            storeOrderDetailService.save(getBeanMapper().map(storeOrderDetailVO, StoreOrderDetailDTO.class));
            statusVO.okStatus(200, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(StoreOrderDetailVO storeOrderDetailVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            storeOrderDetailService.remove(getBeanMapper().map(storeOrderDetailVO, StoreOrderDetailDTO.class));
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
            storeOrderDetailService.removeById(id);
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
            storeOrderDetailService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(StoreOrderDetailVO storeOrderDetailVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            storeOrderDetailService.update(getBeanMapper().map(storeOrderDetailVO, StoreOrderDetailDTO.class));
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
            storeOrderDetailService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public StoreOrderDetailVO getById(@PathVariable("id") Long id) {
        StoreOrderDetailVO storeOrderDetailVO = new StoreOrderDetailVO();
        try {
            Object obj = storeOrderDetailService.getById(id);
            if (obj != null) {
                storeOrderDetailVO = getBeanMapper().map(obj, StoreOrderDetailVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return storeOrderDetailVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<StoreOrderDetailVO> listAll() {
        List<StoreOrderDetailVO> storeOrderDetailVOList = new ArrayList<>();
        try {
            List<Object> objectList = storeOrderDetailService.listAll();
            storeOrderDetailVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, StoreOrderDetailVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return storeOrderDetailVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = storeOrderDetailService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), StoreOrderDetailVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("pageList")
    @ResponseBody
    public PagerVO listPageByCondition(PageQuery pageQuery, StoreOrderDetailQuery storeOrderDetailQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = storeOrderDetailService.listPageByCondition(pageQuery, storeOrderDetailQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), StoreOrderDetailVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setStoreOrderDetailService(StoreOrderDetailService storeOrderDetailService) {
        this.storeOrderDetailService = storeOrderDetailService;
    }
}
