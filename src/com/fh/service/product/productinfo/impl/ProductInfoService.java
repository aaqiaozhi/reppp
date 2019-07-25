package com.fh.service.product.productinfo.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.rules.TbProductSpecificationGroup;
import com.fh.entity.rules.TbProductSpecificationGroupRelation;
import com.fh.entity.rules.TbProductSpecificationRelation;
import com.fh.entity.rules.TbProductSpecificationValue;
import com.fh.entity.rules.TbProductSpecificationValueRelation;
import com.fh.util.PageData;
import com.fh.service.product.productinfo.ProductInfoManager;

/** 
 * 说明： 商品
 * 创建人：JCKJ
 * 创建时间：2018-04-08
 * @version
 */
@Service("productinfoService")
public class ProductInfoService implements ProductInfoManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("ProductInfoMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("ProductInfoMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("ProductInfoMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ProductInfoMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ProductInfoMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ProductInfoMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ProductInfoMapper.deleteAll", ArrayDATA_IDS);
	}

	@Override
	public void goEditproduct(PageData pd) throws Exception {
		dao.update("ProductInfoMapper.goEditproduct", pd);
		
	}

	@Override
	public PageData findVideoById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ProductInfoMapper.findVideoById", pd);
	}

	@Override
	public void saveVideo(PageData pd) throws Exception {
		dao.save("ProductInfoMapper.saveVideo", pd);
	}

	@Override
	public void editVideo(PageData pd) throws Exception {
		dao.update("ProductInfoMapper.editVideo", pd);
		
	}

	@Override
	public List<PageData> listcategory(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("ProductInfoMapper.listcategory", pd);
	}

	@Override
	public PageData findProductTypeById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ProductInfoMapper.findProductTypeById", pd);
	}

	@Override
	public List<PageData> findGroupById(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("ProductInfoMapper.findGroupById", pd);
	}

	@Override
	public List<PageData> findGroupValueById(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>)dao.findForList("ProductInfoMapper.findGroupValueById", pd);
	}

	@Override
	public void savesgc(PageData pd) throws Exception {
		dao.save("ProductInfoMapper.savesgc", pd);
		
	}

	@Override
	public PageData findSPvalueById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ProductInfoMapper.findSPvalueById", pd);
	}

	@Override
	public void savePSR(PageData pd) throws Exception {
		dao.save("ProductInfoMapper.savePSR", pd);
	}

	@Override
	public void savespRelation(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.save("ProductInfoMapper.savespRelation", pd);
	}

	@Override
	public List<TbProductSpecificationGroup> findByCategoryId(String id)
			throws Exception {
		return (List<TbProductSpecificationGroup>)dao.findForList("ProductInfoMapper.findByCategoryId", id);
	}

	@Override
	public List<TbProductSpecificationValue> findByGroupId(String id)
			throws Exception {
		return (List<TbProductSpecificationValue>)dao.findForList("ProductInfoMapper.findByGroupId", id);
	}

	@Override
	public TbProductSpecificationGroupRelation findOne(PageData pd
			) throws Exception {
		
		
		return (TbProductSpecificationGroupRelation)dao.findForObject("ProductInfoMapper.findOne", pd);
	}

	@Override
	public TbProductSpecificationValueRelation findOne1(PageData pd)
			throws Exception {
		return (TbProductSpecificationValueRelation)dao.findForObject("ProductInfoMapper.findOne1", pd);
	}

	@Override
	public void creat(PageData pd) throws Exception {
		dao.save("ProductInfoMapper.creat", pd);
	}

	@Override
	public void creatValueRelation(TbProductSpecificationValueRelation pd)
			throws Exception {
		dao.save("ProductInfoMapper.creatValueRelation", pd);
		
	}

	@Override
	public void deleteGroupRelation(String id) throws Exception {
		dao.delete("ProductInfoMapper.deleteGroupRelation", id);
		
	}

	@Override
	public List<TbProductSpecificationGroupRelation> findList(
			String pd) throws Exception {
		return (List<TbProductSpecificationGroupRelation>)dao.findForList("ProductInfoMapper.findList", pd);
	}

	@Override
	public List<TbProductSpecificationValueRelation> findListValueRelation(
			String pd) throws Exception {
		return (List<TbProductSpecificationValueRelation>)dao.findForList("ProductInfoMapper.findListValueRelation", pd);
	}

	@Override
	public PageData findListPrd(String pd) throws Exception {
		return (PageData)dao.findForObject("ProductInfoMapper.findListPrd", pd);
	}

	@Override
	public void creatSpecificationRelation(TbProductSpecificationRelation pd)
			throws Exception {
		dao.save("ProductInfoMapper.creatSpecificationRelation", pd);
	}

	@Override
	public List<PageData> listsp(String id) throws Exception {
		return (List<PageData>)dao.findForList("ProductInfoMapper.listsp", id);
	}

	@Override
	public List<TbProductSpecificationRelation> listspeRelation(String pd)
			throws Exception {
		return (List<TbProductSpecificationRelation>)dao.findForList("ProductInfoMapper.listspeRelation", pd);
	}

	@Override
	public void deleteSpValueRelation(String pd) throws Exception {
		dao.delete("ProductInfoMapper.deleteSpValueRelation", pd);
	}

	@Override
	public void deleteSpGroup(String pd) throws Exception {
		dao.delete("ProductInfoMapper.deleteSpGroup", pd);
	}

	@Override
	public void deletepdSp(String pd) throws Exception {
		// TODO Auto-generated method stub
		dao.delete("ProductInfoMapper.deletepdSp", pd);
	}
	
}

