package com.fh.entity.rules;

/*
 * 

 * 
 */




/**
 * Entity - 商品参数值关联表
 * 
 * 
 * @version 1.0
 */

public class TbProductSpecificationValueRelation {

	private static final long serialVersionUID = -6109590619136943215L;

	/** ID */
	private Long tpsvrId;
	
	/** 商品规格组关联ID tb_product_specification_group_relation */
	private Long tpsvrRelationId;
	
	/** 名称  */
	private String tpsvrName;
	
	/** 图片  */
	private String tpsvrImage;

	/** 是否选中  */
	private Boolean ifSelect = false;
	
	
	
	public Long getTpsvrId() {
		return tpsvrId;
	}

	public void setTpsvrId(Long tpsvrId) {
		this.tpsvrId = tpsvrId;
	}


	public Long getTpsvrRelationId() {
		return tpsvrRelationId;
	}

	public void setTpsvrRelationId(Long tpsvrRelationId) {
		this.tpsvrRelationId = tpsvrRelationId;
	}


	public String getTpsvrName() {
		return tpsvrName;
	}

	public void setTpsvrName(String tpsvrName) {
		this.tpsvrName = tpsvrName;
	}


	public String getTpsvrImage() {
		return tpsvrImage;
	}

	public void setTpsvrImage(String tpsvrImage) {
		this.tpsvrImage = tpsvrImage;
	}

	
	

	public Boolean getIfSelect() {
		return ifSelect;
	}

	public void setIfSelect(Boolean ifSelect) {
		this.ifSelect = ifSelect;
	}

	
	
}	