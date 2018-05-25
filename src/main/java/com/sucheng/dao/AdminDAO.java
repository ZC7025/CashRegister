package com.sucheng.dao;

import com.sucheng.query.PageQuery;
import com.sucheng.vo.AdminVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AdminDAO数据访问接口<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Repository
public interface AdminDAO extends BaseDAO {

    @Override
    List<Object> listPageByCondition(@Param("pager") PageQuery pageQuery, @Param("query") Object queryObj);

    AdminVO getByPhonePwd(@Param("phone") String phone, @Param("pwd") String pwd);
}
