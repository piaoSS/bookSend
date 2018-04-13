/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.google.common.collect.Lists;
import javax.validation.ConstraintViolationException;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.modules.bookborrow.service.BookBorrowService;
import com.thinkgem.jeesite.modules.bookcollect.service.BookCollectService;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.thinkgem.jeesite.modules.book.entity.Book;
import com.thinkgem.jeesite.modules.book.service.BookService;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import org.springframework.web.multipart.MultipartFile;



/**
 * 图书表Controller
 * @author wp
 * @version 2018-04-08
 */
@Controller
@RequestMapping(value = "${adminPath}/book/book")
public class BookController extends BaseController {

	@Autowired
	private BookService bookService;

	@Autowired
	private BookBorrowService bookBorrowService;

	@Autowired
	private BookCollectService bookCollectService;

	@ModelAttribute
	public Book get(@RequestParam(required=false) String id) {
		Book entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bookService.get(id);
		}
		if (entity == null){
			entity = new Book();
		}
		return entity;
	}

	@RequiresPermissions("book:book:view")
	@RequestMapping(value = {"list", ""})
	public String list(Book book, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Book> page = bookService.findPage(new Page<Book>(request, response), book);
		model.addAttribute("page", page);
		return "modules/book/bookList";
	}

	/**
	 *图书馆首页
	 */
	@RequestMapping(value = "indexBook")
	public String indexBook(Book book, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		//根据 用户id 编号 查找本人借阅书本的数量
		Integer borrowNum = bookBorrowService.borrowNum(user.getId());
		//根据用户id 编号查找本人未还书本的数量
		Integer runNum = bookBorrowService.runNum(user.getId());
		//根据用户id 编号查找本人收藏书本的数量
		Integer collectNum = bookCollectService.collectNum(user.getId());

		//根据用户id 编号查找本人近30天的借阅书本的图书所有信息
		List<Book> borrowBookList = bookService.findUserBorrowBookList(user.getId());
		//根据用户id 编号查找本人所有的收藏书本的图书所有信息
		List<Book> collectBookList = bookService.findUserCollectBookList(user.getId());
		//根据用户id 编号查找本人近30天的续借书本的图书所有信息
		List<Book> renewdBookList = bookService.findUserRenewdBookList(user.getId());
		//根据用户id 编号查找本人未还书本的图书所有信息
		List<Book> notBackBookList = bookService.findUserNotBackBookList(user.getId());

		//根据用户机构编号查找本机构最受欢迎前十的图书
		List<Book> schoolBookList = bookService.findSchoolBookList(user.getSchool().getId());
		//查找所有被 借阅，收集，续借(至少一个>0) 最多的详细信息
		List<Book> allBookList = bookService.findAllBookList();
		allBookList.addAll(notBackBookList);
//		List<Book> listAll = Lists.newArrayList();
//		listAll.addAll(borrowBookList);
//		listAll.addAll(collectBookList);
//		listAll.addAll(renewdBookList);
//		//list实现去重数据
//		List<Book> indexShow = new ArrayList<Book>(new HashSet<Book>(listAll));
//		System.out.println(indexShow.toString());

		//借出list， 收藏list， 未还list
		user.setBookList(borrowBookList,collectBookList,notBackBookList);
		user.setUser(user,borrowNum,runNum,collectNum);
		model.addAttribute("user", user);
		model.addAttribute("allBookList", allBookList);
		return "modules/book/bookIndexShow";
	}

	@RequiresPermissions("book:book:view")
	@RequestMapping(value = "form")
	public String form(Book book, Model model) {
		model.addAttribute("book", book);
		return "modules/book/bookForm";
	}

	@RequiresPermissions("book:book:edit")
	@RequestMapping(value = "save")
	public String save(Book book, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, book)){
			return form(book, model);
		}
		if(book.getId() == ""){
		    book.setRenewal(0);
		    book.setCollect(0);
		    book.setBorrow(0);
        }
		bookService.save(book);
		addMessage(redirectAttributes, "保存图书表成功");
		return "redirect:"+Global.getAdminPath()+"/book/book/?repage";
	}

	@RequiresPermissions("book:book:edit")
	@RequestMapping(value = "delete")
	public String delete(Book book, RedirectAttributes redirectAttributes) {
		bookService.delete(book);
		addMessage(redirectAttributes, "删除图书表成功");
		return "redirect:"+Global.getAdminPath()+"/book/book/?repage";
	}

	/**
	 * 导入图书
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes){
		try {
			int successNum=0;
			int failureNum=0;
			StringBuilder failureMsg=new StringBuilder();
			ImportExcel ei=new ImportExcel(file, 1, 0);
			List<Book> list=ei.getDataList(Book.class);

			for(Book book:list){
				try {
					bookService.save(book);
					successNum++;
					addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户"+failureMsg);
				} catch (ConstraintViolationException ex) {

					failureMsg.append("<br/>书名 "+book.getName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}

				}catch (Exception ex) {
					failureMsg.append("<br/>图书名 "+book.getName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条图书，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 本图书"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入图书表失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/book/book?repage";
	}

	/**
	 * 下载导入图书表数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
//	@RequiresPermissions("book:book:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "图书表数据导入模板.xlsx";
			List<Book> list = Lists.newArrayList();
			list.add(new Book());
			new ExportExcel("图书表数据", Book.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/book/book?repage";
	}
}