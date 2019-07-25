package com.fh.controller.order.mangeorder;

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
import com.fh.entity.freight.freightVO;
import com.fh.util.AppUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.service.freight.freighttpl.FreightTplManager;
import com.fh.service.order.mangeorder.MangeOrderManager;

/** 
 * 说明：订单
 * 创建人：JCKJ
 * 创建时间：2018-04-11
 */
@Controller
@RequestMapping(value="/mangeorder")
public class MangeOrderController extends BaseController {
	
	String menuUrl = "mangeorder/list.do"; //菜单地址(权限用)
	@Resource(name="mangeorderService")
	private MangeOrderManager mangeorderService;
	
	@Resource(name="freighttplService")
	private FreightTplManager freighttplService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增MangeOrder");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("MANGEORDER_ID", this.get32UUID());	//主键
		pd.put("TO_SN", "");	//订单编号
		pd.put("TO_ADD_DATE", Tools.date2Str(new Date()));	//下单时间
		pd.put("TO_PAY_DATE", Tools.date2Str(new Date()));	//支付时间
		pd.put("TO_SHIPPING_DATE", Tools.date2Str(new Date()));	//配送时间
		pd.put("TO_RECEIPT_DATE", Tools.date2Str(new Date()));	//收货时间
		pd.put("TO_MOBILE", "");	//电话号码
		pd.put("TO_AMOUNT", "");	//订单总金额
		pd.put("TO_PAID_AMOUNT", "");	//已付金额
		pd.put("TO_FEE", "");	//支付手续费
		pd.put("TO_CONSIGNEE", "");	//收货人
		mangeorderService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**发货
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/send")
	public void send(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"发货MangeOrder");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		String id=pd.getString("TO_ID");
		
		List<freightVO> list = mangeorderService.findOrderById(pd);
		for (freightVO freightVO : list) {
			PageData pd1 = new PageData();
			pd1.put("TOI_ID", freightVO.getTOI_ID());
			pd1.put("TOI_SHIPPED_QUANTITY", freightVO.getTOI_QUANTITY());
			mangeorderService.editOrder(pd1);
		}
		pd.put("TO_SHIPPING_STATUS", '3');
		mangeorderService.editSend(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改MangeOrder");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String TO_AMOUNT=pd.getString("TO_AMOUNT");
		String TO_OFFSET_AMOUNT=pd.getString("TO_OFFSET_AMOUNT");
		double a=Double.parseDouble(TO_AMOUNT);
		double b=Double.parseDouble(TO_OFFSET_AMOUNT);
		double c=a-b;
		pd.put("TO_AMOUNT", c);
		pd.put("TO_ORDER_STATUS", "2");
		
		mangeorderService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表MangeOrder");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = mangeorderService.list(page);	//列出MangeOrder列表
		mv.setViewName("order/mangeorder/mangeorder_list");
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
		mv.setViewName("order/mangeorder/mangeorder_edit");
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
		pd = mangeorderService.findById(pd);	//根据ID读取
		
		List<freightVO> list = freighttplService.findProById(pd);
		
		mv.addObject("levelListPro",list);
		
		mv.setViewName("order/mangeorder/mangeorder_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	
	
	
	@RequestMapping(value="/list1")
	public ModelAndView list1(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表UserInfo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String string=pd.getString("TOI_ORDER_ID");
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = mangeorderService.list1(page);	//列出UserInfo列表
		
		mv.setViewName("order/mangeorder/orderDear");
		mv.addObject("varList", varList);
		mv.addObject("ord", string);
	
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除MangeOrder");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			mangeorderService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出MangeOrder到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("订单编号");	//1
		titles.add("下单时间");	//2
		titles.add("支付时间");	//3
		titles.add("配送时间");	//4
		titles.add("收货时间");	//5
		titles.add("收货详细地址");	//6
		titles.add("电话号码");	//7
		titles.add("订单总金额");	//8
		titles.add("已付金额");	//9
		titles.add("优惠金额");	//10
		titles.add("支付手续费");	//11
		titles.add("运费");	//12
		titles.add("附言");	//13
		titles.add("调整金额");	//14
		titles.add("订单状态");	//15
		titles.add("支付状态");	//16
		titles.add("配送状态");	//17
		titles.add("会员ID");	//18
		titles.add("操作人员");	//19
		titles.add("收货人");	//20
		dataMap.put("titles", titles);
		List<PageData> varOList = mangeorderService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("TO_SN"));	    //1
			vpd.put("var2", varOList.get(i).getString("TO_ADD_DATE"));	    //2
			vpd.put("var3", varOList.get(i).getString("TO_PAY_DATE"));	    //3
			vpd.put("var4", varOList.get(i).getString("TO_SHIPPING_DATE"));	    //4
			vpd.put("var5", varOList.get(i).getString("TO_RECEIPT_DATE"));	    //5
			vpd.put("var6", varOList.get(i).getString("TO_ADDRESS"));	    //6
			vpd.put("var7", varOList.get(i).getString("TO_MOBILE"));	    //7
			vpd.put("var8", varOList.get(i).getString("TO_AMOUNT"));	    //8
			vpd.put("var9", varOList.get(i).getString("TO_PAID_AMOUNT"));	    //9
			vpd.put("var10", varOList.get(i).getString("TO_DISCOUNT_AMOUNT"));	    //10
			vpd.put("var11", varOList.get(i).getString("TO_FEE"));	    //11
			vpd.put("var12", varOList.get(i).getString("TO_FREIGHT"));	    //12
			vpd.put("var13", varOList.get(i).getString("TO_MEMO"));	    //13
			vpd.put("var14", varOList.get(i).getString("TO_OFFSET_AMOUNT"));	    //14
			vpd.put("var15", varOList.get(i).get("TO_ORDER_STATUS").toString());	//15
			vpd.put("var16", varOList.get(i).get("TO_PAYMENT_STATUS").toString());	//16
			vpd.put("var17", varOList.get(i).get("TO_SHIPPING_STATUS").toString());	//17
			vpd.put("var18", varOList.get(i).get("TO_MEMBER_ID").toString());	//18
			vpd.put("var19", varOList.get(i).getString("TO_OPERATOR"));	    //19
			vpd.put("var20", varOList.get(i).getString("TO_CONSIGNEE"));	    //20
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
