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
 * ProGiftController控制器类<br/>
 *
 * 创建于2018-06-04<br/>
 *
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/data/proGift")
public class ProGiftController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProGiftController.class);

    private ProGiftService proGiftService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(ProGiftVO proGiftVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            proGiftService.save(getBeanMapper().map(proGiftVO, ProGiftDTO.class));
            statusVO.okStatus(0, "添加成功");
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
            statusVO.okStatus(0, "更新成功");
        } catch (ServiceException e) {
            logger.error("更新失败：{}", e.getMessage());
            statusVO.errorStatus(500, "更新失败");
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

    @RequestMapping("proGiftList")
    @ResponseBody
    public PagerVO listPageByCondition(int page, int limit, ProGiftQuery proGiftQuery) {
        PageQuery pageQuery = new PageQuery(page, limit);
        PagerVO pagerVO = new PagerVO();
        try {
            StoreVO storeVO = (StoreVO) SecurityUtils.getSubject().getSession().getAttribute("store");
            if(storeVO == null) {
                logger.error("session失效");
                return pagerVO;
            }
            proGiftQuery.setStoreId(storeVO.getId());
            PagerDTO pagerDTO = proGiftService.listPageByCondition(pageQuery, proGiftQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), ProGiftQuery.class));
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
