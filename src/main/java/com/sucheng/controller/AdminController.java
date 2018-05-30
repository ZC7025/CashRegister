package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.HashUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.constant.Constants;
import com.sucheng.dto.AdminDTO;
import com.sucheng.dto.PagerDTO;
import com.sucheng.enums.HashEncodeEnum;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.AdminQuery;
import com.sucheng.query.PageQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.AdminService;
import com.sucheng.vo.AdminVO;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.PagerVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * AdminController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/data/admin")
public class AdminController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private AdminService adminService;

    @PostMapping("login")
    @ResponseBody
    public ControllerStatusVO login(AdminVO adminVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(adminVO.getPhone(), HashUtils.md5(adminVO.getPwd(),Constants.SALT, HashEncodeEnum.HEX)));
            AdminVO adminVO1 =adminService.getByPhonePwd(adminVO.getPhone(), HashUtils.md5(adminVO.getPwd(), Constants.SALT, HashEncodeEnum.HEX));
            Session session = subject.getSession();
            session.setAttribute("admin",adminVO1);
            statusVO.okStatus(0,"登录成功");
        } catch (ServiceException | AuthenticationException e) {
            logger.error("登录失败：{}", e.getMessage());
            statusVO.errorStatus(500, "登录失败");
        }
        return statusVO;
    }

    @RequestMapping("out")
    @ResponseBody
    public ControllerStatusVO outTest() {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            statusVO.okStatus(0,"注销成功");
        } else {
            statusVO.errorStatus(500, "注销失败");
        }
        return statusVO;
    }

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(AdminVO adminVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            adminService.save(getBeanMapper().map(adminVO, AdminDTO.class));
            statusVO.okStatus(200, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(AdminVO adminVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            adminService.remove(getBeanMapper().map(adminVO, AdminDTO.class));
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
            adminService.removeById(id);
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
            adminService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(AdminVO adminVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            adminService.update(getBeanMapper().map(adminVO, AdminDTO.class));
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
            adminService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public AdminVO getById(@PathVariable("id") Long id) {
        AdminVO adminVO = new AdminVO();
        try {
            Object obj = adminService.getById(id);
            if (obj != null) {
                adminVO = getBeanMapper().map(obj, AdminVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return adminVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<AdminVO> listAll() {
        List<AdminVO> adminVOList = new ArrayList<>();
        try {
            List<Object> objectList = adminService.listAll();
            adminVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, AdminVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return adminVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = adminService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), AdminVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("pageList")
    @ResponseBody
    public PagerVO listPageByCondition(PageQuery pageQuery, AdminQuery adminQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = adminService.listPageByCondition(pageQuery, adminQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), AdminVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
}
