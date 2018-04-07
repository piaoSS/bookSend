/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.house.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.house.entity.SelfHouse;
import com.thinkgem.jeesite.modules.house.service.SelfHouseService;

/**
 * 公寓管理Controller
 * @author wp
 * @version 2018-04-08
 */
@Controller
@RequestMapping(value = "${adminPath}/house/selfHouse")
public class SelfHouseController extends BaseController {

	@Autowired
	private SelfHouseService selfHouseService;
	
	@ModelAttribute
	public SelfHouse get(@RequestParam(required=false) String id) {
		SelfHouse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = selfHouseService.get(id);
		}
		if (entity == null){
			entity = new SelfHouse();
		}
		return entity;
	}
	
	@RequiresPermissions("house:selfHouse:view")
	@RequestMapping(value = {"list", ""})
	public String list(SelfHouse selfHouse, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<SelfHouse> list = selfHouseService.findList(selfHouse); 
		model.addAttribute("list", list);
		return "modules/house/selfHouseList";
	}

	@RequiresPermissions("house:selfHouse:view")
	@RequestMapping(value = "form")
	public String form(SelfHouse selfHouse, Model model) {
		if (selfHouse.getParent()!=null && StringUtils.isNotBlank(selfHouse.getParent().getId())){
			selfHouse.setParent(selfHouseService.get(selfHouse.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(selfHouse.getId())){
				SelfHouse selfHouseChild = new SelfHouse();
				selfHouseChild.setParent(new SelfHouse(selfHouse.getParent().getId()));
				List<SelfHouse> list = selfHouseService.findList(selfHouse); 
				if (list.size() > 0){
					selfHouse.setSort(list.get(list.size()-1).getSort());
					if (selfHouse.getSort() != null){
						selfHouse.setSort(selfHouse.getSort() + 30);
					}
				}
			}
		}
		if (selfHouse.getSort() == null){
			selfHouse.setSort(30);
		}
		model.addAttribute("selfHouse", selfHouse);
		return "modules/house/selfHouseForm";
	}

	@RequiresPermissions("house:selfHouse:edit")
	@RequestMapping(value = "save")
	public String save(SelfHouse selfHouse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, selfHouse)){
			return form(selfHouse, model);
		}
		selfHouseService.save(selfHouse);
		addMessage(redirectAttributes, "保存公寓管理成功");
		return "redirect:"+Global.getAdminPath()+"/house/selfHouse/?repage";
	}
	
	@RequiresPermissions("house:selfHouse:edit")
	@RequestMapping(value = "delete")
	public String delete(SelfHouse selfHouse, RedirectAttributes redirectAttributes) {
		selfHouseService.delete(selfHouse);
		addMessage(redirectAttributes, "删除公寓管理成功");
		return "redirect:"+Global.getAdminPath()+"/house/selfHouse/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<SelfHouse> list = selfHouseService.findList(new SelfHouse());
		for (int i=0; i<list.size(); i++){
			SelfHouse e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}