/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bookborrow.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bookborrow.entity.BookBorrow;
import org.apache.ibatis.annotations.Param;

/**
 * 图书借阅表DAO接口
 * @author wp
 * @version 2018-04-08
 */
@MyBatisDao
public interface BookBorrowDao extends CrudDao<BookBorrow> {

    /**
     * 根据用户id 编号查找本人借阅书本的数量
     * @param userId
     * @return
     */
    public Integer borrowNum(@Param("userId") String userId);

    /**
     * 根据用户id 编号查找本人正在借的书本的数量
     * @param userId
     * @return
     */
    public Integer runNum(@Param("userId") String userId);



}