/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bookcomment.entity;

import com.thinkgem.jeesite.modules.book.entity.Book;
import javax.validation.constraints.NotNull;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 图书评论表Entity
 * @author wp
 * @version 2018-04-08
 */
public class BookComment extends DataEntity<BookComment> {
	
	private static final long serialVersionUID = 1L;
	private Book bookId;		// 图书编号
	private User userId;		// 用户编号
	private String content;		// 评论内容
	private String ip;		// 评论IP
	private User auditUserId;		// 审核人
	private Date auditDate;		// 审核时间
	
	public BookComment() {
		super();
	}

	public BookComment(String id){
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
	
	@Length(min=0, max=255, message="评论内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=100, message="评论IP长度必须介于 0 和 100 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public User getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(User auditUserId) {
		this.auditUserId = auditUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
}