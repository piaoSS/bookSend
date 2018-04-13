/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bookcomment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bookcomment.entity.BookComment;
import com.thinkgem.jeesite.modules.bookcomment.dao.BookCommentDao;

/**
 * 图书评论表Service
 * @author wp
 * @version 2018-04-08
 */
@Service
@Transactional(readOnly = true)
public class BookCommentService extends CrudService<BookCommentDao, BookComment> {

	public BookComment get(String id) {
		return super.get(id);
	}
	
	public List<BookComment> findList(BookComment bookComment) {
		return super.findList(bookComment);
	}
	
	public Page<BookComment> findPage(Page<BookComment> page, BookComment bookComment) {
		return super.findPage(page, bookComment);
	}
	
	@Transactional(readOnly = false)
	public void save(BookComment bookComment) {
		super.save(bookComment);
	}
	
	@Transactional(readOnly = false)
	public void delete(BookComment bookComment) {
		super.delete(bookComment);
	}
	
}