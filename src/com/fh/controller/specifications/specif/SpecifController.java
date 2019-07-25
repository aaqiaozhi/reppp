package com.fh.controller.specifications.specif;

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
import net.sf.json.JSONObject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.freight.freightVO;
import com.fh.entity.freight.specificationsVO;
import com.fh.util.AppUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.service.product.productinfo.ProductInfoManager;
import com.fh.service.specifications.specif.SpecifManager;
import com.mysql.fabric.xmlrpc.base.Data;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

/** 
 * 说明：商品规格
 * 创建人：JCKJ
 * 创建时间：2018-04-13
 */
@Controller
@RequestMapping(value="/specif")
public class SpecifController extends BaseController {
	
	String menuUrl = "specif/list.do"; //菜单地址(权限用)
	@Resource(name="specifService")
	private SpecifManager specifService;
	@Resource(name="productinfoService")
	private ProductInfoManager productinfoService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(@RequestParam String receiverInfo,@RequestParam String reInfo) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Specif");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		String str=	"["+receiverInfo+"]";
		
		JSONObject jsonObject=JSONObject.fromObject(reInfo);
		
		String TPSG_PRODUCT_CATEGORY_ID=jsonObject.getString("TPSG_PRODUCT_CATEGORY_ID");
		String TPSG_NAME=jsonObject.getString("TPSG_NAME");
		String TPSG_SORT=jsonObject.getString("TPSG_SORT");
		String TPSG_TYPE=jsonObject.getString("TPSG_TYPE");
		specificationsVO spe=new specificationsVO();
		
//		spe.setTPSG_ADD_DATE( Tools.date2Str(new Date()));
//		spe.setTPSG_MODIFY_DATE( Tools.date2Str(new Date()));
//		spe.setTPSG_NAME(TPSG_NAME);
//		spe.setTPSG_PRODUCT_CATEGORY_ID(TPSG_PRODUCT_CATEGORY_ID);
//		spe.setTPSG_SORT(TPSG_SORT);
//		spe.setTPSG_TYPE(TPSG_TYPE);
//		
	
		
	    pd.put("TPSG_PRODUCT_CATEGORY_ID", TPSG_PRODUCT_CATEGORY_ID);
		pd.put("TPSG_NAME", TPSG_NAME);
		pd.put("TPSG_SORT", TPSG_SORT);
		pd.put("TPSG_MODIFY_DATE", Tools.date2Str(new Date()));
		pd.put("TPSG_ADD_DATE", Tools.date2Str(new Date()));
		pd.put("TPSG_TYPE", TPSG_TYPE);
//		
		
		
		specifService.save(pd);
		int id=(int) pd.get("TPSG_ID");
		
		
	
//		pd.put("SPECIF_ID", this.get32UUID());	//主键
//		pd.put("TPSG_ADD_DATE", Tools.date2Str(new Date()));	//添加时间
//		pd.put("TPSG_MODIFY_DATE", Tools.date2Str(new Date()));	//修改时间
		
	List<freightVO> list=new ArrayList<freightVO>();
		
		
		JSONArray jsonArray=JSONArray.fromObject(str);
		freightVO stu2=new freightVO();
		for (int i = 0; i < jsonArray.size(); i++) {
			
			
			JSONObject jsonObject2=jsonArray.getJSONObject(i);
			 stu2=(freightVO)JSONObject.toBean(jsonObject2, freightVO.class);
			 list.add(stu2);
		}
        for (freightVO freightVO : list) {
        	  PageData pd1 = new PageData();
        		pd1.put("TPSV_SPECIFICATION_GROUP_ID", id);
        	 // pd1.put("TPSV_ID", freightVO.getTPSV_ID());
        	  pd1.put("TPSV_NAME", freightVO.getTPSV_NAME());
        	  pd1.put("TPSV_SORT", freightVO.getTPSV_SORT());
        	  //pd1.put("TPSV_IMAGE", freightVO.getTPSV_IMAGE());
        	  pd1.put("TPSV_MODIFY_DATE",Tools.date2Str(new Date()));
        	  pd1.put("TPSV_ADD_DATE",Tools.date2Str(new Date()));
        	  specifService.saveValue(pd1);
		}
		
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Specif");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			
			specifService.deleteValue(pd);
			specifService.delete(pd);
			out.write("success");
			out.close();
		} catch (Exception e) {
			out.write("erro");
			out.close();
		}
		
		
		
		
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@RequestParam String receiverInfo,@RequestParam String reInfo) throws Exception{
     
		
		logBefore(logger, Jurisdiction.getUsername()+"修改Specif");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		String str=	"["+receiverInfo+"]";
		
		 PageData pd = new PageData();
	     	//pd = this.getPageData();
		JSONObject jsonObject=JSONObject.fromObject(reInfo);
		String TPSG_PRODUCT_CATEGORY_ID=jsonObject.getString("TPSG_PRODUCT_CATEGORY_ID");
		String TPSG_NAME=jsonObject.getString("TPSG_NAME");
		String TPSG_SORT=jsonObject.getString("TPSG_SORT");
		String TPSG_TYPE=jsonObject.getString("TPSG_TYPE");
	    pd.put("TPSG_PRODUCT_CATEGORY_ID", TPSG_PRODUCT_CATEGORY_ID);
		pd.put("TPSG_NAME", TPSG_NAME);
		pd.put("TPSG_SORT", TPSG_SORT);
		pd.put("TPSG_MODIFY_DATE", Tools.date2Str(new Date()));
		pd.put("TPSG_ADD_DATE", Tools.date2Str(new Date()));
		pd.put("TPSG_TYPE", TPSG_TYPE);
		specifService.edit(pd);
		
		
		//String json_arr_String = JSON.toJSONString(receiverInfo);  
		//JSONObject jsonObject=JSONObject.fromObject("");
//		PageData pd = new PageData();
//		pd = this.getPageData();
//		String string=pd.getString("receiverInfo");
		List<freightVO> list=new ArrayList<freightVO>();
		
		
		JSONArray jsonArray=JSONArray.fromObject(str);
		freightVO stu2=new freightVO();
		for (int i = 0; i < jsonArray.size(); i++) {
			
			
			JSONObject jsonObject2=jsonArray.getJSONObject(i);
			 stu2=(freightVO)JSONObject.toBean(jsonObject2, freightVO.class);
			 list.add(stu2);
		}
        for (freightVO freightVO : list) {
        	  PageData pd1 = new PageData();
        	  pd1.put("TPSV_ID", freightVO.getTPSV_ID());
        	  pd1.put("TPSV_NAME", freightVO.getTPSV_NAME());
        	  pd1.put("TPSV_SORT", freightVO.getTPSV_SORT());
        	  pd1.put("TPSV_MODIFY_DATE",Tools.date2Str(new Date()));
        	  specifService.edit1(pd1);
		}
	
		ModelAndView mv = this.getModelAndView();
//	    PageData pd = new PageData();
//     	pd = this.getPageData();
		//specifService.edit(pd);
		//mv.setViewName("specifications/specif/specif_list");
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Specif");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = specifService.list(page);	//列出Specif列表
		mv.setViewName("specifications/specif/specif_list");
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
		mv.setViewName("specifications/specif/specif_add");
		 List<PageData> list2=productinfoService.listcategory(pd);
		 mv.addObject("list2", list2);
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
		pd = specifService.findById(pd);	//根据ID读取
		
	   List<PageData> list2=productinfoService.listcategory(pd);
	   List<PageData>  list=specifService.find1ById(pd);
		mv.setViewName("specifications/specif/specif_edit");
		mv.addObject("msg", "edit");
		mv.addObject("list2", list2);
		mv.addObject("list", list);
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Specif");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			specifService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Specif到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("添加时间");	//1
		titles.add("修改时间");	//2
		titles.add("名称");	//3
		titles.add("类型");	//4
		titles.add("分类名");	//5
		dataMap.put("titles", titles);
		List<PageData> varOList = specifService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("TPSG_ADD_DATE"));	    //1
			vpd.put("var2", varOList.get(i).getString("TPSG_MODIFY_DATE"));	    //2
			vpd.put("var3", varOList.get(i).getString("TPSG_NAME"));	    //3
			vpd.put("var4", varOList.get(i).get("TPSG_TYPE").toString());	//4
			vpd.put("var5", varOList.get(i).get("TPSG_PRODUCT_CATEGORY_ID").toString());	//5
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
