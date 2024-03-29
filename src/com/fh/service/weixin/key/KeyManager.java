package com.fh.service.weixin.key;

import java.util.List;
import com.fh.entity.Page;
import com.fh.util.PageData;

/** 
 * 说明： 公众平台Key信息接口
 * 创建人：JCKJ
 * 创建时间：2016-10-30
 * @version
 */
public interface KeyManager{

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
	
	/****
	 * 修改定时器
	 * @param pd
	 * @throws Exception
	 */
	
	public void editSetTime(PageData pd)throws Exception;
	
	/****
	 * 启动或者关闭定时器
	 * @param pd
	 * @throws Exception
	 */
	
	public void editStatus(PageData pd)throws Exception;
	
	/****
	 * 修改token
	 * @param pd
	 * @throws Exception
	 */
	
	public void editToken(PageData pd)throws Exception;
	
	
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
	
	/**通过公众号获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByWxId(PageData pd)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	
}

