package com.fh.entity.rules;


import java.math.BigDecimal;
import java.util.Date;

import javax.management.loading.PrivateClassLoader;



/**
 * Entity - 商品表
 * 
 * 
 * @version 1.0
 */

public class TbProduct {

	private static final long serialVersionUID = -6109590619136943215L;

	/***
	 * 商品ID
	 */
	private String  productId;
	
	/** ID */
	private Long tpId;
	
	/** 创建日期 */
	private Date tpAddDate;

	/** 修改日期 */
	private Date tpModifyDate;
	
	/** 商品编号 */
	private String tpSn;
	
	/** 市场价 */
	private BigDecimal tpMarketPrice;
	
	/** 成本价 */
	private BigDecimal tpCost;
	
	/** 销售价 */
	private BigDecimal tpPrice;
	
	/** 使用现金 */
	private BigDecimal tpUsecash;
	
	/** 赠送积分 */
	private BigDecimal tpPoint;
	
	/** 名称 */
	private String tpName;
	
	/** 副标题 */
	private String tpViceName;
	
	/** 缩略图 */
	private String tpImage;
	
	/** 点击数 */
	private Integer tpHits;

	/** 介绍 */
	private String tpDesp;
	
	/** 是否列出  1：是 0：否 */
	private Boolean tpIsList;
	
	/** 是否上架 1：是 0：否 */
	private Boolean tpIsMarketable;
	
	/** 是否置顶 1：是 0：否 */
	private Boolean tpIsTop;
	
	/** 是否为赠品  1：是 0：否  */
	private Boolean tpIsGift;
	
	/** 搜索关键词 */
	private String tpKeyword;
	
	/** 备注 */
	private String tpMemo;

	/** 月点击数 */
	private Long tpMonthHits;
	
	/** 月点击数更新日期 */
	private Date tpMonthHitsDate;
	
	/** 月销量 */
	private Long tpMonthSales;
	
	/** 月销量更新日期 */
	private Date tpMonthSalesDate;
	
	/** 周点击数 */
	private Long tpWeekHits;
	
	/** 周点击数更新日期 */
	private Date tpWeekHitsDate;
	
	/** 周销量 */
	private Long tpWeekSales;
	
	/** 周销量更新日期 */
	private Date tpWeekSalesDate;
	
	/** 销量 */
	private Long tpSales;
	
	/** 评分 */
	private BigDecimal tpScore;
	
	/** 总评评论人数 */
	private Long tpScoreCount;
	
	/** 晒图评论数 */
	private Long tpShaiScoreCount;
	
	/** 好评数 */
	private Long tpGoodScoreCount;
	
	/** 中评数  */
	private Long tpMiddleScoreCount;
	
	/** 差评数  */
	private Long tpBadScoreCount;
		
	/** 库存 */
	private Integer tpStock;
	
	/** 单位*/
	private String tpUnit;
	
	/** 重量 */
	private BigDecimal tpWeight;
	
	/** 品牌ID */
	private Long tpBrandId;
	
	/** 一级分类ID */
	private Long tpProductCategory1Id;
	
	/** 二级分类ID */
	private Long tpProductCategory2Id;
	
	/** 三级分类ID */
	private Long tpProductCategory3Id;
	
	/** 商户ID */
	private Long tpBusinessId;
	
	/** 运费模板ID ，为空则免运费  */
	private Long tpFreightTemplateId;
	
	
	
	
	/** 品牌名称 */
	private String tpBrandName;
	
	/** 一级分类名称 */
	private String tpProductCategory1Name;
	
	/** 二级分类名称 */
	private String tpProductCategory2Name;
	
	/** 三级分类名称 */
	private String tpProductCategory3Name;
	
	/** 所属店铺*/
	private String tpBusinessName;


	
	/** 商品参数组*/
	private TbProductParameterGroupRelation[] parameterGroupRelations;
	
	/** 商品规格组*/
	private TbProductSpecificationGroupRelation[] specificationGroupRelations;
	
	/** 商品规格*/
	private TbProductSpecificationRelation[] specificationRelations;
	
	/** to_love_donation 爱心捐赠 */
	private BigDecimal tpLoveDonation;



	public Long getTpId() {
		return tpId;
	}

	public void setTpId(Long tpId) {
		this.tpId = tpId;
	}

	public Date getTpAddDate() {
		return tpAddDate;
	}

	public void setTpAddDate(Date tpAddDate) {
		this.tpAddDate = tpAddDate;
	}

	public Date getTpModifyDate() {
		return tpModifyDate;
	}

	public void setTpModifyDate(Date tpModifyDate) {
		this.tpModifyDate = tpModifyDate;
	}

	public String getTpSn() {
		return tpSn;
	}

	public void setTpSn(String tpSn) {
		this.tpSn = tpSn;
	}


	public BigDecimal getTpMarketPrice() {
		return tpMarketPrice;
	}

	public void setTpMarketPrice(BigDecimal tpMarketPrice) {
		this.tpMarketPrice = tpMarketPrice;
	}


	public BigDecimal getTpCost() {
		return tpCost;
	}

	public void setTpCost(BigDecimal tpCost) {
		this.tpCost = tpCost;
	}

	
	public BigDecimal getTpPrice() {
		return tpPrice;
	}

	public void setTpPrice(BigDecimal tpPrice) {
		this.tpPrice = tpPrice;
	}
	

	public BigDecimal getTpUsecash() {
		return tpUsecash;
	}

	public void setTpUsecash(BigDecimal tpUsecash) {
		this.tpUsecash = tpUsecash;
	}


	public BigDecimal getTpPoint() {
		return tpPoint;
	}

	public void setTpPoint(BigDecimal tpPoint) {
		this.tpPoint = tpPoint;
	}


	public String getTpName() {
		return tpName;
	}

	public void setTpName(String tpName) {
		this.tpName = tpName;
	}


	public String getTpViceName() {
		return tpViceName;
	}

	public void setTpViceName(String tpViceName) {
		this.tpViceName = tpViceName;
	}


	public String getTpImage() {
		return tpImage;
	}

	public void setTpImage(String tpImage) {
		this.tpImage = tpImage;
	}


	public Integer getTpHits() {
		return tpHits;
	}

	public void setTpHits(Integer tpHits) {
		this.tpHits = tpHits;
	}


	public String getTpDesp() {
		return tpDesp;
	}

	public void setTpDesp(String tpDesp) {
		this.tpDesp = tpDesp;
	}


	public Boolean getTpIsList() {
		return tpIsList;
	}

	public void setTpIsList(Boolean tpIsList) {
		this.tpIsList = tpIsList;
	}


	public Boolean getTpIsMarketable() {
		return tpIsMarketable;
	}

	public void setTpIsMarketable(Boolean tpIsMarketable) {
		this.tpIsMarketable = tpIsMarketable;
	}


	public Boolean getTpIsTop() {
		return tpIsTop;
	}

	public void setTpIsTop(Boolean tpIsTop) {
		this.tpIsTop = tpIsTop;
	}


	public Boolean getTpIsGift() {
		return tpIsGift;
	}

	public void setTpIsGift(Boolean tpIsGift) {
		this.tpIsGift = tpIsGift;
	}


	public String getTpKeyword() {
		return tpKeyword;
	}

	public void setTpKeyword(String tpKeyword) {
		this.tpKeyword = tpKeyword;
	}


	public String getTpMemo() {
		return tpMemo;
	}

	public void setTpMemo(String tpMemo) {
		this.tpMemo = tpMemo;
	}

	
	public Long getTpMonthHits() {
		return tpMonthHits;
	}

	public void setTpMonthHits(Long tpMonthHits) {
		this.tpMonthHits = tpMonthHits;
	}

  
	public Date getTpMonthHitsDate() {
		return tpMonthHitsDate;
	}

	public void setTpMonthHitsDate(Date tpMonthHitsDate) {
		this.tpMonthHitsDate = tpMonthHitsDate;
	}

	public Long getTpMonthSales() {
		return tpMonthSales;
	}

	public void setTpMonthSales(Long tpMonthSales) {
		this.tpMonthSales = tpMonthSales;
	}

	
	public Date getTpMonthSalesDate() {
		return tpMonthSalesDate;
	}

	public void setTpMonthSalesDate(Date tpMonthSalesDate) {
		this.tpMonthSalesDate = tpMonthSalesDate;
	}

	
	public Long getTpWeekHits() {
		return tpWeekHits;
	}

	public void setTpWeekHits(Long tpWeekHits) {
		this.tpWeekHits = tpWeekHits;
	}

	
	public Date getTpWeekHitsDate() {
		return tpWeekHitsDate;
	}

	public void setTpWeekHitsDate(Date tpWeekHitsDate) {
		this.tpWeekHitsDate = tpWeekHitsDate;
	}

	public Long getTpWeekSales() {
		return tpWeekSales;
	}

	public void setTpWeekSales(Long tpWeekSales) {
		this.tpWeekSales = tpWeekSales;
	}


	public Date getTpWeekSalesDate() {
		return tpWeekSalesDate;
	}

	public void setTpWeekSalesDate(Date tpWeekSalesDate) {
		this.tpWeekSalesDate = tpWeekSalesDate;
	}


	public Long getTpSales() {
		return tpSales;
	}

	public void setTpSales(Long tpSales) {
		this.tpSales = tpSales;
	}


	public BigDecimal getTpScore() {
		return tpScore;
	}

	public void setTpScore(BigDecimal tpScore) {
		this.tpScore = tpScore;
	}


	public Long getTpScoreCount() {
		return tpScoreCount;
	}

	public void setTpScoreCount(Long tpScoreCount) {
		this.tpScoreCount = tpScoreCount;
	}


	public Long getTpShaiScoreCount() {
		return tpShaiScoreCount;
	}
	
	public void setTpShaiScoreCount(Long tpShaiScoreCount) {
		this.tpShaiScoreCount = tpShaiScoreCount;
	}


	public Long getTpGoodScoreCount() {
		return tpGoodScoreCount;
	}

	public void setTpGoodScoreCount(Long tpGoodScoreCount) {
		this.tpGoodScoreCount = tpGoodScoreCount;
	}


	public Long getTpMiddleScoreCount() {
		return tpMiddleScoreCount;
	}

	public void setTpMiddleScoreCount(Long tpMiddleScoreCount) {
		this.tpMiddleScoreCount = tpMiddleScoreCount;
	}


	public Long getTpBadScoreCount() {
		return tpBadScoreCount;
	}

	public void setTpBadScoreCount(Long tpBadScoreCount) {
		this.tpBadScoreCount = tpBadScoreCount;
	}


	public Integer getTpStock() {
		return tpStock;
	}

	public void setTpStock(Integer tpStock) {
		this.tpStock = tpStock;
	}


	public String getTpUnit() {
		return tpUnit;
	}

	public void setTpUnit(String tpUnit) {
		this.tpUnit = tpUnit;
	}

	
	public BigDecimal getTpWeight() {
		return tpWeight;
	}

	public void setTpWeight(BigDecimal tpWeight) {
		this.tpWeight = tpWeight;
	}


	public Long getTpBrandId() {
		return tpBrandId;
	}

	public void setTpBrandId(Long tpBrandId) {
		this.tpBrandId = tpBrandId;
	}


	public Long getTpProductCategory1Id() {
		return tpProductCategory1Id;
	}

	public void setTpProductCategory1Id(Long tpProductCategory1Id) {
		this.tpProductCategory1Id = tpProductCategory1Id;
	}


	public Long getTpProductCategory2Id() {
		return tpProductCategory2Id;
	}

	public void setTpProductCategory2Id(Long tpProductCategory2Id) {
		this.tpProductCategory2Id = tpProductCategory2Id;
	}

	public Long getTpProductCategory3Id() {
		return tpProductCategory3Id;
	}

	public void setTpProductCategory3Id(Long tpProductCategory3Id) {
		this.tpProductCategory3Id = tpProductCategory3Id;
	}

	
	public Long getTpBusinessId() {
		return tpBusinessId;
	}

	public void setTpBusinessId(Long tpBusinessId) {
		this.tpBusinessId = tpBusinessId;
	}


	public Long getTpFreightTemplateId() {
		return tpFreightTemplateId;
	}
	
	public void setTpFreightTemplateId(Long tpFreightTemplateId) {
		this.tpFreightTemplateId = tpFreightTemplateId;
	}
	
	
	public BigDecimal getTpLoveDonation() {
		return tpLoveDonation;
	}

	public void setTpLoveDonation(BigDecimal tpLoveDonation) {
		this.tpLoveDonation = tpLoveDonation;
	}
	
	
	

	public String getTpBrandName() {
		return tpBrandName;
	}

	public void setTpBrandName(String tpBrandName) {
		this.tpBrandName = tpBrandName;
	}


	public String getTpProductCategory1Name() {
		return tpProductCategory1Name;
	}

	public void setTpProductCategory1Name(String tpProductCategory1Name) {
		this.tpProductCategory1Name = tpProductCategory1Name;
	}


	public String getTpProductCategory2Name() {
		return tpProductCategory2Name;
	}

	public void setTpProductCategory2Name(String tpProductCategory2Name) {
		this.tpProductCategory2Name = tpProductCategory2Name;
	}


	public String getTpProductCategory3Name() {
		return tpProductCategory3Name;
	}

	public void setTpProductCategory3Name(String tpProductCategory3Name) {
		this.tpProductCategory3Name = tpProductCategory3Name;
	}


	public String getTpBusinessName() {
		return tpBusinessName;
	}

	public void setTpBusinessName(String tpBusinessName) {
		this.tpBusinessName = tpBusinessName;
	}




	public TbProductParameterGroupRelation[] getParameterGroupRelations() {
		return parameterGroupRelations;
	}

	public void setParameterGroupRelations(
			TbProductParameterGroupRelation[] parameterGroupRelations) {
		this.parameterGroupRelations = parameterGroupRelations;
	}

	
	public TbProductSpecificationGroupRelation[] getSpecificationGroupRelations() {
		return specificationGroupRelations;
	}

	public void setSpecificationGroupRelations(
			TbProductSpecificationGroupRelation[] specificationGroupRelations) {
		this.specificationGroupRelations = specificationGroupRelations;
	}

	
	public TbProductSpecificationRelation[] getSpecificationRelations() {
		return specificationRelations;
	}

	public void setSpecificationRelations(
			TbProductSpecificationRelation[] specificationRelations) {
		this.specificationRelations = specificationRelations;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	
}	