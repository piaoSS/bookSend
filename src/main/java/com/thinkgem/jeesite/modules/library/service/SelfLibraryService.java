/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.library.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.library.entity.SelfLibrary;
import com.thinkgem.jeesite.modules.library.dao.SelfLibraryDao;

/**
 * 图书馆楼层分布Service
 * @author wp
 * @version 2018-04-08
 */
@Service
@Transactional(readOnly = true)
public class SelfLibraryService extends TreeService<SelfLibraryDao, SelfLibrary> {

	public SelfLibrary get(String id) {
		return super.get(id);
	}
	
	public List<SelfLibrary> findList(SelfLibrary selfLibrary) {
		if (StringUtils.isNotBlank(selfLibrary.getParentIds())){
			selfLibrary.setParentIds(","+selfLibrary.getParentIds()+",");
		}
		return super.findList(selfLibrary);
	}
	
	@Transactional(readOnly = false)
	public void save(SelfLibrary selfLibrary) {
		super.save(selfLibrary);
	}
	
	@Transactional(readOnly = false)
	public void delete(SelfLibrary selfLibrary) {
		super.delete(selfLibrary);
	}
	
}