package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.PagerDTO;
import com.sucheng.dto.RoleAuthorityDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.PageQuery;
import com.sucheng.query.RoleAuthorityQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.RoleAuthorityService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.PagerVO;
import com.sucheng.vo.RoleAuthorityVO;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * RoleAuthorityController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/role-authority")
public class RoleAuthorityController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(RoleAuthorityController.class);

    private RoleAuthorityService roleAuthorityService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(RoleAuthorityVO roleAuthorityVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            roleAuthorityService.save(getBeanMapper().map(roleAuthorityVO, RoleAuthorityDTO.class));
            statusVO.okStatus(200, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(RoleAuthorityVO roleAuthorityVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            roleAuthorityService.remove(getBeanMapper().map(roleAuthorityVO, RoleAuthorityDTO.class));
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
            roleAuthorityService.removeById(id);
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
            roleAuthorityService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(RoleAuthorityVO roleAuthorityVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            roleAuthorityService.update(getBeanMapper().map(roleAuthorityVO, RoleAuthorityDTO.class));
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
            roleAuthorityService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public RoleAuthorityVO getById(@PathVariable("id") Long id) {
        RoleAuthorityVO roleAuthorityVO = new RoleAuthorityVO();
        try {
            Object obj = roleAuthorityService.getById(id);
            if (obj != null) {
                roleAuthorityVO = getBeanMapper().map(obj, RoleAuthorityVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return roleAuthorityVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<RoleAuthorityVO> listAll() {
        List<RoleAuthorityVO> roleAuthorityVOList = new ArrayList<>();
        try {
            List<Object> objectList = roleAuthorityService.listAll();
            roleAuthorityVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, RoleAuthorityVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return roleAuthorityVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = roleAuthorityService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), RoleAuthorityVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @PostMapping("page-cond")
    @ResponseBody
    public PagerVO listPageByCondition(PageQuery pageQuery, RoleAuthorityQuery roleAuthorityQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = roleAuthorityService.listPageByCondition(pageQuery, roleAuthorityQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), RoleAuthorityVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setRoleAuthorityService(RoleAuthorityService roleAuthorityService) {
        this.roleAuthorityService = roleAuthorityService;
    }
}
