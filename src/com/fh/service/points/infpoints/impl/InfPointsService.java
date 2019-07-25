package com.fh.service.points.infpoints.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.points.InfPoints;
import com.fh.util.PageData;
import com.fh.service.points.infpoints.InfPointsManager;

/** 
 * 说明： 资讯分类
 * 创建人：JCKJ
 * 创建时间：2018-04-04
 * @version
 */
@Service("infpointsService")
public class InfPointsService implements InfPointsManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("InfPointsMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("InfPointsMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("InfPointsMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("InfPointsMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("InfPointsMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("InfPointsMapper.findById", pd);
	}

	/**
	 * 通过ID获取其子级列表
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<InfPoints> listByParentId(String parentId) throws Exception {
		return (List<InfPoints>) dao.findForList("InfPointsMapper.listByParentId", parentId);
	}
	
	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<InfPoints> listTree(String parentId) throws Exception {
		List<InfPoints> valueList = this.listByParentId(parentId);
		for(InfPoints fhentity : valueList){
			fhentity.setTreeurl("infpoints/list.do?TIC_ID="+fhentity.getTIC_ID());
			fhentity.setSubInfPoints(this.listTree(fhentity.getTIC_ID()));
			fhentity.setTarget("treeFrame");
		}
		return valueList;
	}

	@Override
	public List<InfPoints> listByTimId(String timId) throws Exception {
		// TODO Auto-generated method stub
		return (List<InfPoints>) dao.findForList("InfPointsMapper.listByTimId", timId);
	}
		
}

