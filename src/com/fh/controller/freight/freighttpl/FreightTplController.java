package com.fh.controller.freight.freighttpl;

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
import com.fh.entity.freight.freightVO;
import com.fh.entity.system.Dictionaries;
import com.fh.util.AppUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.service.freight.freighttpl.FreightTplManager;


/** 
 * 说明：运费模板
 * 创建人：JCKJ
 * 创建时间：2018-04-08
 */
@Controller
@RequestMapping(value="/freighttpl")
public class FreightTplController extends BaseController {
	
	String menuUrl = "freighttpl/list.do"; //菜单地址(权限用)
	@Resource(name="freighttplService")
	private FreightTplManager freighttplService;

	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增FreightTpl");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//pd.put("FREIGHTTPL_ID", this.get32UUID());	//主键
		pd.put("TFT_ADD_DATE", Tools.date2Str(new Date()));	//创建时间
		pd.put("TFT_MODIFY_DATE", Tools.date2Str(new Date()));	//修改时间
		freighttplService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除FreightTpl");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null ;} //校验权限
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "success";
		
			freighttplService.delete(pd);
		
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改FreightTpl");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		freighttplService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表FreightTpl");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = freighttplService.list(page);	//列出FreightTpl列表
		mv.setViewName("freight/freighttpl/freighttpl_list");
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
		
		List<freightVO> list = freighttplService.findProById(pd);
		
		mv.addObject("levelListPro",list);
		mv.setViewName("freight/freighttpl/freighttpl_edit");
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
		pd = freighttplService.findById(pd);	//根据ID读取
	
		List<freightVO> list = freighttplService.findProById(pd);
		mv.setViewName("freight/freighttpl/freighttpl_edit");
		mv.addObject("msg", "edit");
		mv.addObject("levelListPro",list);
		mv.addObject("pd", pd);
		return mv;
	}	
	
	@RequestMapping(value="/getCity")
	@ResponseBody
	public Object getCity(){
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			//String PID = pd.getString("PID");
		
			List<freightVO>	varList = freighttplService.findCityoById(pd); //用传过来的ID获取此ID下的子列表数据
			List<PageData> pdList = new ArrayList<PageData>();
			for(freightVO d :varList){
				PageData pdf = new PageData();
				pdf.put("CID", d.getCID());
				pdf.put("CNAME", d.getCNAME());
				pdList.add(pdf);
			}
			map.put("list", pdList);	
		} catch(Exception e){
			errInfo = "error";
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}

	
	@RequestMapping(value="/getCounty")
	@ResponseBody
	public Object findCountyById(){
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			//String PID = pd.getString("PID");
		    List<freightVO>	varList = freighttplService.findCountyById(pd); //用传过来的ID获取此ID下的子列表数据
			List<PageData> pdList = new ArrayList<PageData>();
			for(freightVO d :varList){
				PageData pdf = new PageData();
				pdf.put("OID", d.getOID());
				pdf.put("ONAME", d.getONAME());
				pdList.add(pdf);
			}
			map.put("list", pdList);	
		} catch(Exception e){
			errInfo = "error";
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	
	
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出FreightTpl到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("创建时间");	//1
		titles.add("修改时间");	//2
		titles.add("模板名称");	//3
		titles.add("发货地——省份");	//4
		titles.add("发货地——市");	//5
		titles.add("发货地——区县");	//6
		dataMap.put("titles", titles);
		List<PageData> varOList = freighttplService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("TFT_ADD_DATE"));	    //1
			vpd.put("var2", varOList.get(i).getString("TFT_MODIFY_DATE"));	    //2
			vpd.put("var3", varOList.get(i).getString("TFT_NAME"));	    //3
			vpd.put("var4", varOList.get(i).get("TFT_PROV_ID").toString());	//4
			vpd.put("var5", varOList.get(i).get("TFT_CITY_ID").toString());	//5
			vpd.put("var6", varOList.get(i).get("TFT_REGION_ID").toString());	//6
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
