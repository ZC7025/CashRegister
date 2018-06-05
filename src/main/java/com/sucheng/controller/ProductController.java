package com.sucheng.controller;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.StringUtils;
import com.sucheng.dto.PagerDTO;
import com.sucheng.dto.ProductDTO;
import com.sucheng.exception.ServiceException;
import com.sucheng.query.PageQuery;
import com.sucheng.query.ProductQuery;
import com.sucheng.query.StatusQuery;
import com.sucheng.service.ProductService;
import com.sucheng.vo.ControllerStatusVO;
import com.sucheng.vo.PagerVO;
import com.sucheng.vo.ProductVO;
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
 * ProductController控制器类<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Controller
@RequestMapping("/data/product")
public class ProductController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    @PostMapping("save")
    @ResponseBody
    public ControllerStatusVO save(ProductVO productVO, String formulaIds) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            productVO.setProImg1(checkImgNull(productVO.getProImg1()));
            productVO.setProImg2(checkImgNull(productVO.getProImg2()));
            productVO.setProImg3(checkImgNull(productVO.getProImg3()));
            productVO.setProImg4(checkImgNull(productVO.getProImg4()));
            productService.save(getBeanMapper().map(productVO, ProductDTO.class), formulaIds);
            statusVO.okStatus(0, "添加成功");
        } catch (ServiceException e) {
            logger.error("添加失败：{}", e.getMessage());
            statusVO.errorStatus(500, "添加失败");
        }
        return statusVO;
    }

    @PostMapping("remove")
    @ResponseBody
    public ControllerStatusVO remove(ProductVO productVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            productService.remove(getBeanMapper().map(productVO, ProductDTO.class));
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
            productService.removeById(id);
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
            productService.removeByIds(StringUtils.strToLongArray(ids, ","));
            statusVO.okStatus(200, "批量删除成功");
        } catch (ServiceException e) {
            logger.error("批量删除失败：{}", e.getMessage());
            statusVO.errorStatus(500, "批量删除失败");
        }
        return statusVO;
    }

    @PostMapping("update")
    @ResponseBody
    public ControllerStatusVO update(ProductVO productVO) {
        ControllerStatusVO statusVO = new ControllerStatusVO();
        try {
            productVO.setProImg1(checkImgNull(productVO.getProImg1()));
            productVO.setProImg2(checkImgNull(productVO.getProImg2()));
            productVO.setProImg3(checkImgNull(productVO.getProImg3()));
            productVO.setProImg4(checkImgNull(productVO.getProImg4()));
            productService.update(getBeanMapper().map(productVO, ProductDTO.class));
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
            productService.updateActiveStatus(statusQuery);
            statusVO.okStatus(200, statusQuery.getStatus() == 0 ? "激活成功" : "冻结成功");
        } catch (ServiceException e) {
            logger.error("激活或冻结失败：{}", e.getMessage());
            statusVO.errorStatus(500, statusQuery.getStatus() == 0 ? "激活失败" : "冻结失败");
        }
        return statusVO;
    }

    @RequestMapping("one/{id}")
    @ResponseBody
    public ProductVO getById(@PathVariable("id") Long id) {
        ProductVO productVO = new ProductVO();
        try {
            Object obj = productService.getById(id);
            if (obj != null) {
                productVO = getBeanMapper().map(obj, ProductVO.class);
            }
        } catch (ServiceException e) {
            logger.error("返回单个对象JSON数据失败：{}", e.getMessage());
        }
        return productVO;
    }

    @GetMapping("all")
    @ResponseBody
    public List<ProductVO> listAll() {
        List<ProductVO> productVOList = new ArrayList<>();
        try {
            StoreVO storeVO = (StoreVO) SecurityUtils.getSubject().getSession().getAttribute("store");
            if(storeVO == null) {
                logger.error("session失效");
                return productVOList;
            }
            List<Object> objectList = productService.listAllById(storeVO.getId());
            productVOList =  DozerMapperUtils.map(getBeanMapper(), objectList, ProductVO.class);
        } catch (ServiceException e) {
            logger.error("返回所有对象JSON数据失败：{}", e.getMessage());
        }
        return productVOList;
    }

    @RequestMapping("proList")
    @ResponseBody
    public PagerVO listPageByCondition(int page, int limit, ProductQuery productQuery) {
        PageQuery pageQuery = new PageQuery(page, limit);
        PagerVO pagerVO = new PagerVO();
        try {
            StoreVO storeVO = (StoreVO) SecurityUtils.getSubject().getSession().getAttribute("store");
            if(storeVO == null) {
                logger.error("session失效");
                return pagerVO;
            }
            productQuery.setStoreId(storeVO.getId());
            PagerDTO pagerDTO = productService.listPageByCondition(pageQuery, productQuery);
            Mapper mapper = getBeanMapper();
            pagerVO = mapper.map(pagerDTO, PagerVO.class);
            pagerVO.setRows(DozerMapperUtils.mapList(mapper, pagerDTO.getRows(), ProductQuery.class));
        } catch (ServiceException e) {
            logger.error("返回指定条件的分页对象JSON数据失败：{}", e.getMessage());
        }
        return pagerVO;
    }

    /**
     * 判断图片路径是否为空串，是则返回null
     * @param imgPath 值
     * @return
     */
    private String checkImgNull(String imgPath) {
        if("".equals(imgPath)) {
            return null;
        } else {
            return  imgPath;
        }
    }

    @Resource
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
