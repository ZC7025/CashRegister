package com.sucheng.dao;

import com.sucheng.common.DozerMapperUtils;
import com.sucheng.common.ExceptionUtils;
import com.sucheng.dto.PagerDTO;
import com.sucheng.query.PageQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * IndustryDAO数据访问接口<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Repository
public interface IndustryDAO extends BaseDAO {

    @Override
    List<Object> listPageByCondition(@Param("pager") PageQuery pageQuery, @Param("query") Object queryObj);

    @Override
    Long countByCondition(@Param("query") Object queryObj);
}
