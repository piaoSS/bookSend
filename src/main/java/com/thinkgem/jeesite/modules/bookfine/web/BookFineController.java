/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bookfine.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.bookfine.entity.BookFine;
import com.thinkgem.jeesite.modules.bookfine.service.BookFineService;

/**
 * 图书罚款表Controller
 * @author wp
 * @version 2018-04-08
 */
@Controller
@RequestMapping(value = "${adminPath}/bookfine/bookFine")
public class BookFineController extends BaseController {

	@Autowired
	private BookFineService bookFineService;
	
	@ModelAttribute
	public BookFine get(@RequestParam(required=false) String id) {
		BookFine entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bookFineService.get(id);
		}
		if (entity == null){
			entity = new BookFine();
		}
		return entity;
	}
	
	@RequiresPermissions("bookfine:bookFine:view")
	@RequestMapping(value = {"list", ""})
	public String list(BookFine bookFine, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BookFine> page = bookFineService.findPage(new Page<BookFine>(request, response), bookFine); 
		model.addAttribute("page", page);
		return "modules/bookfine/bookFineList";
	}

	@RequiresPermissions("bookfine:bookFine:view")
	@RequestMapping(value = "form")
	public String form(BookFine bookFine, Model model) {
		model.addAttribute("bookFine", bookFine);
		return "modules/bookfine/bookFineForm";
	}

	@RequiresPermissions("bookfine:bookFine:edit")
	@RequestMapping(value = "save")
	public String save(BookFine bookFine, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bookFine)){
			return form(bookFine, model);
		}
		bookFineService.save(bookFine);
		addMessage(redirectAttributes, "保存图书罚款表成功");
		return "redirect:"+Global.getAdminPath()+"/bookfine/bookFine/?repage";
	}
	
	@RequiresPermissions("bookfine:bookFine:edit")
	@RequestMapping(value = "delete")
	public String delete(BookFine bookFine, RedirectAttributes redirectAttributes) {
		bookFineService.delete(bookFine);
		addMessage(redirectAttributes, "删除图书罚款表成功");
		return "redirect:"+Global.getAdminPath()+"/bookfine/bookFine/?repage";
	}

}