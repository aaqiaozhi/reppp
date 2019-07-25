package com.fh.entity.rules;

/*
 * 

 * 
 */


import java.util.Date;



/**
 * Entity - 商品规格值表
 * 
 * 
 * @version 1.0
 */

public class TbProductSpecificationValue {

	private static final long serialVersionUID = -6109590619136943215L;

	/** ID */
	private Long tpsvId;
	
	/** 创建日期 */
	private Date tpsvAddDate;

	/** 修改日期 */
	private Date tpsvModifyDate;
	
	/** 排序 */
	private Integer tpsvSort;
	
	/** 名称 */
	private String tpsvImage;
	
	/** 图片 */
	private String tpsvName;
	
	/** 参数组ID */
	private Long tpsvSpecificationGroupId;

	/** 是否选中 */
	private Boolean ifSelect = false;
	
	
	

	public Long getTpsvId() {
		return tpsvId;
	}

	public void setTpsvId(Long tpsvId) {
		this.tpsvId = tpsvId;
	}

	public Date getTpsvAddDate() {
		return tpsvAddDate;
	}

	public void setTpsvAddDate(Date tpsvAddDate) {
		this.tpsvAddDate = tpsvAddDate;
	}


	public Date getTpsvModifyDate() {
		return tpsvModifyDate;
	}

	public void setTpsvModifyDate(Date tpsvModifyDate) {
		this.tpsvModifyDate = tpsvModifyDate;
	}


	public Integer getTpsvSort() {
		return tpsvSort;
	}

	public void setTpsvSort(Integer tpsvSort) {
		this.tpsvSort = tpsvSort;
	}


	public String getTpsvName() {
		return tpsvName;
	}
	
	public void setTpsvName(String tpsvName) {
		this.tpsvName = tpsvName;
	}


	public String getTpsvImage() {
		return tpsvImage;
	}

	public void setTpsvImage(String tpsvImage) {
		this.tpsvImage = tpsvImage;
	}


	public Long getTpsvSpecificationGroupId() {
		return tpsvSpecificationGroupId;
	}

	public void setTpsvSpecificationGroupId(Long tpsvSpecificationGroupId) {
		this.tpsvSpecificationGroupId = tpsvSpecificationGroupId;
	}
	
	
	

	public Boolean getIfSelect() {
		return ifSelect;
	}

	public void setIfSelect(Boolean ifSelect) {
		this.ifSelect = ifSelect;
	}
}	