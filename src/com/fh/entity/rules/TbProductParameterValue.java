package com.fh.entity.rules;

/*
 * 

 * 
 */


import java.util.Date;



/**
 * Entity - 商品参数值表
 * 
 * 
 * @version 1.0
 */

public class TbProductParameterValue {

	private static final long serialVersionUID = -6109590619136943215L;

	/** ID */
	private Long tppvId;
	
	/** 创建日期 */
	private Date tppvAddDate;

	/** 修改日期 */
	private Date tppvModifyDate;
	
	/** 排序 */
	private Integer tppvSort;
	
	/** 名称 */
	private String tppvName;
	
	/** 参数组ID */
	private Long tppvParameterGroupId;

	/** 值 */
	private String tppvValue = "";
	
	

	public Long getTppvId() {
		return tppvId;
	}

	public void setTppvId(Long tppvId) {
		this.tppvId = tppvId;
	}


	public Date getTppvAddDate() {
		return tppvAddDate;
	}

	public void setTppvAddDate(Date tppvAddDate) {
		this.tppvAddDate = tppvAddDate;
	}

	public Date getTppvModifyDate() {
		return tppvModifyDate;
	}

	public void setTppvModifyDate(Date tppvModifyDate) {
		this.tppvModifyDate = tppvModifyDate;
	}

	public Integer getTppvSort() {
		return tppvSort;
	}

	public void setTppvSort(Integer tppvSort) {
		this.tppvSort = tppvSort;
	}


	public String getTppvName() {
		return tppvName;
	}
	
	public void setTppvName(String tppvName) {
		this.tppvName = tppvName;
	}


	public Long getTppvParameterGroupId() {
		return tppvParameterGroupId;
	}

	public void setTppvParameterGroupId(Long tppvParameterGroupId) {
		this.tppvParameterGroupId = tppvParameterGroupId;
	}

	
	public String getTppvValue() {
		return tppvValue;
	}

	public void setTppvValue(String tppvValue) {
		this.tppvValue = tppvValue;
	}
	
	
}	