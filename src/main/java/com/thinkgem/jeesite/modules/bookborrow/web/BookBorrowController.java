/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bookborrow.web;

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
import com.thinkgem.jeesite.modules.bookborrow.entity.BookBorrow;
import com.thinkgem.jeesite.modules.bookborrow.service.BookBorrowService;

/**
 * 图书借阅表Controller
 * @author wp
 * @version 2018-04-08
 */
@Controller
@RequestMapping(value = "${adminPath}/bookborrow/bookBorrow")
public class BookBorrowController extends BaseController {

	@Autowired
	private BookBorrowService bookBorrowService;
	
	@ModelAttribute
	public BookBorrow get(@RequestParam(required=false) String id) {
		BookBorrow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bookBorrowService.get(id);
		}
		if (entity == null){
			entity = new BookBorrow();
		}
		return entity;
	}
	
	@RequiresPermissions("bookborrow:bookBorrow:view")
	@RequestMapping(value = {"list", ""})
	public String list(BookBorrow bookBorrow, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BookBorrow> page = bookBorrowService.findPage(new Page<BookBorrow>(request, response), bookBorrow); 
		model.addAttribute("page", page);
		return "modules/bookborrow/bookBorrowList";
	}

	@RequiresPermissions("bookborrow:bookBorrow:view")
	@RequestMapping(value = "form")
	public String form(BookBorrow bookBorrow, Model model) {
		model.addAttribute("bookBorrow", bookBorrow);
		return "modules/bookborrow/bookBorrowForm";
	}

	@RequiresPermissions("bookborrow:bookBorrow:edit")
	@RequestMapping(value = "save")
	public String save(BookBorrow bookBorrow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bookBorrow)){
			return form(bookBorrow, model);
		}
		bookBorrowService.save(bookBorrow);
		addMessage(redirectAttributes, "保存图书借阅表成功");
		return "redirect:"+Global.getAdminPath()+"/bookborrow/bookBorrow/?repage";
	}
	
	@RequiresPermissions("bookborrow:bookBorrow:edit")
	@RequestMapping(value = "delete")
	public String delete(BookBorrow bookBorrow, RedirectAttributes redirectAttributes) {
		bookBorrowService.delete(bookBorrow);
		addMessage(redirectAttributes, "删除图书借阅表成功");
		return "redirect:"+Global.getAdminPath()+"/bookborrow/bookBorrow/?repage";
	}

}