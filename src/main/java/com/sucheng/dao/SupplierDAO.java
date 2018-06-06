package com.sucheng.dao;

import com.sucheng.query.PageQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SupplierDAO数据访问接口<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Repository
public interface SupplierDAO extends BaseDAO {

    @Override
    List<Object> listPageByCondition(@Param("pager") PageQuery pageQuery, @Param("query") Object queryObj);

    @Override
    Long countByCondition(@Param("query") Object queryObj);

    /**
     * 根据storeId来更新所有该门店下的默认供应商为非默认
     */
    void updateDefaultById(Integer storeId);

    List<Object> listAllById(Integer storeId);
}
