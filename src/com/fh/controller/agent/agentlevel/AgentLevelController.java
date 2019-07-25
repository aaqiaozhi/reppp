package com.fh.controller.agent.agentlevel;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
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
//import com.fh.service.agent.agentlevelmx.AgentLevelMxManager;
import com.fh.service.system.dictionaries.DictionariesManager;

/** 
 * 说明：代理等级
 * 创建人：JCKJ
 * 创建时间：2018-03-29
 */
@Controller
@RequestMapping(value="/agentlevel")
public class AgentLevelController extends BaseController {
	
	String menuUrl = "agentlevel/list.do"; //菜单地址(权限用)
	@Resource(name="agentlevelService")
	private AgentLevelManager agentlevelService;
	//数据字典
	@Resource(name="dictionariesService")
	private DictionariesManager dictionariesService;
	
	//AGENTLEVEL_ID
	//listSubDictByParentId
	
//	@Resource(name="agentlevelmxService")
//	private AgentLevelMxManager agentlevelmxService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增AgentLevel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//pd.put("AGENTLEVEL_ID", this.get32UUID());	//主键
		pd.put("TML_ADD_DATE", Tools.date2Str(new Date()));	//添加时间
		pd.put("TML_EDIT_DATE", Tools.date2Str(new Date()));	//修改时间
		pd.put("TML_OPT_NAME", Jurisdiction.getUsername());	//操作人员
		agentlevelService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除AgentLevel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null ;} //校验权限
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "success";
//		if(Integer.parseInt(agentlevelmxService.findCount(pd).get("zs").toString()) > 0){
//			errInfo = "false";
//		}else{
//			agentlevelService.delete(pd);
//		}
		String id =(String)pd.get("TML_ID");
		agentlevelService.delete(pd);
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改AgentLevel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("TML_EDIT_DATE", Tools.date2Str(new Date()));	//修改时间
		agentlevelService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表AgentLevel");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = agentlevelService.list(page);	//列出AgentLevel列表
		mv.setViewName("agent/agentlevel/agentlevel_list");
		mv.addObject("varList", varList);
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
		mv.setViewName("agent/agentlevel/agentlevel_edit");
		//PageData pData=dictionariesService.listSubDictById("代理等级");
		//String id=pData.getString("DICTIONARIES_ID");
		//List<Dictionaries> list=dictionariesService.listSubDictByParentId(id);
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		//mv.addObject("levelList", list);
		//mv.addObject("stateList", stateList);
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
		pd = agentlevelService.findById(pd);	//根据ID读取
		mv.setViewName("agent/agentlevel/agentlevel_edit");
		
		//PageData pData=dictionariesService.listSubDictById("代理等级");
		//String id=pData.getString("DICTIONARIES_ID");
		//List<Dictionaries> list=dictionariesService.listSubDictByParentId(id);
		
		//List<Dictionaries> list=dictionariesService.listSubDictByParentId("731addfe4f784452bdcf4ef425404ab8");
		//mv.addObject("levelList", list);
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出AgentLevel到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("等级");	//1
		titles.add("总台数");	//2
		titles.add("直推台数");	//3
		titles.add("小片台数");	//4
		titles.add("极差奖比例");	//5
		titles.add("极差奖");	//6
		titles.add("添加时间");	//7
		titles.add("修改时间");	//8
		titles.add("状态");	//9
		titles.add("操作人员");	//10
		dataMap.put("titles", titles);
		List<PageData> varOList = agentlevelService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("TML_LEVEL"));	    //1
			vpd.put("var2", varOList.get(i).get("TML_TOTAL_NUMBER").toString());	//2
			vpd.put("var3", varOList.get(i).get("TML_DIRECT_NUMBER").toString());	//3
			vpd.put("var4", varOList.get(i).get("TML_SMALL_NUMBER").toString());	//4
			vpd.put("var5", varOList.get(i).getString("TML_RANGE_PRIZE_SCALE"));	    //5
			vpd.put("var6", varOList.get(i).getString("TML_RANGE_PRIZE"));	    //6
			vpd.put("var7", varOList.get(i).getString("TML_ADD_DATE"));	    //7
			vpd.put("var8", varOList.get(i).getString("TML_EDIT_DATE"));	    //8
			vpd.put("var9", varOList.get(i).get("TML_STATUS").toString());	//9
			vpd.put("var10", varOList.get(i).getString("TML_OPT_NAME"));	    //10
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
}
