/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bookborrow.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bookborrow.entity.BookBorrow;
import com.thinkgem.jeesite.modules.bookborrow.dao.BookBorrowDao;

/**
 * 图书借阅表Service
 * @author wp
 * @version 2018-04-08
 */
@Service
@Transactional(readOnly = true)
public class BookBorrowService extends CrudService<BookBorrowDao, BookBorrow> {

	/**
	 * 根据用户id 编号查找本人借阅书本的数量
	 * @param userId
	 * @return
	 */
	public Integer borrowNum(String userId){
		return dao.borrowNum(userId);
	}

	/**
	 * 根据用户id 编号查找本人未还书本的数量
	 * @param userId
	 * @return
	 */
	public Integer runNum(String userId){
		return dao.runNum(userId);
	}

	public BookBorrow get(String id) {
		return super.get(id);
	}
	
	public List<BookBorrow> findList(BookBorrow bookBorrow) {
		return super.findList(bookBorrow);
	}
	
	public Page<BookBorrow> findPage(Page<BookBorrow> page, BookBorrow bookBorrow) {
		return super.findPage(page, bookBorrow);
	}
	
	@Transactional(readOnly = false)
	public void save(BookBorrow bookBorrow) {
		super.save(bookBorrow);
	}
	
	@Transactional(readOnly = false)
	public void delete(BookBorrow bookBorrow) {
		super.delete(bookBorrow);
	}
	
}