package com.fh.entity.rules;

/*
 * 

 * 
 */


import java.util.ArrayList;
import java.util.List;



/**
 * Entity - 商品参数组关联表
 * 
 * 
 * @version 1.0
 */

public class TbProductParameterGroupRelation {

	
	private static final long serialVersionUID = -6109590619136943215L;

	/** ID */
	private Long tppgrId;
	
	/** 商品ID */
	private Long tppgrProductId;
	
	/** 商品参数组值  */
	private String tppgrValue;

	/** 商品参数值*/
	private TbProductParameterValueRelation[] parameterValueRelations;
	
	/** 下级分类 */
	private List<TbProductParameterValueRelation> children = new ArrayList<TbProductParameterValueRelation>();
	
	
	
	
	
	
	public Long getTppgrId() {
		return tppgrId;
	}

	public void setTppgrId(Long tppgrId) {
		this.tppgrId = tppgrId;
	}

	
	public Long getTppgrProductId() {
		return tppgrProductId;
	}

	public void setTppgrProductId(Long tppgrProductId) {
		this.tppgrProductId = tppgrProductId;
	}


	public String getTppgrValue() {
		return tppgrValue;
	}

	public void setTppgrValue(String tppgrValue) {
		this.tppgrValue = tppgrValue;
	}


	public TbProductParameterValueRelation[] getParameterValueRelations() {
		return parameterValueRelations;
	}

	public void setParameterValueRelations(
			TbProductParameterValueRelation[] parameterValueRelations) {
		this.parameterValueRelations = parameterValueRelations;
	}

	
	public List<TbProductParameterValueRelation> getChildren() {
		return children;
	}

	public void setChildren(List<TbProductParameterValueRelation> children) {
		this.children = children;
	}

	
}	