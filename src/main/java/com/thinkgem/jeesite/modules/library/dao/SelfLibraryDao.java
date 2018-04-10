/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.library.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.library.entity.SelfLibrary;

/**
 * 图书馆楼层分布DAO接口
 * @author wp
 * @version 2018-04-08
 */
@MyBatisDao
public interface SelfLibraryDao extends TreeDao<SelfLibrary> {
	
}