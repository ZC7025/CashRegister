package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.HashUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.constant.Constants;
import com.sucheng.dto.PagerDTO;
import com.sucheng.dto.StoreDTO;
import com.sucheng.enums.HashEncodeEnum;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.PageQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.query.StoreQuery;
import com.sucheng.service.StoreService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.PagerVO;
import com.sucheng.vo.StoreVO;
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
 * StoreController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/data/store")
public class StoreController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

    private StoreService storeService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(StoreVO storeVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            // TODO 判断手机号和邮箱唯一
            storeVO.setPwd(HashUtils.md5(storeVO.getPwd(), Constants.SALT, HashEncodeEnum.HEX));
            StoreVO storeVO1 = (StoreVO) SecurityUtils.getSubject().getSession().getAttribute("store");
            if(storeVO1 != null) {
                storeVO.setGeneralId(storeVO1.getId());
            }
            storeService.save(getBeanMapper().map(storeVO, StoreDTO.class));
            statusVO.okStatus(0, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(StoreVO storeVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            storeService.remove(getBeanMapper().map(storeVO, StoreDTO.class));
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
            storeService.removeById(id);
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
            storeService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("login")
    @ResponseBody
    public ControllerStatusVO login(StoreVO storeVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(storeVO.getPhone(), HashUtils.md5(storeVO.getPwd(),Constants.SALT, HashEncodeEnum.HEX)));
            StoreVO storeVO1 =storeService.getByPhonePwd(storeVO.getPhone(), HashUtils.md5(storeVO.getPwd(), Constants.SALT, HashEncodeEnum.HEX));
            Session session = subject.getSession();
            session.setAttribute("store",storeVO1);
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

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(StoreVO storeVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            storeService.update(getBeanMapper().map(storeVO, StoreDTO.class));
            statusVO.okStatus(0, "更新成功");
        } catch (ServiceException e) {
            logger.error("更新失败：{}", e.getMessage());
            statusVO.errorStatus(500, "更新失败");
        }
        return statusVO;
    }

    @PostMapping("updatePwd")
    @ResponseBody
    public ControllerStatusVO updatePwd(StoreVO storeVO, String newPwd) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            StoreVO storeVO1 =storeService.getByPhonePwd(storeVO.getPhone(), HashUtils.md5(storeVO.getPwd(), Constants.SALT, HashEncodeEnum.HEX));
            if(storeVO1 != null) {
                storeVO.setPwd(HashUtils.md5(newPwd, Constants.SALT, HashEncodeEnum.HEX));
                storeService.update(getBeanMapper().map(storeVO, StoreDTO.class));
                statusVO.okStatus(0, "更新成功");
                logger.error("更新密码：{}",storeVO1.getName());
                //删除登录凭证，重新登录
                SecurityUtils.getSubject().getSession().removeAttribute("store");
            } else {
                statusVO.errorStatus(500, "密码错误");
                logger.error("更新失败：{密码错误}",storeVO.getPhone());
            }
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
            storeService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public StoreVO getById(@PathVariable("id") Long id) {
        StoreVO storeVO = new StoreVO();
        try {
            Object obj = storeService.getById(id);
            if (obj != null) {
                storeVO = getBeanMapper().map(obj, StoreVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return storeVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<StoreVO> listAll() {
        List<StoreVO> storeVOList = new ArrayList<>();
        try {
            List<Object> objectList = storeService.listAll();
            storeVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, StoreVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return storeVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = storeService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), StoreVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("storeList")
    @ResponseBody
    public PagerVO listPageByCondition(int page, int limit, StoreQuery storeQuery) {
        PageQuery pageQuery = new PageQuery(page, limit);
        PagerVO pagerVO = new PagerVO();
        try {
            StoreVO storeVO = (StoreVO) SecurityUtils.getSubject().getSession().getAttribute("store");
            if(storeVO != null) {
                storeQuery.setGeneralId(storeVO.getId());
            }
            PagerDTO pagerDTO = storeService.listPageByCondition(pageQuery, storeQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), StoreQuery.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }
}
