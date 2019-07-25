package com.fh.entity.rules;

/*
 * 

 * 
 */


import java.math.BigDecimal;
import java.util.Date;



/**
 * Entity - 商品-规格关联表
 * 
 * 
 * @version 1.0
 */

public class TbProductSpecificationRelation {

	private static final long serialVersionUID = -6109590619136943215L;

	/** ID */
	private Long tpsrId;
	
	/** 创建日期 */
	private Date tpsrAddDate;

	/** 修改日期 */
	private Date tpsrModifyDate;
	
	/** 排序 */
	private Integer tpsrSort;
	
	/** 商品选中规则值ID，多个之间以下划线 _ 链接 ；比如：选中红色（1）、大码（5），则保持1_5  */
	private String tpsrRelationId;
	
	/** 商品规则值关联表值组合 （多个名称之间用“，”连接）  */
	private String tpsrRelationName;
	
	/** 市场价 */
	private BigDecimal tpsrMarketPrice;
	
	/** 成本价 */
	private BigDecimal tpsrCost;
	
	/** 销售价 */
	private BigDecimal tpsrPrice;
	
	/** 赠送积分 */
	private BigDecimal tpsrPoint;
	
	/** 库存 */
	private Integer tpsrStock;
	
	/** 是否默认 1：是0：否*/
	private Boolean tpsrIsDefault;
	
	/** 是否启用 1：是0：否 */
	private Boolean tpsrIsEnable;
	
	/** 商品ID  */
	private Long tpsrProductsId;

	

	public Long getTpsrId() {
		return tpsrId;
	}

	public void setTpsrId(Long tpsrId) {
		this.tpsrId = tpsrId;
	}

	public Date getTpsrAddDate() {
		return tpsrAddDate;
	}

	public void setTpsrAddDate(Date tpsrAddDate) {
		this.tpsrAddDate = tpsrAddDate;
	}

	public Date getTpsrModifyDate() {
		return tpsrModifyDate;
	}

	public void setTpsrModifyDate(Date tpsrModifyDate) {
		this.tpsrModifyDate = tpsrModifyDate;
	}

	
	public Integer getTpsrSort() {
		return tpsrSort;
	}

	public void setTpsrSort(Integer tpsrSort) {
		this.tpsrSort = tpsrSort;
	}

	
	public String getTpsrRelationId() {
		return tpsrRelationId;
	}

	public void setTpsrRelationId(String tpsrRelationId) {
		this.tpsrRelationId = tpsrRelationId;
	}
	

	public String getTpsrRelationName() {
		return tpsrRelationName;
	}

	public void setTpsrRelationName(String tpsrRelationName) {
		this.tpsrRelationName = tpsrRelationName;
	}


	public BigDecimal getTpsrMarketPrice() {
		return tpsrMarketPrice;
	}

	public void setTpsrMarketPrice(BigDecimal tpsrMarketPrice) {
		this.tpsrMarketPrice = tpsrMarketPrice;
	}

	
	public BigDecimal getTpsrCost() {
		return tpsrCost;
	}

	public void setTpsrCost(BigDecimal tpsrCost) {
		this.tpsrCost = tpsrCost;
	}

	public BigDecimal getTpsrPrice() {
		return tpsrPrice;
	}

	public void setTpsrPrice(BigDecimal tpsrPrice) {
		this.tpsrPrice = tpsrPrice;
	}

	
	public BigDecimal getTpsrPoint() {
		return tpsrPoint;
	}

	public void setTpsrPoint(BigDecimal tpsrPoint) {
		this.tpsrPoint = tpsrPoint;
	}

	
	public Integer getTpsrStock() {
		return tpsrStock;
	}

	public void setTpsrStock(Integer tpsrStock) {
		this.tpsrStock = tpsrStock;
	}


	public Boolean getTpsrIsDefault() {
		return tpsrIsDefault;
	}

	public void setTpsrIsDefault(Boolean tpsrIsDefault) {
		this.tpsrIsDefault = tpsrIsDefault;
	}


	public Boolean getTpsrIsEnable() {
		return tpsrIsEnable;
	}

	public void setTpsrIsEnable(Boolean tpsrIsEnable) {
		this.tpsrIsEnable = tpsrIsEnable;
	}

	public Long getTpsrProductsId() {
		return tpsrProductsId;
	}

	public void setTpsrProductsId(Long tpsrProductsId) {
		this.tpsrProductsId = tpsrProductsId;
	}

}	