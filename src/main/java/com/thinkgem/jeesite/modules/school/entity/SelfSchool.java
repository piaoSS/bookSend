/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.school.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 学校机构设置Entity
 * @author wp
 * @version 2018-04-07
 */
public class SelfSchool extends TreeEntity<SelfSchool> {
	
	private static final long serialVersionUID = 1L;
//	private SelfSchool parent;		// 父级编号
//	private String parentIds;		// 所有父级编号
//	private String name;		// 名称
//	private Integer sort;		// 排序
	private String type;		// 类型
	private String grade;		// 机构等级/编码
	
	public SelfSchool() {
		super();
		this.type = "1";
	}

	public SelfSchool(String id){
		super(id);
	}

//	@JsonBackReference
//	@NotNull(message="父级编号不能为空")
	public SelfSchool getParent() {
		return parent;
	}

	public void setParent(SelfSchool parent) {
		this.parent = parent;
	}
//
//	@Length(min=1, max=2000, message="所有父级编号长度必须介于 1 和 2000 之间")
//	public String getParentIds() {
//		return parentIds;
//	}
//
//	public void setParentIds(String parentIds) {
//		this.parentIds = parentIds;
//	}
//
//	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getSort() {
//		return sort;
//	}
//
//	public void setSort(String sort) {
//		this.sort = sort;
//	}
	
	@Length(min=1, max=1, message="类型长度必须介于 1 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=1, message="机构等级长度必须介于 1 和 1 之间")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
//	public String getParentId() {
//		return parent != null && parent.getId() != null ? parent.getId() : "0";
//	}
}