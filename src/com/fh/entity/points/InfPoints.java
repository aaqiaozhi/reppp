package com.fh.entity.points;

import java.util.List;

/** 
 * 说明：资讯分类 实体类
 * 创建人：JCKJ
 * 创建时间：2018-04-04
 */
public class InfPoints{ 
	
	private String TIC_ID;	//主键
	
	private String TIC_PARENT_ID;	
	
	
	//父类ID
	private String target;
	private InfPoints infpoints;
	private List<InfPoints> subInfPoints;
	private boolean hasInfPoints = false;
	private String treeurl;
	
	private String TIC_NAME;			//分类名称
	
	
	
	
	public String getTIC_ID() {
		return TIC_ID;
	}
	public void setTIC_ID(String tIC_ID) {
		TIC_ID = tIC_ID;
	}
	public String getTIC_PARENT_ID() {
		return TIC_PARENT_ID;
	}
	public void setTIC_PARENT_ID(String tIC_PARENT_ID) {
		TIC_PARENT_ID = tIC_PARENT_ID;
	}
	public String getFTIC_NAME() {
		return TIC_NAME;
	}
	public void setFTIC_NAME(String TIC_NAME) {
		this.TIC_NAME = TIC_NAME;
	}
	private String TIC_ADD_DATE;			//创建时间
	public String getFTIC_ADD_DATE() {
		return TIC_ADD_DATE;
	}
	public void setFTIC_ADD_DATE(String TIC_ADD_DATE) {
		this.TIC_ADD_DATE = TIC_ADD_DATE;
	}
	private int TIC_STATUS;				//状态
	public int getFTIC_STATUS() {
		return TIC_STATUS;
	}
	public void setFTIC_STATUS(int TIC_STATUS) {
		this.TIC_STATUS = TIC_STATUS;
	}

	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public InfPoints getInfPoints() {
		return infpoints;
	}
	public void setInfPoints(InfPoints infpoints) {
		this.infpoints = infpoints;
	}
	public List<InfPoints> getSubInfPoints() {
		return subInfPoints;
	}
	public void setSubInfPoints(List<InfPoints> subInfPoints) {
		this.subInfPoints = subInfPoints;
	}
	public boolean isHasInfPoints() {
		return hasInfPoints;
	}
	public void setHasInfPoints(boolean hasInfPoints) {
		this.hasInfPoints = hasInfPoints;
	}
	public String getTreeurl() {
		return treeurl;
	}
	public void setTreeurl(String treeurl) {
		this.treeurl = treeurl;
	}
	
}
