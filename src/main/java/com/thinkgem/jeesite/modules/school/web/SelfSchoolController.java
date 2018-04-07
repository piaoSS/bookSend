/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.school.web;

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
import com.thinkgem.jeesite.modules.school.entity.SelfSchool;
import com.thinkgem.jeesite.modules.school.service.SelfSchoolService;

/**
 * 学校机构设置Controller
 * @author wp
 * @version 2018-04-07
 */
@Controller
@RequestMapping(value = "${adminPath}/school/selfSchool")
public class SelfSchoolController extends BaseController {

	@Autowired
	private SelfSchoolService selfSchoolService;
	
	@ModelAttribute
	public SelfSchool get(@RequestParam(required=false) String id) {
		SelfSchool entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = selfSchoolService.get(id);
		}
		if (entity == null){
			entity = new SelfSchool();
		}
		return entity;
	}
	
	@RequiresPermissions("school:selfSchool:view")
	@RequestMapping(value = {"list", ""})
	public String list(SelfSchool selfSchool, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<SelfSchool> list = selfSchoolService.findList(selfSchool); 
		model.addAttribute("list", list);
		return "modules/school/selfSchoolList";
	}

	@RequiresPermissions("school:selfSchool:view")
	@RequestMapping(value = "form")
	public String form(SelfSchool selfSchool, Model model) {
		if (selfSchool.getParent()!=null && StringUtils.isNotBlank(selfSchool.getParent().getId())){
			selfSchool.setParent(selfSchoolService.get(selfSchool.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(selfSchool.getId())){
				SelfSchool selfSchoolChild = new SelfSchool();
				selfSchoolChild.setParent(new SelfSchool(selfSchool.getParent().getId()));
				List<SelfSchool> list = selfSchoolService.findList(selfSchool); 
				if (list.size() > 0){
					selfSchool.setSort(list.get(list.size()-1).getSort());
					if (selfSchool.getSort() != null){
						selfSchool.setSort(selfSchool.getSort() + 30);
					}
				}
			}
		}
		if (selfSchool.getSort() == null){
			selfSchool.setSort(30);
		}
		model.addAttribute("selfSchool", selfSchool);
		return "modules/school/selfSchoolForm";
	}

	@RequiresPermissions("school:selfSchool:edit")
	@RequestMapping(value = "save")
	public String save(SelfSchool selfSchool, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, selfSchool)){
			return form(selfSchool, model);
		}
		selfSchoolService.save(selfSchool);
		addMessage(redirectAttributes, "保存学校机构设置成功");
		return "redirect:"+Global.getAdminPath()+"/school/selfSchool/?repage";
	}
	
	@RequiresPermissions("school:selfSchool:edit")
	@RequestMapping(value = "delete")
	public String delete(SelfSchool selfSchool, RedirectAttributes redirectAttributes) {
		selfSchoolService.delete(selfSchool);
		addMessage(redirectAttributes, "删除学校机构设置成功");
		return "redirect:"+Global.getAdminPath()+"/school/selfSchool/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
          @RequestParam(required=false) Long grade, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<SelfSchool> list = selfSchoolService.findList(new SelfSchool());
		for (int i=0; i<list.size(); i++){
			SelfSchool e = list.get(i);
            if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1))
                && (type == null || (type != null && (type.equals("1") ? type.equals(e.getType()) : true)))
                && (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))){
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", e.getId());
                map.put("pId", e.getParentId());
                map.put("pIds", e.getParentIds());
                map.put("name", e.getName());
                if (type != null && "3".equals(type)){
                    map.put("isParent", true);
                }
                mapList.add(map);
            }
		}
		return mapList;
	}
}