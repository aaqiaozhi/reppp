package com.fh.service.withdrawal.withdrawaldetail.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import com.fh.service.withdrawal.withdrawaldetail.WithdrawalDetailManager;

/** 
 * 说明： 提现明细
 * 创建人：JCKJ
 * 创建时间：2018-04-12
 * @version
 */
@Service("withdrawaldetailService")
public class WithdrawalDetailService implements WithdrawalDetailManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("WithdrawalDetailMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("WithdrawalDetailMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("WithdrawalDetailMapper.edit", pd);
	}
	
	
	public void edit1(PageData pd)throws Exception{
		dao.update("WithdrawalDetailMapper.edit1", pd);
	}
	
	public void edit2(PageData pd)throws Exception{
		dao.update("WithdrawalDetailMapper.edit2", pd);
	}
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("WithdrawalDetailMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("WithdrawalDetailMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("WithdrawalDetailMapper.findById", pd);
	}
	
	public PageData findAmountById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("WithdrawalDetailMapper.findAmountById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("WithdrawalDetailMapper.deleteAll", ArrayDATA_IDS);
	}

	@Override
	public void edit3(PageData pd) throws Exception {
		dao.update("WithdrawalDetailMapper.edit3", pd);
	}

	@Override
	public void edit4(PageData pd) throws Exception {
		dao.update("WithdrawalDetailMapper.edit4", pd);
		
	}
	
}

