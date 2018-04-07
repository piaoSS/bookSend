/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.school.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.school.entity.SelfSchool;
import com.thinkgem.jeesite.modules.school.dao.SelfSchoolDao;

/**
 * 学校机构设置Service
 * @author wp
 * @version 2018-04-07
 */
@Service
@Transactional(readOnly = true)
public class SelfSchoolService extends TreeService<SelfSchoolDao, SelfSchool> {

	public SelfSchool get(String id) {
		return super.get(id);
	}

	public List<SelfSchool> findList(SelfSchool selfSchool) {
		if (StringUtils.isNotBlank(selfSchool.getParentIds())){
			selfSchool.setParentIds(","+selfSchool.getParentIds()+",");
		}
		return super.findList(selfSchool);
	}

	@Transactional(readOnly = false)
	public void save(SelfSchool selfSchool) {
		super.save(selfSchool);
	}
	
	@Transactional(readOnly = false)
	public void delete(SelfSchool selfSchool) {
		super.delete(selfSchool);
	}
	
}