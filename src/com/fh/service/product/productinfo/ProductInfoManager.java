package com.fh.service.product.productinfo;

import java.util.List;
import com.fh.entity.Page;
import com.fh.entity.rules.TbProductSpecificationGroup;
import com.fh.entity.rules.TbProductSpecificationGroupRelation;
import com.fh.entity.rules.TbProductSpecificationRelation;
import com.fh.entity.rules.TbProductSpecificationValue;
import com.fh.entity.rules.TbProductSpecificationValueRelation;
import com.fh.util.PageData;

/** 
 * 说明： 商品接口
 * 创建人：JCKJ
 * 创建时间：2018-04-08
 * @version
 */
public interface ProductInfoManager{

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
	
	
	public void goEditproduct(PageData pd)throws Exception;
	
	
	
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
	
	
	
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listcategory(PageData pd)throws Exception;
	
	
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	
	
	/***
	 * 判断是否存在视频
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findVideoById(PageData pd)throws Exception;
	
	/**
	 * 增加视频
	 * @param pd
	 * @throws Exception
	 */
	public void saveVideo(PageData pd)throws Exception;
	
	
	/**修改视频
	 * @param pd
	 * @throws Exception
	 */
	public void editVideo(PageData pd)throws Exception;
	
	
	public PageData findProductTypeById(PageData pd)throws Exception;
	
	
	public List<PageData> findGroupById(PageData pd)throws Exception;
	
	public List<PageData> findGroupValueById(PageData pd)throws Exception;
	
	public void savesgc(PageData pd)throws Exception;

	
	public PageData findSPvalueById(PageData pd)throws Exception;
	
	
	public void savePSR(PageData pd)throws Exception;
	
	public void savespRelation(PageData pd)throws Exception;
	
	
	public List<TbProductSpecificationGroup> findByCategoryId(String  id)throws Exception;
	
	public List<TbProductSpecificationValue> findByGroupId(String  id)throws Exception;
	
	public TbProductSpecificationGroupRelation findOne(PageData pd)throws Exception;
	
	public TbProductSpecificationValueRelation findOne1(PageData pd)throws Exception;
	
	public void creat(PageData pd)throws Exception;
	
	
	public void creatValueRelation(TbProductSpecificationValueRelation pd)throws Exception;
	
	public void deleteGroupRelation(String id)throws Exception;
	
	public List<TbProductSpecificationGroupRelation> findList(String pd)throws Exception;
	
	public List<TbProductSpecificationValueRelation> findListValueRelation(String pd)throws Exception;
	
	public PageData findListPrd(String pd)throws Exception;
	
	public void creatSpecificationRelation(TbProductSpecificationRelation pd)throws Exception;
	
	public List<PageData> listsp(String  id)throws Exception;
	
	
	public List<TbProductSpecificationRelation> listspeRelation(String pd)throws Exception;
	
	public void deleteSpValueRelation(String pd)throws Exception;
	
	public void deleteSpGroup(String pd)throws Exception;
	
	public void deletepdSp(String pd)throws Exception;
	

	
}

