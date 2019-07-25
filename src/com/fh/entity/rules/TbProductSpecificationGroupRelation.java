package com.fh.entity.rules;

/*
 * 

 * 
 */


import java.util.ArrayList;
import java.util.List;

/**
 * Entity - 商品规格组关联表
 * 
 * 
 * @version 1.0
 */

public class TbProductSpecificationGroupRelation {

	private static final long serialVersionUID = -6109590619136943215L;

	/** ID */
	private Long tpsgrId;
	
	/** 商品ID */
	private Long tpsgrProductsId;
	
	/** 名称  */
	private String tpsgrName;
	
	/** 类型  */
	private Integer tpsgrType;

	private int TPSVR_RELATION_ID;

	private TbProductSpecificationValueRelation[] specificationValueRelations;
	
	
	

	public int getTPSVR_RELATION_ID() {
		return TPSVR_RELATION_ID;
	}

	public void setTPSVR_RELATION_ID(int tPSVR_RELATION_ID) {
		TPSVR_RELATION_ID = tPSVR_RELATION_ID;
	}

	public TbProductSpecificationValueRelation[] getSpecificationValueRelations() {
		return specificationValueRelations;
	}

	public void setSpecificationValueRelations(
			TbProductSpecificationValueRelation[] specificationValueRelations) {
		this.specificationValueRelations = specificationValueRelations;
	}

	public Long getTpsgrId() {
		return tpsgrId;
	}

	public void setTpsgrId(Long tpsgrId) {
		this.tpsgrId = tpsgrId;
	}

	
	public Long getTpsgrProductsId() {
		return tpsgrProductsId;
	}

	public void setTpsgrProductsId(Long tpsgrProductsId) {
		this.tpsgrProductsId = tpsgrProductsId;
	}


	public String getTpsgrName() {
		return tpsgrName;
	}

	public void setTpsgrName(String tpsgrName) {
		this.tpsgrName = tpsgrName;
	}


	public Integer getTpsgrType() {
		return tpsgrType;
	}
	
	public void setTpsgrType(Integer tpsgrType) {
		this.tpsgrType = tpsgrType;
	}
	
	
	
	


	

}	