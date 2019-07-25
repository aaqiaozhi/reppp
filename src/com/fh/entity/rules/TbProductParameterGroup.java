package com.fh.entity.rules;


/*
 * 

 * 
 */


import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Entity - 商品参数组表
 * 
 * 
 * @version 1.0
 */

public class TbProductParameterGroup {

	private static final long serialVersionUID = -6109590619136943215L;

	/** ID */
	private Long tppgId;
	
	/** 创建日期 */
	private Date tppgAddDate;

	/** 修改日期 */
	private Date tppgModifyDate;
	
	/** 排序 */
	private Integer tppgSort;
	
	/** 名称 */
	private String tppgName;
	
	/** 绑定ID */
	private Long tppgProductCategoryId;
	
	/** 绑定分类名称 */
	private String tppgProductCategoryName;
	
	/** 参数名称 */
	private String parameterName;

	/** 参数值列表 */
	private List<TbProductParameterValue> valueList = new ArrayList<TbProductParameterValue>();
	
	
	public Long getTppgId() {
		return tppgId;
	}

	public void setTppgId(Long tppgId) {
		this.tppgId = tppgId;
	}


	public Date getTppgAddDate() {
		return tppgAddDate;
	}

	public void setTppgAddDate(Date tppgAddDate) {
		this.tppgAddDate = tppgAddDate;
	}

	public Date getTppgModifyDate() {
		return tppgModifyDate;
	}

	public void setTppgModifyDate(Date tppgModifyDate) {
		this.tppgModifyDate = tppgModifyDate;
	}


	public Integer getTppgSort() {
		return tppgSort;
	}

	public void setTppgSort(Integer tppgSort) {
		this.tppgSort = tppgSort;
	}

	public String getTppgName() {
		return tppgName;
	}
	
	public void setTppgName(String tppgName) {
		this.tppgName = tppgName;
	}

	public Long getTppgProductCategoryId() {
		return tppgProductCategoryId;
	}

	public void setTppgProductCategoryId(Long tppgProductCategoryId) {
		this.tppgProductCategoryId = tppgProductCategoryId;
	}

	
	

	public String getTppgProductCategoryName() {
		return tppgProductCategoryName;
	}

	public void setTppgProductCategoryName(String tppgProductCategoryName) {
		this.tppgProductCategoryName = tppgProductCategoryName;
	}


	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public List<TbProductParameterValue> getValueList() {
		return valueList;
	}

	public void setValueList(List<TbProductParameterValue> valueList) {
		this.valueList = valueList;
	}
	
	
	
}	