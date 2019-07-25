package com.fh.service.order.mangeorder.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.freight.freightVO;
import com.fh.util.PageData;
import com.fh.service.order.mangeorder.MangeOrderManager;

/** 
 * 说明： 订单
 * 创建人：JCKJ
 * 创建时间：2018-04-11
 * @version
 */
@Service("mangeorderService")
public class MangeOrderService implements MangeOrderManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("MangeOrderMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("MangeOrderMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("MangeOrderMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MangeOrderMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MangeOrderMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MangeOrderMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("MangeOrderMapper.deleteAll", ArrayDATA_IDS);
	}

	@Override
	public void editSend(PageData pd) throws Exception {
		dao.update("MangeOrderMapper.editSend", pd);
		
	}

	
	@SuppressWarnings("unchecked")
	public List<PageData> list1(Page page) throws Exception {
		return (List<PageData>)dao.findForList("MangeOrderMapper.datalistPage1", page);
	}

	
	@SuppressWarnings("unchecked")
	public List<freightVO> findOrderById(PageData pd) throws Exception {
		return (List<freightVO>)dao.findForList("MangeOrderMapper.findOrderById", pd);
	}

	@Override
	public void editOrder(PageData pd) throws Exception {
		dao.update("MangeOrderMapper.editOrder", pd);
	}

	@SuppressWarnings("unchecked")
	public List<PageData> listOrder(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("MangeOrderMapper.listOrder", pd);
	}

	@Override
	public void editOrderStatus(PageData pd) throws Exception {
		dao.update("MangeOrderMapper.editOrderStatus", pd);
		
	}
	
}

