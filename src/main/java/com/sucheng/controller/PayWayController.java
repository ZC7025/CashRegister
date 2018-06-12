package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.PagerDTO;
import com.sucheng.dto.PayWayDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.PageQuery;
import com.sucheng.query.PayWayQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.PayWayService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.PagerVO;
import com.sucheng.vo.PayWayVO;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * PayWayController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/data/payWay")
public class PayWayController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(PayWayController.class);

    private PayWayService payWayService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(PayWayVO payWayVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            payWayService.save(getBeanMapper().map(payWayVO, PayWayDTO.class));
            statusVO.okStatus(0, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(PayWayVO payWayVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            payWayService.remove(getBeanMapper().map(payWayVO, PayWayDTO.class));
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
            payWayService.removeById(id);
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
            payWayService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(0, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(PayWayVO payWayVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            payWayService.update(getBeanMapper().map(payWayVO, PayWayDTO.class));
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
            payWayService.updateActiveStatus(statusQuery);
            statusVO.okStatus(0, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public PayWayVO getById(@PathVariable("id") Long id) {
        PayWayVO payWayVO = new PayWayVO();
        try {
            Object obj = payWayService.getById(id);
            if (obj != null) {
                payWayVO = getBeanMapper().map(obj, PayWayVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return payWayVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<PayWayVO> listAll() {
        List<PayWayVO> payWayVOList = new ArrayList<>();
        try {
            List<Object> objectList = payWayService.listAll();
            payWayVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, PayWayVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return payWayVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = payWayService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), PayWayVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("payWayList")
    @ResponseBody
    public PagerVO listPageByCondition(int page, int limit, PayWayQuery payWayQuery) {
        PageQuery pageQuery = new PageQuery(page, limit);
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = payWayService.listPageByCondition(pageQuery, payWayQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), PayWayQuery.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setPayWayService(PayWayService payWayService) {
        this.payWayService = payWayService;
    }
}
