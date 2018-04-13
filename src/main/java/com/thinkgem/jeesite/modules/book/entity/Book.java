/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.library.entity.SelfLibrary;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 图书表Entity
 * @author wp
 * @version 2018-04-08
 */
public class Book extends DataEntity<Book> {

	private static final long serialVersionUID = 1L;
	private SelfLibrary library;		// 图书存放地址
	private String photo;		// 图片
	private String name;		// 书名
	private String pressName;		// 出版社名称
	private Date pressTime;		// 出版日期
	private Integer total;		// 总数量
	private Integer num;		// 不在库数量
	private String category;		// 图书类别
	private String author;		// 作者
	private Double price;		// 单价
	private Date storageDate;		// 入库时间
	private Integer collect;		// 收藏数
	private Integer borrow;		// 借阅数
	private Integer renewal;		// 续借数
    private String remarks;	// 备注

	private List<Book> bookList = Lists.newArrayList(); // 拥有列表

	public Book() {
		super();
	}

	public Book(String id){
		super(id);
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}


	@JsonIgnore
	@ExcelField(value="library.name",title="图书地址", align=2,type=1, sort=10)
	public SelfLibrary getLibrary() {
		return library;
	}

	public void setLibrary(SelfLibrary library) {
		this.library = library;
	}

	@Length(min=0, max=1000, message="图片地址长度必须介于 0 和 1000 之间")
	@ExcelField(title="图片地址", align=2, sort=15,type=2)
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Length(min=0, max=200, message="书名长度必须介于 0 和 200 之间")
	@ExcelField(title="书名", align=2, sort=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Length(min=0, max=100, message="图书类别长度必须介于 0 和 100 之间")
    @ExcelField(title="图书类别", align=2, sort=21, dictType="book_category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Length(min=0, max=100, message="作者长度必须介于 0 和 100 之间")
    @ExcelField(title="作者", align=2, sort=22)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @ExcelField(title="单价", align=2, sort=23)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

	@Length(min=0, max=100, message="出版社名称长度必须介于 0 和 200 之间")
	@ExcelField(title="出版社名称", align=2, sort=25)
	public String getPressName() {
		return pressName;
	}

	public void setPressName(String pressName) {
		this.pressName = pressName;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="出版日期", align=2, sort=30)
	public Date getPressTime() {
		return pressTime;
	}

	public void setPressTime(Date pressTime) {
		this.pressTime = pressTime;
	}

	@ExcelField(title="总数", align=2, sort=35)
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@ExcelField(title="不在库数量", align=2, sort=40)
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="入库时间", align=2, sort=45)
	public Date getStorageDate() {
		return storageDate;
	}

	public void setStorageDate(Date storageDate) {
		this.storageDate = storageDate;
	}

	@ExcelField(title="收藏数", align=2, sort=50)
	public Integer getCollect() {
		return collect;
	}

	public void setCollect(Integer collect) {
		this.collect = collect;
	}

	@ExcelField(title="借阅数", align=2, sort=55)
	public Integer getBorrow() {
		return borrow;
	}

	public void setBorrow(Integer borrow) {
		this.borrow = borrow;
	}

	@ExcelField(title="续借数", align=2, sort=60)
	public Integer getRenewal() {
		return renewal;
	}

	public void setRenewal(Integer renewal) {
		this.renewal = renewal;
	}

    @Override
    @Length(min=0, max=500, message="图书介绍必须介于 0 和 500 之间")
    @ExcelField(title="图书介绍", align=2, sort=65)
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Book book = (Book) o;
		return Objects.equals(id, book.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}