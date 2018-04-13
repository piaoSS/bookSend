/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bookborrow.entity;

import com.thinkgem.jeesite.modules.book.entity.Book;
import javax.validation.constraints.NotNull;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 图书借阅表Entity
 * @author wp
 * @version 2018-04-08
 */
public class BookBorrow extends DataEntity<BookBorrow> {
	
	private static final long serialVersionUID = 1L;
	private Book bookId;		// 图书编号
	private User userId;		// 用户编号
	private String status;		// 图书状态（归还，未还，损坏，遗失)
	private Integer num;		// 续借次数（默认续借一个月）
	private String ip;		// 评论IP
	
	public BookBorrow() {
		super();
	}

	public BookBorrow(String id){
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
	
	@Length(min=0, max=1, message="图书状态（归还，未还，损坏，遗失)长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@NotNull(message="续借次数（默认续借一个月）不能为空")
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	@Length(min=0, max=100, message="评论IP长度必须介于 0 和 100 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}