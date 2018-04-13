/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.library.web;

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
import com.thinkgem.jeesite.modules.library.entity.SelfLibrary;
import com.thinkgem.jeesite.modules.library.service.SelfLibraryService;

/**
 * 图书馆楼层分布Controller
 * @author wp
 * @version 2018-04-08
 */
@Controller
@RequestMapping(value = "${adminPath}/library/selfLibrary")
public class SelfLibraryController extends BaseController {

	@Autowired
	private SelfLibraryService selfLibraryService;
	
	@ModelAttribute
	public SelfLibrary get(@RequestParam(required=false) String id) {
		SelfLibrary entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = selfLibraryService.get(id);
		}
		if (entity == null){
			entity = new SelfLibrary();
		}
		return entity;
	}
	
	@RequiresPermissions("library:selfLibrary:view")
	@RequestMapping(value = {"list", ""})
	public String list(SelfLibrary selfLibrary, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<SelfLibrary> list = selfLibraryService.findList(selfLibrary); 
		model.addAttribute("list", list);
		return "modules/library/selfLibraryList";
	}

	@RequiresPermissions("library:selfLibrary:view")
	@RequestMapping(value = "form")
	public String form(SelfLibrary selfLibrary, Model model) {
		if (selfLibrary.getParent()!=null && StringUtils.isNotBlank(selfLibrary.getParent().getId())){
			selfLibrary.setParent(selfLibraryService.get(selfLibrary.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(selfLibrary.getId())){
				SelfLibrary selfLibraryChild = new SelfLibrary();
				selfLibraryChild.setParent(new SelfLibrary(selfLibrary.getParent().getId()));
				List<SelfLibrary> list = selfLibraryService.findList(selfLibrary); 
				if (list.size() > 0){
					selfLibrary.setSort(list.get(list.size()-1).getSort());
					if (selfLibrary.getSort() != null){
						selfLibrary.setSort(selfLibrary.getSort() + 30);
					}
				}
			}
		}
		if (selfLibrary.getSort() == null){
			selfLibrary.setSort(30);
		}
		model.addAttribute("selfLibrary", selfLibrary);
		return "modules/library/selfLibraryForm";
	}

	@RequiresPermissions("library:selfLibrary:edit")
	@RequestMapping(value = "save")
	public String save(SelfLibrary selfLibrary, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, selfLibrary)){
			return form(selfLibrary, model);
		}
		selfLibraryService.save(selfLibrary);
		addMessage(redirectAttributes, "保存图书馆楼层分布成功");
		return "redirect:"+Global.getAdminPath()+"/library/selfLibrary/?repage";
	}
	
	@RequiresPermissions("library:selfLibrary:edit")
	@RequestMapping(value = "delete")
	public String delete(SelfLibrary selfLibrary, RedirectAttributes redirectAttributes) {
		selfLibraryService.delete(selfLibrary);
		addMessage(redirectAttributes, "删除图书馆楼层分布成功");
		return "redirect:"+Global.getAdminPath()+"/library/selfLibrary/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<SelfLibrary> list = selfLibraryService.findList(new SelfLibrary());
		for (int i=0; i<list.size(); i++){
			SelfLibrary e = list.get(i);
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