/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.school.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.school.entity.SelfSchool;

/**
 * 学校机构设置DAO接口
 * @author wp
 * @version 2018-04-07
 */
@MyBatisDao
public interface SelfSchoolDao extends TreeDao<SelfSchool> {
	
}