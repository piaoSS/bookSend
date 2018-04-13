/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bookcomment.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bookcomment.entity.BookComment;

/**
 * 图书评论表DAO接口
 * @author wp
 * @version 2018-04-08
 */
@MyBatisDao
public interface BookCommentDao extends CrudDao<BookComment> {
	
}