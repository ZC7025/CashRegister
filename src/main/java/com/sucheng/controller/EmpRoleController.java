package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.EmpRoleDTO;
import com.sucheng.dto.PagerDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.EmpRoleQuery;
import com.sucheng.query.PageQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.EmpRoleService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.EmpRoleVO;
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
 * EmpRoleController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/emp-role")
public class EmpRoleController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(EmpRoleController.class);

    private EmpRoleService empRoleService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(EmpRoleVO empRoleVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            empRoleService.save(getBeanMapper().map(empRoleVO, EmpRoleDTO.class));
            statusVO.okStatus(200, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(EmpRoleVO empRoleVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            empRoleService.remove(getBeanMapper().map(empRoleVO, EmpRoleDTO.class));
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
            empRoleService.removeById(id);
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
            empRoleService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(EmpRoleVO empRoleVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            empRoleService.update(getBeanMapper().map(empRoleVO, EmpRoleDTO.class));
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
            empRoleService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public EmpRoleVO getById(@PathVariable("id") Long id) {
        EmpRoleVO empRoleVO = new EmpRoleVO();
        try {
            Object obj = empRoleService.getById(id);
            if (obj != null) {
                empRoleVO = getBeanMapper().map(obj, EmpRoleVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return empRoleVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<EmpRoleVO> listAll() {
        List<EmpRoleVO> empRoleVOList = new ArrayList<>();
        try {
            List<Object> objectList = empRoleService.listAll();
            empRoleVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, EmpRoleVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return empRoleVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = empRoleService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), EmpRoleVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("pageList")
    @ResponseBody
    public PagerVO listPageByCondition(PageQuery pageQuery, EmpRoleQuery empRoleQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = empRoleService.listPageByCondition(pageQuery, empRoleQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), EmpRoleVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setEmpRoleService(EmpRoleService empRoleService) {
        this.empRoleService = empRoleService;
    }
}
