package com.fh.service.specifications.specif.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.freight.specificationsVO;
import com.fh.util.PageData;
import com.fh.service.specifications.specif.SpecifManager;

/** 
 * 说明： 商品规格
 * 创建人：JCKJ
 * 创建时间：2018-04-13
 * @version
 */
@Service("specifService")
public class SpecifService implements SpecifManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		
		dao.save("SpecifMapper.savespeci", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("SpecifMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("SpecifMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("SpecifMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("SpecifMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("SpecifMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("SpecifMapper.deleteAll", ArrayDATA_IDS);
	}


	
	@SuppressWarnings("unchecked")
	public List<PageData> find1ById(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("SpecifMapper.find1ById", pd);
	}

	@Override
	public void edit1(PageData pd) throws Exception {
		dao.update("SpecifMapper.edit1", pd);
		
	}

	@Override
	public void saveValue(PageData pd) throws Exception {
		dao.save("SpecifMapper.saveValue", pd);
		
	}

	@Override
	public void deleteValue(PageData pd) throws Exception {
		dao.delete("SpecifMapper.deleteValue", pd);
	}
	
}

