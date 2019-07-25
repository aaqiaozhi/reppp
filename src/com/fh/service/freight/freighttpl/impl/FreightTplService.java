package com.fh.service.freight.freighttpl.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.freight.freightVO;
import com.fh.entity.system.Dictionaries;
import com.fh.util.PageData;
import com.fh.service.freight.freighttpl.FreightTplManager;

/** 
 * 说明： 运费模板
 * 创建人：JCKJ
 * 创建时间：2018-04-08
 * @version
 */
@Service("freighttplService")
public class FreightTplService implements FreightTplManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("FreightTplMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("FreightTplMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("FreightTplMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("FreightTplMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("FreightTplMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("FreightTplMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("FreightTplMapper.deleteAll", ArrayDATA_IDS);
	}


	


	@SuppressWarnings("unchecked")
	public List<freightVO> findProById(PageData pd) throws Exception {
		return (List<freightVO>)dao.findForList("FreightTplMapper.findProById", pd);
	}

	
	@SuppressWarnings("unchecked")
	public List<freightVO> findCityoById(PageData pd) throws Exception {
		return (List<freightVO>)dao.findForList("FreightTplMapper.findCityoById", pd);
	}

	@Override
	public List<freightVO> findCountyById(PageData pd) throws Exception {
		return (List<freightVO>)dao.findForList("FreightTplMapper.findCountyById", pd);
	}

	@Override
	public List<freightVO> findTabById(PageData pd) throws Exception {
		return (List<freightVO>)dao.findForList("FreightTplMapper.findTabById", pd);
	}


	
}

