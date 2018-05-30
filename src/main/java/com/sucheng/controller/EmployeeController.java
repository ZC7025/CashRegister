package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.HashUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.constant.Constants;
import com.sucheng.dto.EmployeeDTO;
import com.sucheng.dto.PagerDTO;
import com.sucheng.enums.HashEncodeEnum;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.EmployeeQuery;
import com.sucheng.query.PageQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.EmployeeService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.EmployeeVO;
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
 * EmployeeController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/data/employee")
public class EmployeeController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private EmployeeService employeeService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(EmployeeVO employeeVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            // TODO 验证手机号邮箱唯一
            employeeVO.setPwd(HashUtils.md5(employeeVO.getPwd(), Constants.SALT, HashEncodeEnum.HEX));
            employeeService.save(getBeanMapper().map(employeeVO, EmployeeDTO.class));
            statusVO.okStatus(0, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @GetMapping("remove/{id}")
    @ResponseBody
    public ControllerStatusVO removeById(@PathVariable("id") Long id) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            employeeService.removeById(id);
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
            employeeService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(EmployeeVO employeeVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            employeeService.update(getBeanMapper().map(employeeVO, EmployeeDTO.class));
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
            employeeService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public EmployeeVO getById(@PathVariable("id") Long id) {
        EmployeeVO employeeVO = new EmployeeVO();
        try {
            Object obj = employeeService.getById(id);
            if (obj != null) {
                employeeVO = getBeanMapper().map(obj, EmployeeVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return employeeVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<EmployeeVO> listAll() {
        List<EmployeeVO> employeeVOList = new ArrayList<>();
        try {
            List<Object> objectList = employeeService.listAll();
            employeeVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, EmployeeVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return employeeVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = employeeService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), EmployeeVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("empList")
    @ResponseBody
    public PagerVO listPageByCondition(int page, int limit, EmployeeQuery employeeQuery) {
        PageQuery pageQuery = new PageQuery(page, limit);
        PagerVO pagerVO = new PagerVO();
        try {
            StoreVO storeVO = (StoreVO) SecurityUtils.getSubject().getSession().getAttribute("store");
            if(storeVO != null) {
                employeeQuery.setStoreId(storeVO.getId());
            }
            PagerDTO pagerDTO = employeeService.listPageByCondition(pageQuery, employeeQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), EmployeeQuery.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
