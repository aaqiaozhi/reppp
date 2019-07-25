package com.fh.controller.product.productinfo;

import java.io.PrintWriter;
import java.math.BigDecimal;
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
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.freight.freightVO;
import com.fh.entity.rules.TbProduct;
import com.fh.entity.rules.TbProductSpecificationGroup;
import com.fh.entity.rules.TbProductSpecificationGroupRelation;
import com.fh.entity.rules.TbProductSpecificationRelation;
import com.fh.entity.rules.TbProductSpecificationValue;
import com.fh.entity.rules.TbProductSpecificationValueRelation;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.Descartes;
import com.fh.util.FileUpload;
import com.fh.util.FileUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.PathUtil;
import com.fh.util.Tools;
import com.fh.util.Watermark;
import com.fh.service.freight.freighttpl.FreightTplManager;
import com.fh.service.product.productinfo.ProductInfoManager;

/** 
 * 说明：商品
 * 创建人：JCKJ
 * 创建时间：2018-04-08
 */
@Controller
@RequestMapping(value="/productinfo")
public class ProductInfoController extends BaseController {
	public static final Map<String, Object> json = new HashMap<String, Object>();
	String menuUrl = "productinfo/list.do"; //菜单地址(权限用)
	@Resource(name="productinfoService")
	private ProductInfoManager productinfoService;
	@Resource(name="freighttplService")
	private FreightTplManager freighttplService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增ProductInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//pd.put("PRODUCTINFO_ID", this.get32UUID());	//主键
		pd.put("TP_IS_LIST", true);	//是否列出
		pd.put("TP_IS_TOP", true);	//是否置顶
		
		pd.put("TP_ADD_DATE", Tools.date2Str(new Date()));
		pd.put("TP_MODIFY_DATE", Tools.date2Str(new Date()));
		if("1".equals(pd.getString("TP_IS_MARKETABLE"))){
			pd.put("TP_IS_MARKETABLE", true);
		}else{
			pd.put("TP_IS_MARKETABLE", false);
		}
		
		if("1".equals(pd.getString("TP_IS_GIFT"))){
			pd.put("TP_IS_GIFT", true);
		}else{
			pd.put("TP_IS_GIFT", false);
		}
		if("1".equals(pd.getString("TP_IS_DISTRIBUTION"))){
			pd.put("TP_IS_DISTRIBUTION", true);
		}else{
			pd.put("TP_IS_DISTRIBUTION", false);
		}
		if("请选择".equals(pd.getString("TP_FREIGHT_TEMPLATE_ID"))){
			pd.put("TP_FREIGHT_TEMPLATE_ID", null);
		}
		
		productinfoService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	
	@RequestMapping(value="/saveSp")
	public ModelAndView saveSp() throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String tpsgr_products_id=pd.getString("TPSR_PRODUCTS_ID");
           boolean TP0=pd.containsKey("TPSGR_NAME0");
           boolean TP1=pd.containsKey("TPSGR_NAME1");
           boolean TP2=pd.containsKey("TPSGR_NAME2");
           if(TP0==true&&TP1==false){
        		//if(!"默认不选".equals(pd.getString("TPSVR_ID0"))){
    				PageData pd4 = new PageData();
    				
    				pd4.put("TPSGR_PRODUCTS_ID", tpsgr_products_id);
    				pd4.put("TPSGR_NAME", pd.getString("TPSGR_NAME0"));
    				pd4.put("TPSGR_TYPE", "2");
    				
    				productinfoService.savesgc(pd4);
    				
    				int idsgs=(int) pd4.get("TPSVR_RELATION_ID");
    				
    				String TPSV_ID=pd.getString("TPSVR_ID0");
    				PageData pd1 = new PageData();
    				pd1.put("TPSV_ID", TPSV_ID);
    				PageData pd2=productinfoService.findSPvalueById(pd1);
    				PageData pd3 = new PageData();
    				String TPSV_NAME=pd2.getString("TPSV_NAME");
    				String TPSV_IMAGE=pd2.getString("TPSV_IMAGE");
    				String TPSVR_RELATION_ID=String.valueOf(idsgs);
    				pd3.put("TPSVR_NAME", TPSV_NAME);
    				pd3.put("TPSVR_IMAGE", TPSV_IMAGE);
    				pd3.put("TPSVR_RELATION_ID", TPSVR_RELATION_ID);
    				productinfoService.savePSR(pd3);
    				
    				PageData pd5 = new PageData();
    				String tpsr_relation_id=pd.getString("TPSVR_ID0");
    				String tpsr_relation_name=pd.getString("TPSGR_NAME0")+":"+pd2.getString("TPSV_NAME");
    				pd5.put("TPSR_RELATION_ID", tpsr_relation_id);
    				pd5.put("TPSR_RELATION_NAME", tpsr_relation_name);
    				pd5.put("TPSR_ADD_DATE", Tools.date2Str(new Date()));
    				pd5.put("TPSR_MODIFY_DATE", Tools.date2Str(new Date()));
    				pd5.put("TPSR_SORT", '1');
    				pd5.put("TPSR_PRODUCTS_ID", tpsgr_products_id);
    				pd5.put("TPSR_MARKET_PRICE", pd.getString("TPSR_MARKET_PRICE"));
    				pd5.put("TPSR_COST", pd.getString("TPSR_COST"));
    				pd5.put("TPSR_PRICE", pd.getString("TPSR_PRICE"));
    				pd5.put("TPSR_STOCK", pd.getString("TPSR_STOCK"));
    				pd5.put("TPSR_IS_ENABLE", true);
    				
    				String stus=pd.getString("TPSR_IS_DEFAULT");
    				
    				if("1".equals(stus)){
    					pd5.put("TPSR_IS_DEFAULT", true);
    				}else{
    					pd5.put("TPSR_IS_DEFAULT", false);
    				}
    				//做新增xx_product_specification_relation
    				
    				productinfoService.savespRelation(pd5);
    		//	}
           }
           
           if(TP0==true&&TP1==true){
        	 if(!"默认不选".equals(pd.getString("TPSVR_ID0"))&&"默认不选".equals(pd.getString("TPSVR_ID1"))){
				PageData pd4 = new PageData();
				
				pd4.put("TPSGR_PRODUCTS_ID", tpsgr_products_id);
				pd4.put("TPSGR_NAME", pd.getString("TPSGR_NAME0"));
				pd4.put("TPSGR_TYPE", "2");
				
				productinfoService.savesgc(pd4);
				
				int idsgs=(int) pd4.get("TPSVR_RELATION_ID");
				
				String TPSV_ID=pd.getString("TPSVR_ID0");
				PageData pd1 = new PageData();
				pd1.put("TPSV_ID", TPSV_ID);
				PageData pd2=productinfoService.findSPvalueById(pd1);
				
				
				PageData pd3 = new PageData();
				String TPSV_NAME=pd2.getString("TPSV_NAME");
				String TPSV_IMAGE=pd2.getString("TPSV_IMAGE");
				String TPSVR_RELATION_ID=String.valueOf(idsgs);
				pd3.put("TPSVR_NAME", TPSV_NAME);
				pd3.put("TPSVR_IMAGE", TPSV_IMAGE);
				pd3.put("TPSVR_RELATION_ID", TPSVR_RELATION_ID);
				productinfoService.savePSR(pd3);
				
				PageData pd5 = new PageData();
				String tpsr_relation_id=pd.getString("TPSVR_ID0");
				String tpsr_relation_name=pd.getString("TPSGR_NAME0")+":"+pd2.getString("TPSV_NAME");
				pd5.put("TPSR_RELATION_ID", tpsr_relation_id);
				pd5.put("TPSR_RELATION_NAME", tpsr_relation_name);
				pd5.put("TPSR_ADD_DATE", Tools.date2Str(new Date()));
				pd5.put("TPSR_MODIFY_DATE", Tools.date2Str(new Date()));
				pd5.put("TPSR_SORT", '1');
				pd5.put("TPSR_PRODUCTS_ID", tpsgr_products_id);
				pd5.put("TPSR_MARKET_PRICE", pd.getString("TPSR_MARKET_PRICE"));
				pd5.put("TPSR_COST", pd.getString("TPSR_COST"));
				pd5.put("TPSR_PRICE", pd.getString("TPSR_PRICE"));
				pd5.put("TPSR_STOCK", pd.getString("TPSR_STOCK"));
				pd5.put("TPSR_IS_ENABLE", true);
				String stus=pd.getString("TPSR_IS_DEFAULT");
				if("1".equals(stus)){
					pd5.put("TPSR_IS_DEFAULT", true);
				}else{
					pd5.put("TPSR_IS_DEFAULT", false);
				}
				//做新增xx_product_specification_relation
				
				productinfoService.savespRelation(pd5);
		}
         	 if(!"默认不选".equals(pd.getString("TPSVR_ID0"))&&!"默认不选".equals(pd.getString("TPSVR_ID1"))){
 				PageData pd4 = new PageData();
 				
 				pd4.put("TPSGR_PRODUCTS_ID", tpsgr_products_id);
 				pd4.put("TPSGR_NAME", pd.getString("TPSGR_NAME0"));
 				pd4.put("TPSGR_TYPE", "2");
 				
 				productinfoService.savesgc(pd4);
 				
 				int idsgs=(int) pd4.get("TPSVR_RELATION_ID");
 				
 				PageData pd6 = new PageData();
 				
 				pd6.put("TPSGR_PRODUCTS_ID", tpsgr_products_id);
 				pd6.put("TPSGR_NAME", pd.getString("TPSGR_NAME1"));
 				pd6.put("TPSGR_TYPE", "2");
 				
 				productinfoService.savesgc(pd6);
 				
 				int idsgs1=(int) pd4.get("TPSVR_RELATION_ID");
 				
 				
 				
 				
 				
 				String TPSV_ID=pd.getString("TPSVR_ID0");
 				PageData pd1 = new PageData();
 				pd1.put("TPSV_ID", TPSV_ID);
 				PageData pd2=productinfoService.findSPvalueById(pd1);
 				
 				String TPSV_ID1=pd.getString("TPSVR_ID1");
 				PageData pd7 = new PageData();
 				pd7.put("TPSV_ID", TPSV_ID1);
 				PageData pd8=productinfoService.findSPvalueById(pd7);
 				
 				
 				
 				
 				PageData pd3 = new PageData();
 				String TPSV_NAME=pd2.getString("TPSV_NAME");
 				String TPSV_IMAGE=pd2.getString("TPSV_IMAGE");
 				String TPSVR_RELATION_ID=String.valueOf(idsgs);
 				pd3.put("TPSVR_NAME", TPSV_NAME);
 				pd3.put("TPSVR_IMAGE", TPSV_IMAGE);
 				pd3.put("TPSVR_RELATION_ID", TPSVR_RELATION_ID);
 				productinfoService.savePSR(pd3);
 				
 				
 				
 				PageData pd9 = new PageData();
 				String TPSV_NAME1=pd7.getString("TPSV_NAME");
 				String TPSV_IMAGE1=pd7.getString("TPSV_IMAGE");
 				String TPSVR_RELATION_ID1=String.valueOf(idsgs1);
 				pd9.put("TPSVR_NAME", TPSV_NAME1);
 				pd9.put("TPSVR_IMAGE", TPSV_IMAGE1);
 				pd9.put("TPSVR_RELATION_ID", TPSVR_RELATION_ID1);
 				productinfoService.savePSR(pd9);
 				
 				
 				
 				
 				
 				PageData pd5 = new PageData();
 				String tpsr_relation_id=pd.getString("TPSVR_ID0");
 				String tpsr_relation_id1=pd.getString("TPSVR_ID1");
 				String tpsr_relation_name=pd.getString("TPSGR_NAME0")+":"+pd2.getString("TPSV_NAME")+","+pd.getString("TPSGR_NAME1")+":"+pd8.getString("TPSV_NAME");
 				pd5.put("TPSR_RELATION_ID", tpsr_relation_id+"_"+tpsr_relation_id1);
 				pd5.put("TPSR_RELATION_NAME", tpsr_relation_name);
 				pd5.put("TPSR_ADD_DATE", Tools.date2Str(new Date()));
 				pd5.put("TPSR_MODIFY_DATE", Tools.date2Str(new Date()));
 				pd5.put("TPSR_SORT", '1');
 				pd5.put("TPSR_PRODUCTS_ID", tpsgr_products_id);
 				pd5.put("TPSR_MARKET_PRICE", pd.getString("TPSR_MARKET_PRICE"));
 				pd5.put("TPSR_COST", pd.getString("TPSR_COST"));
 				pd5.put("TPSR_PRICE", pd.getString("TPSR_PRICE"));
 				pd5.put("TPSR_STOCK", pd.getString("TPSR_STOCK"));
 				pd5.put("TPSR_IS_ENABLE", true);
 				String stus=pd.getString("TPSR_IS_DEFAULT");
 				if("1".equals(stus)){
 					pd5.put("TPSR_IS_DEFAULT", true);
 				}else{
 					pd5.put("TPSR_IS_DEFAULT", false);
 				}
 				//做新增xx_product_specification_relation
 				productinfoService.savespRelation(pd5);
 		}
         	 
         	if("默认不选".equals(pd.getString("TPSVR_ID0"))&&!"默认不选".equals(pd.getString("TPSVR_ID1"))){
 				PageData pd4 = new PageData();
 				
 				pd4.put("TPSGR_PRODUCTS_ID", tpsgr_products_id);
 				pd4.put("TPSGR_NAME", pd.getString("TPSGR_NAME1"));
 				pd4.put("TPSGR_TYPE", "2");
 				
 				productinfoService.savesgc(pd4);
 				
 				int idsgs=(int) pd4.get("TPSVR_RELATION_ID");
 				
 				String TPSV_ID=pd.getString("TPSVR_ID1");
 				PageData pd1 = new PageData();
 				pd1.put("TPSV_ID", TPSV_ID);
 				PageData pd2=productinfoService.findSPvalueById(pd1);
 				PageData pd3 = new PageData();
 				String TPSV_NAME=pd2.getString("TPSV_NAME");
 				String TPSV_IMAGE=pd2.getString("TPSV_IMAGE");
 				String TPSVR_RELATION_ID=String.valueOf(idsgs);
 				pd3.put("TPSVR_NAME", TPSV_NAME);
 				pd3.put("TPSVR_IMAGE", TPSV_IMAGE);
 				pd3.put("TPSVR_RELATION_ID", TPSVR_RELATION_ID);
 				productinfoService.savePSR(pd3);
 				
 				PageData pd5 = new PageData();
 				String tpsr_relation_id=pd.getString("TPSVR_ID1");
 				String tpsr_relation_name=pd.getString("TPSGR_NAME1")+":"+pd2.getString("TPSV_NAME");
 				pd5.put("TPSR_RELATION_ID", tpsr_relation_id);
 				pd5.put("TPSR_RELATION_NAME", tpsr_relation_name);
 				pd5.put("TPSR_ADD_DATE", Tools.date2Str(new Date()));
 				pd5.put("TPSR_MODIFY_DATE", Tools.date2Str(new Date()));
 				pd5.put("TPSR_SORT", '1');
 				pd5.put("TPSR_PRODUCTS_ID", tpsgr_products_id);
 				pd5.put("TPSR_MARKET_PRICE", pd.getString("TPSR_MARKET_PRICE"));
 				pd5.put("TPSR_COST", pd.getString("TPSR_COST"));
 				pd5.put("TPSR_PRICE", pd.getString("TPSR_PRICE"));
 				pd5.put("TPSR_STOCK", pd.getString("TPSR_STOCK"));
 				pd5.put("TPSR_IS_ENABLE", true);
 				String stus=pd.getString("TPSR_IS_DEFAULT");
 				
 				if("1".equals(stus)){
 					pd5.put("TPSR_IS_DEFAULT", true);
 				}else{
 					pd5.put("TPSR_IS_DEFAULT", false);
 				}
 				//做新增xx_product_specification_relation
 				productinfoService.savespRelation(pd5);
 		}
         	 
         	 
         	 
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
		logBefore(logger, Jurisdiction.getUsername()+"删除ProductInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		productinfoService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改ProductInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("TP_MODIFY_DATE", Tools.date2Str(new Date()));
		if("1".equals(pd.getString("TP_IS_MARKETABLE"))){
			pd.put("TP_IS_MARKETABLE", true);
		}else{
			pd.put("TP_IS_MARKETABLE", false);
		}
		
		if("1".equals(pd.getString("TP_IS_GIFT"))){
			pd.put("TP_IS_GIFT", true);
		}else{
			pd.put("TP_IS_GIFT", false);
		}
		if("1".equals(pd.getString("TP_IS_DISTRIBUTION"))){
			pd.put("TP_IS_DISTRIBUTION", true);
		}else{
			pd.put("TP_IS_DISTRIBUTION", false);
		}
		
		if("请选择".equals(pd.getString("TP_FREIGHT_TEMPLATE_ID"))){
			pd.put("TP_FREIGHT_TEMPLATE_ID", null);
		}
		productinfoService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表ProductInfo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = productinfoService.list(page);	//列出ProductInfo列表
		mv.setViewName("product/productinfo/productinfo_list");
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
		mv.setViewName("product/productinfo/productinfo_edit");
		
		 List<freightVO>	list=freighttplService.findTabById(pd);
		
	    mv.addObject("levelListTab", list);
	    List<PageData> list2=productinfoService.listcategory(pd);
	    mv.addObject("listcategory", list2);
		
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
		
	    List<freightVO>	list=freighttplService.findTabById(pd);
		pd = productinfoService.findById(pd);	//根据ID读取
		
		List<PageData> list2=productinfoService.listcategory(pd);
		
		mv.setViewName("product/productinfo/productinfo_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		mv.addObject("levelListTab", list);
		
		mv.addObject("listcategory", list2);
		return mv;
	}	
	
	
	 /**新增规格
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goSpecifications")
	public ModelAndView goSpecifications()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData pd1 =productinfoService.findProductTypeById(pd);
		//TP_PRODUCT_CATEGORY1_ID
		String idString=pd.getString("TP_ID");
		
		List<PageData> list=productinfoService.findGroupById(pd1);
		
		int i=0;
		for (PageData pageData : list) {
			List<PageData>  list2 =productinfoService.findGroupValueById(pageData);
			mv.addObject("levelListValue"+i, list2);
			mv.addObject("group"+i, pageData.getString("TPSG_NAME"));
			i=i+1;
		}
		
//		
//	    List<freightVO>	list=freighttplService.findTabById(pd);
//		pd = productinfoService.findById(pd);	//根据ID读取
//		
//		List<PageData> list2=productinfoService.listcategory(pd);
//		
	mv.setViewName("product/productinfo/specifications_add");
//	mv.addObject("msg", "edit");
//		mv.addObject("pd", pd);
		mv.addObject("list", list);
//		
		mv.addObject("idString", idString);
		return mv;
	}
	
	
	
	 /**测试页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/gotest")
	public ModelAndView gotest()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
	
		
		mv.setViewName("product/productinfo/dit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		
		return mv;
	}	
	
	
	
	
	
	 /**去产品介绍页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/editproduct")
	public ModelAndView goEditproduct()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = productinfoService.findById(pd);	//根据ID读取
		mv.setViewName("product/productinfo/introduce");
		
		mv.addObject("pd", pd);
	
		return mv;
	}	
	
	
	 /**去视频
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/video")
	public ModelAndView goVideo()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = productinfoService.findById(pd);	//根据ID读取
		mv.setViewName("product/productinfo/video");
		
		mv.addObject("pd", pd);
	
		return mv;
	}	
	
	
	/***
	 * 对视频操作
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveVideo")
	public ModelAndView saveVideo() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Fhfile");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData pd1 = new PageData();
		String id=pd.getString("TP_ID");
		String url=pd.getString("TPI_LARGE");
		pd=productinfoService.findVideoById(pd);
		if(pd==null){
			pd1.put("TPI_PRODUCT_ID", id);
			pd1.put("TPI_LARGE", url);
			pd1.put("TPI_TYPE", '1');
			productinfoService.saveVideo(pd1);
		}else{
			pd1.put("TPI_PRODUCT_ID", id);
			pd1.put("TPI_LARGE", url);
			productinfoService.editVideo(pd1);
		}
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 *  去上传图片页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goPicture")
	public ModelAndView goPicture()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
			//根据ID读取
		mv.setViewName("product/productinfo/picture_add");
		
		mv.addObject("pd", pd);
	
		return mv;
	}	
	
	
	/**上传图片
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/savePt")
	@ResponseBody
	public Object savePictures(@RequestParam(required=false) MultipartFile file,@RequestParam  String TP_ID) throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		
		logBefore(logger, Jurisdiction.getUsername()+"新增图片");
		Map<String,String> map = new HashMap<String,String>();
		String  ffile = DateUtil.getDays(), fileName = "";
		PageData pd = new PageData();
		if(Jurisdiction.buttonJurisdiction(menuUrl, "add")){
			if (null != file && !file.isEmpty()) {
				//String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//文件上传路径
				String filePath=Const.PICTURE_PATH+Const.FILEPATH_IMG + ffile;
				
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//执行上传
			}else{
				logBefore(logger, Jurisdiction.getUsername()+"上传失败");
				System.out.println("上传失败");
			}
		    
			//pd.put("PICTURES_ID", this.get32UUID());			//主键
			//pd.put("TITLE", "图片");								//标题
			//pd.put("NAME", fileName);							//文件名
							//路径
			//pd.put("CREATETIME", Tools.date2Str(new Date()));	//创建时间
		//	pd.put("MASTER_ID", "1");							//附属与
		//	pd.put("BZ", "图片管理处上传");						//备注
			Watermark.setWatemark(Const.PICTURE_PATH + Const.FILEPATH_IMG + ffile + "/" + fileName);//加水印
			String str=Const.FILEPATH_IMG+ffile + "/" + fileName;
             pd.put("TPI_PRODUCT_ID", TP_ID);
		   
		     pd.put("TPI_THUMBNAIL", str);
             pd.put("TPI_MEDIUM", str);
             pd.put("TPI_LARGE", str);
             pd.put("TPI_SOURCE", str);
			
			pd.put("TPI_TYPE", '0');
			productinfoService.saveVideo(pd);
			
			
		
		}
		map.put("result", "ok");
		return AppUtil.returnObject(pd, map);
	}
	
	
	
	
	
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEditproduct")
	@ResponseBody
	public Object editProduct() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改InfPoints");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,String> map = new HashMap<String,String>();
		productinfoService.goEditproduct(pd);
		String errInfo = "success";
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
		
	}
	
	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除ProductInfo");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			productinfoService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出ProductInfo到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("商品编号");	//1
		titles.add("添加时间");	//2
		titles.add("修改时间");	//3
		titles.add("市场价");	//4
		titles.add("成本价");	//5
		titles.add("销售价");	//6
		titles.add("赠送积分");	//7
		titles.add("名称");	//8
		titles.add("副名称");	//9
		titles.add("商品图");	//10
		titles.add("介绍");	//11
		titles.add("是否上架");	//12
		titles.add("是否为兑换品");	//13
		titles.add("是否为分销商品");	//14
		titles.add("销量");	//15
		titles.add("库存");	//16
		titles.add("单位");	//17
		titles.add("重量");	//18
		titles.add("运费模板");	//19
		dataMap.put("titles", titles);
		List<PageData> varOList = productinfoService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("TP_SN"));	    //1
			vpd.put("var2", varOList.get(i).getString("TP_ADD_DATE"));	    //2
			vpd.put("var3", varOList.get(i).getString("TP_MODIFY_DATE"));	    //3
			vpd.put("var4", varOList.get(i).getString("TP_MARKET_PRICE"));	    //4
			vpd.put("var5", varOList.get(i).getString("TP_COST"));	    //5
			vpd.put("var6", varOList.get(i).getString("TP_PRICE"));	    //6
			vpd.put("var7", varOList.get(i).getString("TP_POINT"));	    //7
			vpd.put("var8", varOList.get(i).getString("TP_NAME"));	    //8
			vpd.put("var9", varOList.get(i).getString("TP_VICE_NAME"));	    //9
			vpd.put("var10", varOList.get(i).getString("TP_IMAGE"));	    //10
			vpd.put("var11", varOList.get(i).getString("TP_DESP"));	    //11
			vpd.put("var12", varOList.get(i).getString("TP_IS_MARKETABLE"));	    //12
			vpd.put("var13", varOList.get(i).getString("TP_IS_GIFT"));	    //13
			vpd.put("var14", varOList.get(i).getString("TP_IS_DISTRIBUTION"));	    //14
			vpd.put("var15", varOList.get(i).get("TP_SALES").toString());	//15
			vpd.put("var16", varOList.get(i).get("TP_STOCK").toString());	//16
			vpd.put("var17", varOList.get(i).getString("TP_UNIT"));	    //17
			vpd.put("var18", varOList.get(i).getString("TP_WEIGHT"));	    //18
			vpd.put("var19", varOList.get(i).get("TP_FREIGHT_TEMPLATE_ID").toString());	//19
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	
	@RequestMapping(value="/goIntroduce")
	public ModelAndView goIntroduce()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
													//放入视图容器
	
	    mv.setViewName("product/productinfo/introduce");
		
		return mv;
	}	
	
	
	
	@RequestMapping(value="/saveIntroduce")
	@ResponseBody
	public Object savePictures(@RequestParam(required=false) MultipartFile file) throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		
		logBefore(logger, Jurisdiction.getUsername()+"新增图片");
		Map<String,String> map = new HashMap<String,String>();
		String  ffile = DateUtil.getDays(), fileName = "";
		PageData pd = new PageData();
		if(Jurisdiction.buttonJurisdiction(menuUrl, "add")){
			if (null != file && !file.isEmpty()) {
				//String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//文件上传路径
				String filePath=Const.PICTURE_PATH+Const.FILEPATH_IMG + ffile;
				
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//执行上传
			}else{
				logBefore(logger, Jurisdiction.getUsername()+"上传失败");
				System.out.println("上传失败");
			}
		   
			
			
			//pd.put("PICTURES_ID", this.get32UUID());			//主键
			//pd.put("TITLE", "图片");								//标题
			//pd.put("NAME", fileName);							//文件名
							//路径
			//pd.put("CREATETIME", Tools.date2Str(new Date()));	//创建时间
		//	pd.put("MASTER_ID", "1");							//附属与
		//	pd.put("BZ", "图片管理处上传");						//备注
			Watermark.setWatemark(Const.PICTURE_PATH + Const.FILEPATH_IMG + ffile + "/" + fileName);//加水印
			String str=Const.FILEPATH_IMG+ffile + "/" + fileName;
		
			map.put("str", str);
		}
		map.put("result", "ok");
		
		return AppUtil.returnObject(pd, map);
	}
	
	
	
	
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	
	
	
	
	
	
	
	
	
	  @ResponseBody
	    @RequestMapping(value="/findProductSpecificationByCategoryId")
	    public Map<String, Object> findProductSpecificationByCategoryId(@RequestParam String id,@RequestParam String pid){
	    	//String tpId = request.getParameter("tpId");//商品ID
	    	Map<String, Object> json = new HashMap<String, Object>();
	    	List<TbProductSpecificationGroup> groups = null;//规格组列表
	    	List<TbProductSpecificationValue> values = null;//规格值列表
	    	try {
	    		groups = productinfoService.findByCategoryId(id);
	    		if(groups!=null&&groups.size()>0){
	    			for (int i = 0; i < groups.size(); i++) {
	    				if(!pid.isEmpty()){
	    					values = productinfoService.findByGroupId(String.valueOf(groups.get(i).getTpsgId()));
							//TbProduct product = tbProductService.findById(Long.valueOf(tpId));
							if(null!=pid){
																
									//商品编辑页面判断商品规格是否选中
									TbProductSpecificationGroupRelation groupRelation = new TbProductSpecificationGroupRelation();
									//groupRelation.setTpsgrProductsId(Long.valueOf(pid));
									//groupRelation.setTpsgrName(groups.get(i).getTpsgName());
									PageData pdData=new PageData();
									pdData.put("NAME", groups.get(i).getTpsgName());
									pdData.put("ID", pid);
									groupRelation = productinfoService.findOne(pdData);
									if(null!=groupRelation){
										if(values!=null&&values.size()>0){
											for (int j = 0; j < values.size(); j++) {
												TbProductSpecificationValueRelation valueRelation = new TbProductSpecificationValueRelation();
												valueRelation.setTpsvrRelationId(groupRelation.getTpsgrId());
												valueRelation.setTpsvrName(values.get(j).getTpsvName());
												PageData pdData1=new PageData();
												
												pdData1.put("NAME",values.get(j).getTpsvName());
												pdData1.put("ID", groupRelation.getTpsgrId());
												valueRelation = productinfoService.findOne1(pdData1);
												if(null!=valueRelation){
													values.get(j).setIfSelect(true);
												}
											}
										}
									}
								
							}
							groups.get(i).setValueList(values);
	    				}else{
	    					//values = tbProductSpecificationValueService.findByGroupId(groups.get(i).getTpsgId());
	    					//groups.get(i).setValueList(values);
	    				}    				
	    			}
	    			
	    			json.put("status", "1000");
	    			json.put("message", "查询成功");
	    			json.put("result", groups);
	    			return json;
	    		}else{				
	    			json.put("status", "1004");
	    			json.put("message", "数据为空");
	    			return json;
	    		}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		logger.error("[TbProductController-findProductSpecificationByCategoryId()]：错误原因:" + e.getMessage());
	    		json.put("status", "1003");
	    		json.put("message", "服务器繁忙，请与管理人员联系");
	    		return json;
	    	}
	    }
	  
	  
	  
	  @RequestMapping(value="/goSpecifications1")
		public ModelAndView goSpecifications1()throws Exception{
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			String pdid=pd.getString("pid");
		List<PageData>	list=productinfoService.listsp(pdid);
		if(list.size()>0){
			  mv.setViewName("product/productinfo/specifications_edit");
				mv.addObject("pd", pd);
				return mv;
		}else{
			  mv.setViewName("product/productinfo/specifications_add");
				mv.addObject("pd", pd);
				return mv;
		}
		  
		}
	  
		@ResponseBody
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public Object add(TbProduct entity) {
			try{
				//商品规格
				TbProductSpecificationRelation[] specificationRelations = entity.getSpecificationRelations();
				//添加商品规格组
				TbProductSpecificationGroupRelation[] specificationGroupRelations = entity.getSpecificationGroupRelations();
				if(specificationGroupRelations!=null&&specificationGroupRelations.length>0){
					for (int i = 0; i < specificationGroupRelations.length; i++) {
						if(!specificationGroupRelations[i].getTpsgrName().isEmpty()){						
							//添加商品规格值
							TbProductSpecificationValueRelation[] specificationValueRelations = specificationGroupRelations[i].getSpecificationValueRelations();
							if(specificationValueRelations!=null&&specificationValueRelations.length>0){
								//规格组名称不为空，并且至少有一个规格值时保存数据
								specificationGroupRelations[i].setTpsgrProductsId(Long.valueOf(entity.getProductId()));
								PageData pd = new PageData();
								pd.put("TPSGR_PRODUCTS_ID", entity.getProductId());
								pd.put("TPSGR_NAME", specificationGroupRelations[i].getTpsgrName());
								pd.put("TPSGR_TYPE", "1");
								productinfoService.creat(pd);
								int a=(int) pd.get("TPSVR_RELATION_ID");
								Boolean isSase = true;//规格组名称是否保存
								for (int j = 0; j < specificationValueRelations.length; j++) {
									//如果选中则添加
									if(specificationValueRelations[j].getIfSelect()){
										isSase = false;
										specificationValueRelations[j].setTpsvrRelationId(new Long((long)a));
										productinfoService.creatValueRelation(specificationValueRelations[j]);
									}
								}
								if(isSase){		
									
									//规格值没有选中内容，规格组不添加，删除
									productinfoService.deleteGroupRelation(String.valueOf(a));
								}
							}
						}
					}
				}
				
				//添加商品规格
				if(specificationRelations!=null&&specificationRelations.length>0){
					/**
					 * 保存规则值匹配ID
					 */
					List<List<String>> dimValue = new ArrayList<List<String>>();
					List<String> tempList = new ArrayList<String>();
					
					//TbProductSpecificationGroupRelation groupRelation = new TbProductSpecificationGroupRelation();
					//groupRelation.setTpsgrProductsId(product.getTpId());
					List<TbProductSpecificationGroupRelation> groupRelations = productinfoService.findList(entity.getProductId());
					if(groupRelations!=null&&groupRelations.size()>0){
						for (int i = 0; i < groupRelations.size(); i++) {
							TbProductSpecificationValueRelation valueRelation = new TbProductSpecificationValueRelation();
							valueRelation.setTpsvrRelationId(groupRelations.get(i).getTpsgrId());
							List<TbProductSpecificationValueRelation> valueRelations = productinfoService.findListValueRelation(String.valueOf(groupRelations.get(i).getTpsgrId()));
							if(valueRelations!=null&&valueRelations.size()>0){
								tempList = new ArrayList<String>();
								for (int j = 0; j < valueRelations.size(); j++) {
									tempList.add(valueRelations.get(j).getTpsvrId().toString());
								}
								dimValue.add(tempList);
							}					
						}
					}
					// 保存笛卡尔积结果  
					List<List<String>> recursiveResult = new ArrayList<List<String>>();  
					// 递归实现笛卡尔积  
					Descartes.recursive(dimValue, recursiveResult, 0, new ArrayList<String>());  
					
					System.out.println("1、递归实现笛卡尔乘积: 共 " + recursiveResult.size() + " 个结果");  
					System.out.println("1、规格乘积: 共 " + specificationRelations.length + " 个结果");  
				
					if(recursiveResult.size()!=specificationRelations.length){
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						json.put("status", "1006");
						json.put("message", "商品规则错误");
						return json;
					}
					for (int i = 0; i < recursiveResult.size(); i++) {
						String str = "";
						for (int j = 0; j < recursiveResult.get(i).size(); j++) {
							str += recursiveResult.get(i).get(j) + "_";
						}
						if(!str.isEmpty()){					
							specificationRelations[i].setTpsrRelationId(str.substring(0, str.length()-1));
						}
					}
					//查询产品
					PageData pData=productinfoService.findListPrd(entity.getProductId());
					
				
					//保存商品规则
					for (int i = 0; i < specificationRelations.length; i++) {
						if(specificationRelations[i].getTpsrPrice()!=null&&specificationRelations[i].getTpsrStock()!=null){						
							if(i==0){
								specificationRelations[i].setTpsrIsDefault(true);
							}else{
								specificationRelations[i].setTpsrIsDefault(false);
							}
							if(specificationRelations[i].getTpsrIsEnable()==null){
								specificationRelations[i].setTpsrIsEnable(false);
							}
							if(specificationRelations[i].getTpsrMarketPrice()==null||specificationRelations[i].getTpsrMarketPrice().equals("0.00")){
								specificationRelations[i].setTpsrMarketPrice((BigDecimal) pData.get("TP_MARKET_PRICE"));
							}
							if(specificationRelations[i].getTpsrCost()==null||specificationRelations[i].getTpsrCost().equals("0.00")){
								specificationRelations[i].setTpsrCost((BigDecimal) pData.get("TP_COST"));
							}
							if(specificationRelations[i].getTpsrPoint()==null||specificationRelations[i].getTpsrPoint().equals("0.00")){
								specificationRelations[i].setTpsrPoint((BigDecimal) pData.get("TP_PRICE"));
							}
							specificationRelations[i].setTpsrAddDate(new Date());
							specificationRelations[i].setTpsrModifyDate(new Date());
							specificationRelations[i].setTpsrSort(i);
							specificationRelations[i].setTpsrProductsId(Long.valueOf(entity.getProductId()));
							productinfoService.creatSpecificationRelation(specificationRelations[i]);
						}
					}
				}
				
				json.put("status", "1000");
	        	json.put("message", "添加成功");
				return json;
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				//log.error("[TbProductController-add()]：错误原因:" + e.getMessage());
				json.put("status", "1003");
	        	json.put("message", "本次操作出现不明错误，请联系技术人员解决!");
				return json;
			}
		}
	  
  
		
		  @ResponseBody
		  @RequestMapping(value="/findProductSpecificationByProductId")
		    public Map<String, Object> findProductSpecificationByProductId(@RequestParam String  id){
		    	Map<String, Object> json = new HashMap<String, Object>();
		    	try {
		    		json.put("status", "1000");
		    		json.put("message", "查询成功");
		    		json.put("result", productinfoService.listspeRelation(id));
		    		return json;
		    	} catch (Exception e) {
		    		e.printStackTrace();
		    		logger.error("[TbProductController-findProductSpecificationByProductId()]：错误原因:" + e.getMessage());
		    		json.put("status", "1003");
		    		json.put("message", "服务器繁忙，请与管理人员联系");
		    		return json;
		    	}
		    }
		
			@ResponseBody
			@RequestMapping(value = "/editSpecification", method = RequestMethod.POST)
			public Object editSpecification(TbProduct entity) {
				try {
					String proID=entity.getProductId();
					//商品规格
					TbProductSpecificationRelation[] specificationRelations = entity.getSpecificationRelations();
					
					//先删除商品规格
					//TbProductSpecificationGroupRelation specificationGroupRelation = new TbProductSpecificationGroupRelation();
					//specificationGroupRelation.setTpsgrProductsId(entity.getTpId());
					List<TbProductSpecificationGroupRelation> specificationGroupRelationsList = productinfoService.findList(proID);
					if(specificationGroupRelationsList!=null&&specificationGroupRelationsList.size()>0){
						for (int i = 0; i < specificationGroupRelationsList.size(); i++) {
							//删除商品规格值
							//TbProductSpecificationValueRelation specificationValueRelation = new TbProductSpecificationValueRelation();
							//specificationValueRelation.setTpsvrRelationId(specificationGroupRelationsList.get(i).getTpsgrId());
							List<TbProductSpecificationValueRelation> specificationValueRelations = productinfoService.findListValueRelation(String.valueOf(specificationGroupRelationsList.get(i).getTpsgrId()));
							if(specificationValueRelations!=null&&specificationValueRelations.size()>0){
								for (int j = 0; j < specificationValueRelations.size(); j++) {
									productinfoService.deleteSpValueRelation(String.valueOf(specificationValueRelations.get(j).getTpsvrId()));
										
								}
							}
							//删除商品规格组
							productinfoService.deleteSpGroup(String.valueOf(specificationGroupRelationsList.get(i).getTpsgrId()));
						}
					}
					
					//添加商品规格组
					TbProductSpecificationGroupRelation[] specificationGroupRelations = entity.getSpecificationGroupRelations();
					if(specificationGroupRelations!=null&&specificationGroupRelations.length>0){
						for (int i = 0; i < specificationGroupRelations.length; i++) {
							if(!specificationGroupRelations[i].getTpsgrName().isEmpty()){						
								//添加商品规格值
								TbProductSpecificationValueRelation[] specificationValueRelations = specificationGroupRelations[i].getSpecificationValueRelations();
								if(specificationValueRelations!=null&&specificationValueRelations.length>0){
									//规格组名称不为空，并且至少有一个规格值时保存数据
								//	specificationGroupRelations[i].setTpsgrProductsId(entity.getTpId());
									
									
									PageData pd = new PageData();
									pd.put("TPSGR_PRODUCTS_ID", proID);
									pd.put("TPSGR_NAME", specificationGroupRelations[i].getTpsgrName());
									pd.put("TPSGR_TYPE", "1");
									productinfoService.creat(pd);
									int a=(int) pd.get("TPSVR_RELATION_ID");
									
									//TbProductSpecificationGroupRelation groupRelation = tbProductSpecificationGroupRelationService.creat(specificationGroupRelations[i]);
									Boolean isSase = true;//规格组名称是否保存
									for (int j = 0; j < specificationValueRelations.length; j++) {
										//如果选中则添加
										if(specificationValueRelations[j].getIfSelect()){
											isSase = false;
											//specificationValueRelations[j].setTpsvrRelationId(groupRelation.getTpsgrId());
											//tbProductSpecificationValueRelationService.save(specificationValueRelations[j]);
											specificationValueRelations[j].setTpsvrRelationId(new Long((long)a));
											productinfoService.creatValueRelation(specificationValueRelations[j]);
										}
									}
									if(isSase){								
										//规格值没有选中内容，规格组不添加，删除
									//	tbProductSpecificationGroupRelationService.delete(groupRelation.getTpsgrId());
										productinfoService.deleteGroupRelation(String.valueOf(a));
									}
								}
							}
						}
					}
					
					//删除商品-规格
					TbProductSpecificationRelation specificationRelation = new TbProductSpecificationRelation();
					specificationRelation.setTpsrProductsId(entity.getTpId());
				//	List<TbProductSpecificationRelation> specificationRelationsList = tbProductSpecificationRelationService.findList(specificationRelation);
					
					List<PageData>	listpg=productinfoService.listsp(proID);
					
					if(listpg!=null&&listpg.size()>0){
						for (int i = 0; i < listpg.size(); i++) {
							//删除购物车选中商品规格
							//tbProductSpecificationRelationService.updateCartSpecification(specificationRelationsList.get(i).getTpsrId());
							//删除商品规格
							productinfoService.deletepdSp(String.valueOf(listpg.get(i).get("TPSR_ID")));
						}
					}
					
					//添加商品规格
					if(specificationRelations!=null&&specificationRelations.length>0){
						/**
						 * 保存规则值匹配ID
						 */
						List<List<String>> dimValue = new ArrayList<List<String>>();
						List<String> tempList = new ArrayList<String>();
						
						//TbProductSpecificationGroupRelation groupRelation = new TbProductSpecificationGroupRelation();
					//	groupRelation.setTpsgrProductsId(Long.valueOf(proID));
					//	List<TbProductSpecificationGroupRelation> groupRelations = tbProductSpecificationGroupRelationService.findList(groupRelation);
						List<TbProductSpecificationGroupRelation> groupRelations = productinfoService.findList(proID);
						
						if(groupRelations!=null&&groupRelations.size()>0){
							for (int i = 0; i < groupRelations.size(); i++) {
								TbProductSpecificationValueRelation valueRelation = new TbProductSpecificationValueRelation();
								valueRelation.setTpsvrRelationId(groupRelations.get(i).getTpsgrId());
								//List<TbProductSpecificationValueRelation> valueRelations = tbProductSpecificationValueRelationService.findList(valueRelation);
								List<TbProductSpecificationValueRelation> valueRelations = productinfoService.findListValueRelation(String.valueOf(groupRelations.get(i).getTpsgrId()));
								
								if(valueRelations!=null&&valueRelations.size()>0){
									tempList = new ArrayList<String>();
									for (int j = 0; j < valueRelations.size(); j++) {
										tempList.add(valueRelations.get(j).getTpsvrId().toString());
									}
									dimValue.add(tempList);
								}					
							}
						}
						
						// 保存笛卡尔积结果  
						List<List<String>> recursiveResult = new ArrayList<List<String>>();  
						// 递归实现笛卡尔积  
						Descartes.recursive(dimValue, recursiveResult, 0, new ArrayList<String>()); 
						
						System.out.println("2、商品规则共有: 共 " + specificationRelations.length + " 个结果");  
						System.out.println("2、递归实现笛卡尔乘积: 共 " + recursiveResult.size() + " 个结果");  
						
						if(recursiveResult.size()!=specificationRelations.length){
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
							json.put("status", "1006");
							json.put("message", "商品规则错误");
							return json;
						}
						for (int i = 0; i < recursiveResult.size(); i++) {
							String str = "";
							for (int j = 0; j < recursiveResult.get(i).size(); j++) {
								str += recursiveResult.get(i).get(j) + "_";
							}
							if(!str.isEmpty()){					
								specificationRelations[i].setTpsrRelationId(str.substring(0, str.length()-1));
							}
						}
						
						//查询产品
						PageData pData=productinfoService.findListPrd(proID);
						//保存商品规则
						for (int i = 0; i < specificationRelations.length; i++) {
							if(specificationRelations[i].getTpsrPrice()!=null&&specificationRelations[i].getTpsrStock()!=null){						
								if(i==0){
									specificationRelations[i].setTpsrIsDefault(true);
								}else{
									specificationRelations[i].setTpsrIsDefault(false);
								}
								if(specificationRelations[i].getTpsrIsEnable()==null){
									specificationRelations[i].setTpsrIsEnable(false);
								}
								if(specificationRelations[i].getTpsrMarketPrice()==null||specificationRelations[i].getTpsrMarketPrice().equals("0.00")){
									specificationRelations[i].setTpsrMarketPrice((BigDecimal) pData.get("TP_MARKET_PRICE"));
								}
								if(specificationRelations[i].getTpsrCost()==null||specificationRelations[i].getTpsrCost().equals("0.00")){
									specificationRelations[i].setTpsrCost((BigDecimal) pData.get("TP_COST"));
								}
								if(specificationRelations[i].getTpsrPoint()==null||specificationRelations[i].getTpsrPoint().equals("0.00")){
									specificationRelations[i].setTpsrPoint((BigDecimal) pData.get("TP_PRICE"));
								}
								specificationRelations[i].setTpsrAddDate(new Date());
								specificationRelations[i].setTpsrModifyDate(new Date());
								specificationRelations[i].setTpsrSort(i);
								
								specificationRelations[i].setTpsrProductsId(Long.valueOf(proID));
								productinfoService.creatSpecificationRelation(specificationRelations[i]);
							}
						}
					}

				    json.put("status", "1000");
		        	json.put("message", "编辑成功");
					return json;
				} catch (Exception e) {
					e.printStackTrace();
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				//	log.error("[TbProductController-edit()]：错误原因:" + e.getMessage());
					json.put("status", "1003");
		        	json.put("message", "本次操作出现不明错误，请联系技术人员解决!");
					return json;
				}
				
				
			}
	  
	  
}
