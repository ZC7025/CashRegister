package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.GradeDTO;
import com.sucheng.dto.PagerDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.GradeQuery;
import com.sucheng.query.PageQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.GradeService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.GradeVO;
import com.sucheng.vo.PagerVO;
import com.sucheng.vo.StoreVO;
import org.apache.shiro.SecurityUtils;
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
 * GradeController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/data/grade")
public class GradeController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(GradeController.class);

    private GradeService gradeService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(GradeVO gradeVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            gradeService.save(getBeanMapper().map(gradeVO, GradeDTO.class));
            statusVO.okStatus(0, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(GradeVO gradeVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            gradeService.remove(getBeanMapper().map(gradeVO, GradeDTO.class));
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
            gradeService.removeById(id);
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
            gradeService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(0, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(GradeVO gradeVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            gradeService.update(getBeanMapper().map(gradeVO, GradeDTO.class));
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
            gradeService.updateActiveStatus(statusQuery);
            statusVO.okStatus(0, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public GradeVO getById(@PathVariable("id") Long id) {
        GradeVO gradeVO = new GradeVO();
        try {
            Object obj = gradeService.getById(id);
            if (obj != null) {
                gradeVO = getBeanMapper().map(obj, GradeVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return gradeVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<GradeVO> listAll() {
        List<GradeVO> gradeVOList = new ArrayList<>();
        try {
            List<Object> objectList = gradeService.listAll();
            gradeVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, GradeVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return gradeVOList;
    }

    @PostMapping("page")
    @ResponseBody
    public PagerVO listPage(PageQuery pageQuery) {
        PagerVO pagerVO = new PagerVO();
        try {
            PagerDTO pagerDTO = gradeService.listPage(pageQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), GradeVO.class));
        } catch (ServiceException e) {
            logger.error("返回分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @RequestMapping("gradeList")
    @ResponseBody
    public PagerVO listPageByCondition(int page, int limit, GradeQuery gradeQuery) {
        PageQuery pageQuery = new PageQuery(page, limit);
        PagerVO pagerVO = new PagerVO();
        try {
            Session session = SecurityUtils.getSubject().getSession();
            StoreVO storeVO = (StoreVO) session.getAttribute("store");
            if(storeVO == null) {
                logger.error("门店session失效");
                return null;
            }
            gradeQuery.setStoreId(storeVO.getId());
            PagerDTO pagerDTO = gradeService.listPageByCondition(pageQuery, gradeQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), GradeVO.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    @Resource
    public void setGradeService(GradeService gradeService) {
        this.gradeService = gradeService;
    }
}
