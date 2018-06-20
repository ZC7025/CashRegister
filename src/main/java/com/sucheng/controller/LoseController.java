package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dos.StockDO;
import com.sucheng.dto.LoseDTO;
import com.sucheng.dto.PagerDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.LoseQuery;
import com.sucheng.query.PageQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.LoseService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.LoseVO;
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
 * LoseController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/data/lose")
public class LoseController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoseController.class);

    private LoseService loseService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(LoseVO loseVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            loseService.save(getBeanMapper().map(loseVO, LoseDTO.class));
            statusVO.okStatus(0, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(LoseVO loseVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            loseService.remove(getBeanMapper().map(loseVO, LoseDTO.class));
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
            loseService.removeById(id);
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
            loseService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(0, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(LoseVO loseVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            loseService.update(getBeanMapper().map(loseVO, LoseDTO.class));
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
            loseService.updateActiveStatus(statusQuery);
            statusVO.okStatus(0, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public LoseVO getById(@PathVariable("id") Long id) {
        LoseVO loseVO = new LoseVO();
        try {
            Object obj = loseService.getById(id);
            if (obj != null) {
                loseVO = getBeanMapper().map(obj, LoseVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return loseVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<LoseVO> listAll() {
        List<LoseVO> loseVOList = new ArrayList<>();
        try {
            List<Object> objectList = loseService.listAll();
            loseVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, LoseVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return loseVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = loseService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), LoseVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("loseList")
    @ResponseBody
    public PagerVO listPageByCondition(int page, int limit, LoseQuery loseQuery) {
        PageQuery pageQuery = new PageQuery(page, limit);
        PagerVO pagerVO = new PagerVO();
        try {
            StoreVO storeVO = (StoreVO) SecurityUtils.getSubject().getSession().getAttribute("store");
            if(storeVO == null) {
                logger.error("session失效");
                return pagerVO;
            }
            loseQuery.setStoreId(storeVO.getId());
            if(loseQuery.getSearchType() == null) {
                loseQuery.setSearchType("raw");
            }
            PagerDTO pagerDTO = loseService.listPageByCondition(pageQuery, loseQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), LoseQuery.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setLoseService(LoseService loseService) {
        this.loseService = loseService;
    }
}
