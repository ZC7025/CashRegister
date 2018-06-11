package com.sucheng.service;

import com.sucheng.query.StatusQuery;

import java.util.List;

/**
 * TaskPlanService服务接口<br/>
 *
 * 创建于2018-06-11<br/>
 *
 *
 * @version 1.0
 */
public interface TaskPlanService extends BaseService {

    void updateActiveStatus(Integer id, String status);
}
