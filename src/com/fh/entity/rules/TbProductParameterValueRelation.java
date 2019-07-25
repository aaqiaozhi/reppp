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

public class TbProductParameterValueRelation {

	private static final long serialVersionUID = -6109590619136943215L;

	/** ID */
	private Long tppvrId;
	
	/** 商品参数组关联表ID  tb_product_parameter_group_relation */
	private Long tppvrRelationId;
	
	/** 名称  */
	private String tppvrName;
	
	/** 值  */
	private String tppvrValue;

	
	

	public Long getTppvrId() {
		return tppvrId;
	}

	public void setTppvrId(Long tppvrId) {
		this.tppvrId = tppvrId;
	}

	public Long getTppvrRelationId() {
		return tppvrRelationId;
	}

	public void setTppvrRelationId(Long tppvrRelationId) {
		this.tppvrRelationId = tppvrRelationId;
	}

	public String getTppvrName() {
		return tppvrName;
	}

	public void setTppvrName(String tppvrName) {
		this.tppvrName = tppvrName;
	}

	public String getTppvrValue() {
		return tppvrValue;
	}

	public void setTppvrValue(String tppvrValue) {
		this.tppvrValue = tppvrValue;
	}

}	