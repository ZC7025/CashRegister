package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.HalfProDTO;
import com.sucheng.dto.PagerDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.HalfProQuery;
import com.sucheng.query.PageQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.HalfProService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.HalfProVO;
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
 * HalfProController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/data/halfPro")
public class HalfProController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(HalfProController.class);

    private HalfProService halfProService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(HalfProVO halfProVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            halfProService.save(getBeanMapper().map(halfProVO, HalfProDTO.class));
            statusVO.okStatus(0, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(HalfProVO halfProVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            halfProService.remove(getBeanMapper().map(halfProVO, HalfProDTO.class));
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
            halfProService.removeById(id);
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
            halfProService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(0, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(HalfProVO halfProVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            halfProService.update(getBeanMapper().map(halfProVO, HalfProDTO.class));
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
            halfProService.updateActiveStatus(statusQuery);
            statusVO.okStatus(0, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public HalfProVO getById(@PathVariable("id") Long id) {
        HalfProVO halfProVO = new HalfProVO();
        try {
            Object obj = halfProService.getById(id);
            if (obj != null) {
                halfProVO = getBeanMapper().map(obj, HalfProVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return halfProVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<HalfProVO> listAll() {
        List<HalfProVO> halfProVOList = new ArrayList<>();
        try {
            List<Object> objectList = halfProService.listAll();
            halfProVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, HalfProVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return halfProVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = halfProService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), HalfProVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("pageList")
    @ResponseBody
    public PagerVO listPageByCondition(int page, int limit, HalfProQuery halfProQuery) {
        PageQuery pageQuery = new PageQuery(page, limit);
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = halfProService.listPageByCondition(pageQuery, halfProQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), HalfProQuery.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setHalfProService(HalfProService halfProService) {
        this.halfProService = halfProService;
    }
}
