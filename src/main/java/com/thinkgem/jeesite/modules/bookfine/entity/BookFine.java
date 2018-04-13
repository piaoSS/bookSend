/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bookfine.entity;

import com.thinkgem.jeesite.modules.book.entity.Book;
import javax.validation.constraints.NotNull;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 图书罚款表Entity
 * @author wp
 * @version 2018-04-08
 */
public class BookFine extends DataEntity<BookFine> {
	
	private static final long serialVersionUID = 1L;
	private Book bookId;		// 图书编号
	private User userId;		// 用户编号
	private String reason;		// 罚款原因
	
	public BookFine() {
		super();
	}

	public BookFine(String id){
		super(id);
	}

	@NotNull(message="图书编号不能为空")
	public Book getBookId() {
		return bookId;
	}

	public void setBookId(Book bookId) {
		this.bookId = bookId;
	}
	
	@NotNull(message="用户编号不能为空")
	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=255, message="罚款原因长度必须介于 0 和 255 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}