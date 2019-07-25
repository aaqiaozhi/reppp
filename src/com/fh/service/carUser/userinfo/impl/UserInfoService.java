package com.fh.service.carUser.userinfo.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.system.Menu;
import com.fh.util.PageData;
import com.fh.service.carUser.userinfo.UserInfoManager;

/** 
 * 说明： 车联会员信息
 * 创建人：JCKJ
 * 创建时间：2018-04-02
 * @version
 */
@Service("userinfoService")
public class UserInfoService implements UserInfoManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("UserInfoMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("UserInfoMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("UserInfoMapper.edit", pd);
	}
	
	/**修改信息
	 * @param pd
	 * @throws Exception
	 */
	public void editInfo(PageData pd)throws Exception{
		dao.update("UserInfoMapper.editInfo", pd);
	}
	
	/**改变状态
	 * @param pd
	 * @throws Exception
	 */
	public void status(PageData pd)throws Exception{
		dao.update("UserInfoMapper.status", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("UserInfoMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("UserInfoMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserInfoMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("UserInfoMapper.deleteAll", ArrayDATA_IDS);
	}

	/****
	 * 通过ID获取其子ID
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> listCarUserByParentId(String parentId) throws Exception {
		return (List<Menu>) dao.findForList("UserInfoMapper.listCarUserByParentId", parentId);
		
		
	}

	/****
	 * 通过递归获取其子ID
	 */
	@Override
	public List<Menu> listAllCarUser(String MENU_ID) throws Exception {
		List<Menu> menuList = this.listCarUserByParentId(MENU_ID);
		for(Menu menu : menuList){
			menu.setMENU_URL("");
			menu.setSubMenu(this.listAllCarUser(menu.getMENU_ID()));
			menu.setTarget("treeFrame");
		}
		return menuList;
	
	}

	
	/**
	 * 通过菜单ID获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageData getCarUserById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("UserInfoMapper.getCarUserById", pd);
	}

	/***
	 * 子级查询
	 */
	@Override
	public List<PageData> list1(Page page) throws Exception {
		return (List<PageData>)dao.findForList("UserInfoMapper.datalistPage1", page);
		
	}

	@Override
	public int getCount(String id) throws Exception {
		
		List<PageData> list=(List<PageData>)dao.findForList("UserInfoMapper.getcountById", id);
		return list.size();
	}

	@Override
	public void savere(PageData pd) throws Exception {
	
			dao.save("UserInfoMapper.savere", pd);
		
		
	}
	

	
}

