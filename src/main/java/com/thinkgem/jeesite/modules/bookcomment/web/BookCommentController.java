/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bookcomment.web;

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
import com.thinkgem.jeesite.modules.bookcomment.entity.BookComment;
import com.thinkgem.jeesite.modules.bookcomment.service.BookCommentService;

/**
 * 图书评论表Controller
 * @author wp
 * @version 2018-04-08
 */
@Controller
@RequestMapping(value = "${adminPath}/bookcomment/bookComment")
public class BookCommentController extends BaseController {

	@Autowired
	private BookCommentService bookCommentService;
	
	@ModelAttribute
	public BookComment get(@RequestParam(required=false) String id) {
		BookComment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bookCommentService.get(id);
		}
		if (entity == null){
			entity = new BookComment();
		}
		return entity;
	}
	
	@RequiresPermissions("bookcomment:bookComment:view")
	@RequestMapping(value = {"list", ""})
	public String list(BookComment bookComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BookComment> page = bookCommentService.findPage(new Page<BookComment>(request, response), bookComment); 
		model.addAttribute("page", page);
		return "modules/bookcomment/bookCommentList";
	}

	@RequiresPermissions("bookcomment:bookComment:view")
	@RequestMapping(value = "form")
	public String form(BookComment bookComment, Model model) {
		model.addAttribute("bookComment", bookComment);
		return "modules/bookcomment/bookCommentForm";
	}

	@RequiresPermissions("bookcomment:bookComment:edit")
	@RequestMapping(value = "save")
	public String save(BookComment bookComment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bookComment)){
			return form(bookComment, model);
		}
		bookCommentService.save(bookComment);
		addMessage(redirectAttributes, "保存图书评论表成功");
		return "redirect:"+Global.getAdminPath()+"/bookcomment/bookComment/?repage";
	}
	
	@RequiresPermissions("bookcomment:bookComment:edit")
	@RequestMapping(value = "delete")
	public String delete(BookComment bookComment, RedirectAttributes redirectAttributes) {
		bookCommentService.delete(bookComment);
		addMessage(redirectAttributes, "删除图书评论表成功");
		return "redirect:"+Global.getAdminPath()+"/bookcomment/bookComment/?repage";
	}

}