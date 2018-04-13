/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bookfine.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bookfine.entity.BookFine;
import com.thinkgem.jeesite.modules.bookfine.dao.BookFineDao;

/**
 * 图书罚款表Service
 * @author wp
 * @version 2018-04-08
 */
@Service
@Transactional(readOnly = true)
public class BookFineService extends CrudService<BookFineDao, BookFine> {

	public BookFine get(String id) {
		return super.get(id);
	}
	
	public List<BookFine> findList(BookFine bookFine) {
		return super.findList(bookFine);
	}
	
	public Page<BookFine> findPage(Page<BookFine> page, BookFine bookFine) {
		return super.findPage(page, bookFine);
	}
	
	@Transactional(readOnly = false)
	public void save(BookFine bookFine) {
		super.save(bookFine);
	}
	
	@Transactional(readOnly = false)
	public void delete(BookFine bookFine) {
		super.delete(bookFine);
	}
	
}