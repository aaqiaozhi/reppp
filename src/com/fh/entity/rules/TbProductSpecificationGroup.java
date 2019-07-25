package com.fh.entity.rules;

/*
 * 

 * 
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Entity - 商品规格组表
 * 
 * 
 * @version 1.0
 */

public class TbProductSpecificationGroup {

	private static final long serialVersionUID = -6109590619136943215L;

	/** ID */
	private Long tpsgId;
	
	/** 创建日期 */
	private Date tpsgAddDate;

	/** 修改日期 */
	private Date tpsgModifyDate;
	
	/** 排序 */
	private Integer tpsgSort;
	
	/** 名称 */
	private String tpsgName;
	
	/** 类型 */
	private Integer tpsgType;
	
	/** 绑定ID */
	private Long tpsgProductCategoryId;
	
	/** 绑定分类名称 */
	private String tpsgProductCategoryName;
	
	/** 规则值名称 */
	private String specificationName;

	/** 规格值列表 */
	private List<TbProductSpecificationValue> valueList = new ArrayList<TbProductSpecificationValue>();
	
	
	public Long getTpsgId() {
		return tpsgId;
	}

	public void setTpsgId(Long tpsgId) {
		this.tpsgId = tpsgId;
	}

	public Date getTpsgAddDate() {
		return tpsgAddDate;
	}

	public void setTpsgAddDate(Date tpsgAddDate) {
		this.tpsgAddDate = tpsgAddDate;
	}

	public Date getTpsgModifyDate() {
		return tpsgModifyDate;
	}

	public void setTpsgModifyDate(Date tpsgModifyDate) {
		this.tpsgModifyDate = tpsgModifyDate;
	}

	public Integer getTpsgSort() {
		return tpsgSort;
	}

	public void setTpsgSort(Integer tpsgSort) {
		this.tpsgSort = tpsgSort;
	}

	public String getTpsgName() {
		return tpsgName;
	}
	
	public void setTpsgName(String tpsgName) {
		this.tpsgName = tpsgName;
	}

	
	public Integer getTpsgType() {
		return tpsgType;
	}

	public void setTpsgType(Integer tpsgType) {
		this.tpsgType = tpsgType;
	}

	public Long getTpsgProductCategoryId() {
		return tpsgProductCategoryId;
	}

	public void setTpsgProductCategoryId(Long tpsgProductCategoryId) {
		this.tpsgProductCategoryId = tpsgProductCategoryId;
	}

	

	public String getTpsgProductCategoryName() {
		return tpsgProductCategoryName;
	}

	public void setTpsgProductCategoryName(String tpsgProductCategoryName) {
		this.tpsgProductCategoryName = tpsgProductCategoryName;
	}

	public String getSpecificationName() {
		return specificationName;
	}

	public void setSpecificationName(String specificationName) {
		this.specificationName = specificationName;
	}

	
	public List<TbProductSpecificationValue> getValueList() {
		return valueList;
	}

	public void setValueList(List<TbProductSpecificationValue> valueList) {
		this.valueList = valueList;
	}

	
}	