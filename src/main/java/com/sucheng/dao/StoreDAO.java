package com.sucheng.dao;

import com.sucheng.query.PageQuery;
import com.sucheng.vo.StoreVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * StoreDAO数据访问接口<br/>
 *
 * 创建于2018-05-24<br/>
 *
 * @author 7025
 * @version 1.0
 */
@Repository
public interface StoreDAO extends BaseDAO {

    @Override
    List<Object> listPageByCondition(@Param("pager") PageQuery pageQuery, @Param("query") Object queryObj);

    @Override
    Long countByCondition(@Param("query") Object queryObj);

    StoreVO getByPhonePwd(@Param("phone") String phone, @Param("pwd") String pwd);

    Integer hasPhoneEmail(@Param("phone")String phone, @Param("email")String email);
}
