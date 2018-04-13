/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.book.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.book.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 图书表DAO接口
 * @author wp
 * @version 2018-04-08
 */
@MyBatisDao
public interface BookDao extends CrudDao<Book> {

    /**
     * 根据用户id 编号查找本人近30天的借阅书本的图书所有信息
     * @param userId
     * @return
     */
    public List<Book> findUserBorrowBookList(@Param("userId") String userId);

    /**
     * 根据用户id 编号查找本人近30天的收藏书本的图书所有信息
     * @param userId
     * @return
     */
    public List<Book> findUserCollectBookList(@Param("userId") String userId);

    /**
     * 根据用户id 编号查找本人近30天的续借书本的图书所有信息
     * @param userId
     * @return
     */
    public List<Book> findUserRenewdBookList(@Param("userId") String userId);

    /**
     * 根据用户id 编号查找本人未还书本的图书所有信息
     * @param userId
     * @return
     */
    public List<Book> findUserNotBackBookList(@Param("userId") String userId);


    /**
     * 根据根据用户机构编号查找本机构最受欢迎的图书
     * @param schoolId
     * @return
     */
    public List<Book> findSchoolBookList(@Param("schoolId") String schoolId);

    /**
     * 查找所有被 借阅，收集，续借(至少一个>0) 最多的详细信息
     */
    public List<Book> findAllBookList();
}