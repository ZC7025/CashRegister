package com.sucheng.dao;

import com.sucheng.query.PageQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FormulaDAO数据访问接口<br/>
 *
 * 创建于2018-06-05<br/>
 *
 *
 * @version 1.0
 */
@Repository
public interface FormulaDAO extends BaseDAO {

    @Override
    List<Object> listPageByCondition(@Param("pager") PageQuery pageQuery, @Param("query") Object queryObj);

    @Override
    Long countByCondition(@Param("query") Object queryObj);
}
