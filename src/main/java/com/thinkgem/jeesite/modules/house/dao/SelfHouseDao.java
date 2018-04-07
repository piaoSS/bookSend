/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.house.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.house.entity.SelfHouse;

/**
 * 公寓管理DAO接口
 * @author wp
 * @version 2018-04-08
 */
@MyBatisDao
public interface SelfHouseDao extends TreeDao<SelfHouse> {
	
}