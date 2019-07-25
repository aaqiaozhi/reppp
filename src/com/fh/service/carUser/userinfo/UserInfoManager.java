package com.fh.service.carUser.userinfo;

import java.util.List;
import com.fh.entity.Page;
import com.fh.entity.system.Menu;
import com.fh.util.PageData;

/** 
 * 说明： 车联会员信息接口
 * 创建人：JCKJ
 * 创建时间：2018-04-02
 * @version
 */
public interface UserInfoManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	
	/**新增
	 * tb_member_recommend
	 * @param pd
	 * @throws Exception
	 */
	public void savere(PageData pd)throws Exception;
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
	
	/**修改信息
	 * @param pd
	 * @throws Exception
	 */
	public void editInfo(PageData pd)throws Exception;
	
	/**改变状态
	 * @param pd
	 * @throws Exception
	 */
	public void status(PageData pd)throws Exception;
	
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
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	
	/**
	 * 根据id查询下面的子节点
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<Menu> listCarUserByParentId(String parentId)throws Exception;
	
	/***
	 * 
	 * @param MENU_ID
	 * @return
	 * @throws Exception
	 */
	public List<Menu> listAllCarUser(String MENU_ID) throws Exception;
	
	/**
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getCarUserById(PageData pd) throws Exception;
	
	public List<PageData> list1(Page page)throws Exception;
	
	/**
	 * 查询条数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int  getCount(String id)throws Exception;
	
}

