/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.book.entity.Book;
import com.thinkgem.jeesite.modules.book.dao.BookDao;

/**
 * 图书表Service
 * @author wp
 * @version 2018-04-08
 */
@Service
@Transactional(readOnly = true)
public class BookService extends CrudService<BookDao, Book> {

	/**
	 * 根据用户id 编号查找本人近30天的借阅书本的图书所有信息
	 * @param userId
	 * @return
	 */
	public List<Book> findUserBorrowBookList(String userId){
		return dao.findUserBorrowBookList(userId);
	}

	/**
	 * 根据用户id 编号查找本人近30天的收藏书本的图书所有信息
	 * @param userId
	 * @return
	 */
	public List<Book> findUserCollectBookList(String userId){
		return dao.findUserCollectBookList(userId);
	}

	/**
	 * 根据用户id 编号查找本人近30天的续借书本的图书所有信息
	 * @param userId
	 * @return
	 */
	public List<Book> findUserRenewdBookList(String userId){
		return dao.findUserRenewdBookList(userId);
	}

	/**
	 * 根据用户id 编号查找本人未还书本的图书所有信息
	 * @param userId
	 * @return
	 */
	public List<Book> findUserNotBackBookList(String userId){
		return dao.findUserNotBackBookList(userId);
	}


	/**
	 * 根据根据用户机构编号查找本机构最受欢迎的图书
	 * @param schoolId
	 * @return
	 */
	public List<Book> findSchoolBookList(String schoolId){
		return dao.findSchoolBookList(schoolId);
	}

	/**
	 * 查找所有被 借阅，收集，续借(至少一个>0) 最多的详细信息
	 */
	public List<Book> findAllBookList(){
		return dao.findAllBookList();
	}

	public Book get(String id) {
		return super.get(id);
	}
	
	public List<Book> findList(Book book) {
		return super.findList(book);
	}
	
	public Page<Book> findPage(Page<Book> page, Book book) {
		return super.findPage(page, book);
	}
	
	@Transactional(readOnly = false)
	public void save(Book book) {
		super.save(book);
	}
	
	@Transactional(readOnly = false)
	public void delete(Book book) {
		super.delete(book);
	}
	
}