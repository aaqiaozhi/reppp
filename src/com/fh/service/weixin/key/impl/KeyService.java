package com.fh.service.weixin.key.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import com.fh.util.weixin.Weixin;
import com.fh.service.weixin.key.KeyManager;

/** 
 * 说明： 公众平台Key信息
 * 创建人：JCKJ
 * 创建时间：2016-10-30
 * @version
 */
@Service("keyService")
public class KeyService implements KeyManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("KeyMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("KeyMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("KeyMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("KeyMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("KeyMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("KeyMapper.findById", pd);
	}
	
	/**通过公众号获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByWxId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("KeyMapper.findByWxId", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("KeyMapper.deleteAll", ArrayDATA_IDS);
	}

	/****
	 * 修改定时器
	 */
	@Override
	public void editSetTime(PageData pd) throws Exception {
		dao.update("KeyMapper.editSetTime", pd);
		
	}

	@Override
	public void editStatus(PageData pd) throws Exception {
		dao.update("KeyMapper.editStatus", pd);
		
	}

	@Override
	public void editToken(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.update("KeyMapper.editToken", pd);
	}
	/***
	 * 得到微信token
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public String getAccess_token(String appid, String appsecret){
		Weixin wx=new Weixin();
		return wx.getAccess_token(appid,appsecret);
	}
	/****
	 * 得到微信jstoken
	 * @param getAccess_token
	 * @return
	 */
	 	public String getJsapi_ticket(String getAccess_token){
		Weixin wx=new Weixin();
		return wx.jsapi_ticket(getAccess_token);
	}
}

