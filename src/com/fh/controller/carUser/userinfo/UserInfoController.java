package com.fh.controller.carUser.userinfo;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Dictionaries;
import com.fh.util.AppUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.service.agent.agentlevel.AgentLevelManager;
import com.fh.service.carUser.userinfo.UserInfoManager;
//import com.fh.service.carUser.userinfomx.UserInfoMxManager;
import com.fh.service.system.dictionaries.DictionariesManager;

/** 
 * 说明：车联会员信息
 * 创建人：JCKJ
 * 创建时间：2018-04-02
 */
@Controller
@RequestMapping(value="/userinfo")
public class UserInfoController extends BaseController {
	
	String menuUrl = "userinfo/list.do"; //菜单地址(权限用)
	@Resource(name="userinfoService")
	private UserInfoManager userinfoService;
	
	
	
	@Resource(name="agentlevelService")
	private AgentLevelManager agentlevelService;
	
	
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
//	@Resource(name="userinfomxService")
//	private UserInfoMxManager userinfomxService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增UserInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("USERINFO_ID", this.get32UUID());	//主键
		pd.put("TM_NAME", "");	//姓名
		pd.put("TM_TYPE", "0");	//会员类型
		pd.put("TM_LEVEL_ID", "0");	//会员等级
		pd.put("TM_NICKNAME", "");	//呢称
		pd.put("TM_USERNAME", "");	//登录用户名
		pd.put("TM_ADDRESS", "");	//地址
		pd.put("TM_MOBILE", "");	//电话号码
		pd.put("TM_ADD_DATE", Tools.date2Str(new Date()));	//创建日期
		pd.put("TM_SCORE", "0");	//积分
		pd.put("TM_STATUS", "0");	//状态
		pd.put("TM_YAOQING", "0");	//所属会员
		pd.put("TM_YONGJIN", "0");	//总佣金
		pd.put("TM_IS_YONGJIN", "0");	//已提现佣金
		pd.put("TM_SIGN_CONTINUITY_COUNT", "0");	//连续签到天数
		pd.put("TM_SIGN_LAST_DATE", Tools.date2Str(new Date()));	//最后签到日期
		pd.put("TM_SIGN_ALL_COUNT", "0");	//总共签到天数
		pd.put("TM_IS_SET", "0");	//手动设置会员等级
		userinfoService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除UserInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null ;} //校验权限
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "success";
//		if(Integer.parseInt(userinfomxService.findCount(pd).get("zs").toString()) > 0){
//			errInfo = "false";
//		}else{
//			
//		}
		userinfoService.delete(pd);
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改UserInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("TM_IS_SET", '1');
		userinfoService.edit(pd);
		
		PageData pd1= new PageData();
		pd1.put("TML_ID", pd.getString("TML_LEVEL"));
		PageData Pad=agentlevelService.findById(pd1);
		Pad.put("TMP_MEMBER_ID", pd.getString("TM_ID"));
		userinfoService.savere(Pad);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**修改信息
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/editInfo")
	public ModelAndView editInfo() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改UserInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		userinfoService.editInfo(pd);
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**改变状态
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/status")
	@ResponseBody
	public Object status() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"改变状态UserInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null ;} //校验权限
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		userinfoService.status(pd);
		String errInfo = "success";
		map.put("result", errInfo);
		
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表UserInfo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String dateStart = pd.getString("dateStart");
		if(null != dateStart && !"".equals(dateStart)){
			pd.put("dateStart", dateStart.trim());
		}
		String dateEnd = pd.getString("dateEnd");
		if(null != dateEnd && !"".equals(dateEnd)){
			pd.put("dateEnd", dateEnd.trim());
		}
		
		page.setPd(pd);
		List<PageData>	varList = userinfoService.list(page);	//列出UserInfo列表
		
		List<PageData> levelList = agentlevelService.listAll(pd);//列出所有会员等级
		
		mv.setViewName("carUser/userinfo/userinfo_list");
		mv.addObject("varList", varList);
		mv.addObject("levelList", levelList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("carUser/userinfo/userinfo_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = userinfoService.findById(pd);	//根据ID读取
		
//		PageData pData=dictionariesService.listSubDictById("代理等级");
//		String id=pData.getString("DICTIONARIES_ID");
//		List<Dictionaries> list=dictionariesService.listSubDictByParentId(id);
		PageData pd1= new PageData();
		//pd1.put("DJ", "代理等级");
		List<PageData> list=agentlevelService.listAllname(pd1);
		
		
		
		//mv.addObject("levelList", list);
		
		mv.setViewName("carUser/userinfo/userinfo_edit");
		mv.addObject("msg", "edit");
		mv.addObject("list_lv", list);
		mv.addObject("pd", pd);
		return mv;
	}	

	 /**去修改信息页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEditInfo")
	public ModelAndView goEditInfo()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = userinfoService.findById(pd);	//根据ID读取
		
		mv.setViewName("carUser/userinfo/userinfo_editInfo");
		mv.addObject("msg", "editInfo");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 显示菜单列表ztree(菜单管理)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listcarMenu")
	public ModelAndView listAllMenu(Model model,String TM_ID)throws Exception{
		ModelAndView mv = this.getModelAndView();
		try{
			JSONArray arr = JSONArray.fromObject(userinfoService.listAllCarUser(TM_ID));
			String json = arr.toString();
			json = json.replaceAll("TM_ID", "id").replaceAll("TM_YAOQING", "pId").replaceAll("TM_NAME", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked").replaceAll("MENU_URL", "url");
			model.addAttribute("zTreeNodes", json);
			mv.addObject("MENU_ID",TM_ID);
			mv.setViewName("carUser/userinfo/caruser_ztree");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 请求编辑菜单页面
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(String id)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			pd.put("TM_ID",id);				//接收过来的要修改的ID
			pd = userinfoService.getCarUserById(pd);	//读取此ID的菜单数据
			mv.addObject("pd", pd);				//放入视图容器
			pd.put("TM_ID",pd.get("TM_YAOQING").toString());			//用作读取父菜单信息
			mv.addObject("pds", userinfoService.getCarUserById(pd));			//传入父菜单所有信息
			mv.addObject("TM_ID", pd.get("TM_YAOQING").toString());	//传入父菜单ID，作为子菜单的父菜单ID用
			mv.addObject("MSG", "edit");
			pd.put("TM_ID",id);			//复原本菜单ID
			mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
			mv.setViewName("system/menu/menu_edit");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出UserInfo到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("姓名");	//1
		titles.add("会员类型");	//2
		titles.add("会员等级");	//3
		titles.add("呢称");	//4
		titles.add("登录用户名");	//5
		titles.add("地址");	//6
		titles.add("电话号码");	//7
		titles.add("创建日期");	//8
		titles.add("积分");	//9
		titles.add("状态");	//10
		titles.add("所属会员");	//11
		titles.add("总佣金");	//12
		titles.add("已提现佣金");	//13
		titles.add("连续签到天数");	//14
		titles.add("最后签到日期");	//15
		titles.add("总共签到天数");	//16
		titles.add("手动设置会员等级");	//17
		dataMap.put("titles", titles);
		List<PageData> varOList = userinfoService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("TM_NAME"));	    //1
			vpd.put("var2", varOList.get(i).get("TM_TYPE").toString());	//2
			vpd.put("var3", varOList.get(i).get("TM_LEVEL_ID").toString());	//3
			vpd.put("var4", varOList.get(i).getString("TM_NICKNAME"));	    //4
			vpd.put("var5", varOList.get(i).getString("TM_USERNAME"));	    //5
			vpd.put("var6", varOList.get(i).getString("TM_ADDRESS"));	    //6
			vpd.put("var7", varOList.get(i).getString("TM_MOBILE"));	    //7
			vpd.put("var8", varOList.get(i).getString("TM_ADD_DATE"));	    //8
			vpd.put("var9", varOList.get(i).get("TM_SCORE").toString());	//9
			vpd.put("var10", varOList.get(i).get("TM_STATUS").toString());	//10
			vpd.put("var11", varOList.get(i).get("TM_YAOQING").toString());	//11
			vpd.put("var12", varOList.get(i).get("TM_YONGJIN").toString());	//12
			vpd.put("var13", varOList.get(i).get("TM_IS_YONGJIN").toString());	//13
			vpd.put("var14", varOList.get(i).get("TM_SIGN_CONTINUITY_COUNT").toString());	//14
			vpd.put("var15", varOList.get(i).getString("TM_SIGN_LAST_DATE"));	    //15
			vpd.put("var16", varOList.get(i).get("TM_SIGN_ALL_COUNT").toString());	//16
			vpd.put("var17", varOList.get(i).get("TM_IS_SET").toString());	//17
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	
    //子级查询
	@RequestMapping(value="/list1")
	public ModelAndView list1(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表UserInfo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		
		page.setPd(pd);
		List<PageData>	varList = userinfoService.list1(page);	//列出UserInfo列表
		for (PageData pageData : varList) {
			//JSONArray arr = JSONArray.fromObject()
			long id=(long)pageData.get("TM_ID");
			int i=userinfoService.getCount(String.valueOf(id));
			pageData.put("TOTAL", i);
		}
		mv.setViewName("carUser/userinfo/caruser_list");
		mv.addObject("varList", varList);
		//mv.addObject("varList", varList);
		mv.addObject("TB_TM_ID", pd.getString("TM_ID"));
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
}
