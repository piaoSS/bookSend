/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.house.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.house.entity.SelfHouse;
import com.thinkgem.jeesite.modules.house.dao.SelfHouseDao;

/**
 * 公寓管理Service
 * @author wp
 * @version 2018-04-08
 */
@Service
@Transactional(readOnly = true)
public class SelfHouseService extends TreeService<SelfHouseDao, SelfHouse> {

	public SelfHouse get(String id) {
		return super.get(id);
	}
	
	public List<SelfHouse> findList(SelfHouse selfHouse) {
		if (StringUtils.isNotBlank(selfHouse.getParentIds())){
			selfHouse.setParentIds(","+selfHouse.getParentIds()+",");
		}
		return super.findList(selfHouse);
	}
	
	@Transactional(readOnly = false)
	public void save(SelfHouse selfHouse) {
		super.save(selfHouse);
	}
	
	@Transactional(readOnly = false)
	public void delete(SelfHouse selfHouse) {
		super.delete(selfHouse);
	}
	
}