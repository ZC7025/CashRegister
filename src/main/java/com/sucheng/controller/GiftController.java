package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.GiftDTO;
import com.sucheng.dto.PagerDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.GiftQuery;
import com.sucheng.query.PageQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.GiftService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.GiftVO;
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
 * GiftController控制器类<br/>
 * 商品套餐
 * 创建于2018-06-04<br/>
 *
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/data/gift")
public class GiftController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(GiftController.class);

    private GiftService giftService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(GiftVO giftVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            giftService.save(getBeanMapper().map(giftVO, GiftDTO.class));
            statusVO.okStatus(0, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(GiftVO giftVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            giftService.remove(getBeanMapper().map(giftVO, GiftDTO.class));
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
            giftService.removeById(id);
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
            giftService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(GiftVO giftVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            giftService.update(getBeanMapper().map(giftVO, GiftDTO.class));
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
            giftService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public GiftVO getById(@PathVariable("id") Long id) {
        GiftVO giftVO = new GiftVO();
        try {
            Object obj = giftService.getById(id);
            if (obj != null) {
                giftVO = getBeanMapper().map(obj, GiftVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return giftVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<GiftVO> listAll() {
        List<GiftVO> giftVOList = new ArrayList<>();
        try {
            List<Object> objectList = giftService.listAll();
            giftVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, GiftVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return giftVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = giftService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), GiftVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("giftList")
    @ResponseBody
    public PagerVO listPageByCondition(int page, int limit, GiftQuery giftQuery) {
        PageQuery pageQuery = new PageQuery(page, limit);
        PagerVO pagerVO = new PagerVO();
        try {
            StoreVO storeVO = (StoreVO) SecurityUtils.getSubject().getSession().getAttribute("store");
            if(storeVO == null) {
                logger.error("session失效");
                return pagerVO;
            }
            giftQuery.setStoreId(storeVO.getId());
            PagerDTO pagerDTO = giftService.listPageByCondition(pageQuery, giftQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), GiftQuery.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setGiftService(GiftService giftService) {
        this.giftService = giftService;
    }
}
