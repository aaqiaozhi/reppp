package com.fh.controller.points.infpoints;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import javax.annotation.Resource;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.points.InfPoints;
import com.fh.util.AppUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.service.points.infpoints.InfPointsManager;

/** 
 * 说明：资讯分类
 * 创建人：JCKJ
 * 创建时间：2018-04-04
 */
@Controller
@RequestMapping(value="/infpoints")
public class InfPointsController extends BaseController {
	
	String menuUrl = "infpoints/list.do"; //菜单地址(权限用)
	@Resource(name="infpointsService")
	private InfPointsManager infpointsService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增InfPoints");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//pd.put("INFPOINTS_ID", this.get32UUID());	//主键
		pd.put("TIC_ADD_DATE", Tools.date2Str(new Date()));	//创建时间
		
		pd.put("TIC_ORDER","1");
		pd.put("TIC_STATUS","1");
		infpointsService.save(pd);
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
	public Object delete(@RequestParam String TIC_ID) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除InfPoints");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} 					//校验权限
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd.put("TIC_ID", TIC_ID);
		String errInfo = "success";
		if(infpointsService.listByParentId(TIC_ID).size() > 0||infpointsService.listByTimId(TIC_ID).size()>0){		//判断是否有子级，是：不允许删除
			errInfo = "false";
		}else{
			infpointsService.delete(pd);	//执行删除
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改InfPoints");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("TIC_ADD_DATE", Tools.date2Str(new Date()));
		infpointsService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表InfPoints");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} 	//校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");								//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String TIC_ID = null == pd.get("TIC_ID")?"":pd.get("TIC_ID").toString();
		if(null != pd.get("id") && !"".equals(pd.get("id").toString())){
			TIC_ID = pd.get("id").toString();
		}
		pd.put("TIC_ID", TIC_ID);					//上级ID
		page.setPd(pd);
		List<PageData>	varList = infpointsService.list(page);			//列出InfPoints列表
		mv.addObject("pd", infpointsService.findById(pd));				//传入上级所有信息
		mv.addObject("TIC_ID", TIC_ID);			//上级ID
		
		mv.setViewName("points/infpoints/infpoints_list");
		
		mv.addObject("varList", varList);
		mv.addObject("QX",Jurisdiction.getHC());								//按钮权限
		return mv;
	}

	/**
	 * 显示列表ztree
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listTree")
	public ModelAndView listTree(Model model,String TIC_ID)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
		List<InfPoints>	list=infpointsService.listTree("0");
			JSONArray arr = JSONArray.fromObject(list);
			String json = arr.toString();
			json = json.replaceAll("FTIC_ID", "id").replaceAll("TIC_PARENT_ID", "pId").replaceAll("FTIC_NAME", "name").replaceAll("subInfPoints", "nodes").replaceAll("hasInfPoints", "checked").replaceAll("treeurl", "url");
			model.addAttribute("zTreeNodes", json);
			mv.addObject("TIC_ID",TIC_ID);
			mv.addObject("pd", pd);	
			mv.setViewName("points/infpoints/infpoints_tree");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
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
		String TIC_ID = null == pd.get("TIC_ID")?"":pd.get("TIC_ID").toString();
		pd.put("TIC_ID", TIC_ID);					//上级ID
		mv.addObject("pds",infpointsService.findById(pd));				//传入上级所有信息
		mv.addObject("TIC_ID", TIC_ID);			//传入ID，作为子级ID用
		mv.setViewName("points/infpoints/infpoints_edit");
		mv.addObject("msg", "save");
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
		String TIC_ID = pd.getString("TIC_ID");
		pd = infpointsService.findById(pd);							//根据ID读取		
		mv.addObject("pd", pd);													//放入视图容器
		pd.put("TIC_ID",pd.get("TIC_PARENT_ID").toString());			//用作上级信息
		mv.addObject("pds",infpointsService.findById(pd));				//传入上级所有信息
		mv.addObject("TIC_ID", pd.get("TIC_PARENT_ID").toString());	//传入上级ID，作为子ID用
		pd.put("TIC_ID",TIC_ID);					//复原本ID
		pd = infpointsService.findById(pd);							//根据ID读取
		mv.setViewName("points/infpoints/infpoints_edit");
		mv.addObject("msg", "edit");
		return mv;
	}	
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出InfPoints到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("分类名称");	//1
		titles.add("创建时间");	//2
		titles.add("状态");	//3
		dataMap.put("titles", titles);
		List<PageData> varOList = infpointsService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("TIC_NAME"));	    //1
			vpd.put("var2", varOList.get(i).getString("TIC_ADD_DATE"));	    //2
			vpd.put("var3", varOList.get(i).get("TIC_STATUS").toString());	//3
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
