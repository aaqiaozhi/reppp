package com.fh.service.points.infpoints;

import java.util.List;
import com.fh.entity.Page;
import com.fh.entity.points.InfPoints;
import com.fh.util.PageData;

/** 
 * 说明： 资讯分类接口
 * 创建人：JCKJ
 * 创建时间：2018-04-04
 * @version
 */
public interface InfPointsManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**
	 * 通过ID获取其子级列表
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<InfPoints> listByParentId(String parentId) throws Exception;
	
	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<InfPoints> listTree(String parentId) throws Exception;
	
	/****
	 * 查询咨询分类表下面的咨询
	 * @param timId
	 * @return
	 * @throws Exception
	 */
	public List<InfPoints> listByTimId(String timId) throws Exception;
}

