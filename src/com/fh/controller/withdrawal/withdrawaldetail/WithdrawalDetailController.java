package com.fh.controller.withdrawal.withdrawaldetail;

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
import com.fh.util.AppUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.Tools;
import com.fh.service.withdrawal.withdrawaldetail.WithdrawalDetailManager;

/** 
 * 说明：提现明细
 * 创建人：JCKJ
 * 创建时间：2018-04-12
 */
@Controller
@RequestMapping(value="/withdrawaldetail")
public class WithdrawalDetailController extends BaseController {
	
	String menuUrl = "withdrawaldetail/list.do"; //菜单地址(权限用)
	@Resource(name="withdrawaldetailService")
	private WithdrawalDetailManager withdrawaldetailService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增WithdrawalDetail");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("WITHDRAWALDETAIL_ID", this.get32UUID());	//主键
		pd.put("TMWC_MEMBER_ID", "0");	//会员名称
		pd.put("TMWC_AMOUNT", "0");	//提现金额
		pd.put("TMWC_ADD_DATE", Tools.date2Str(new Date()));	//申请时间
		pd.put("TMWC_DEAL_DATE", Tools.date2Str(new Date()));	//审核时间
		pd.put("TMWC_UPDATE_DATE", Tools.date2Str(new Date()));	//打款时间
		pd.put("TMWC_ACCOUNT_ID", "0");	//提现账号名
		pd.put("TMWC_OPT_ID", "0");	//操作员
		pd.put("TMWC_SERVICE_CHARGE", "0");	//手续费
		withdrawaldetailService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除WithdrawalDetail");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		withdrawaldetailService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改WithdrawalDetail");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData pd1 = new PageData();
		pd1 = withdrawaldetailService.findById(pd);
		//提现金额
		double a=(double) pd1.get("TMWC_AMOUNT");
		//double a=1;
		String status=pd.getString("TMWC_STATUS");
		if("2".equals(status)){
			pd.put("TMWC_DEAL_DATE", Tools.date2Str(new Date()));
			pd.put("TMWC_OPT_ID", Jurisdiction.getUsername());
			withdrawaldetailService.edit1(pd);
		}
		if("3".equals(status)){
			pd.put("TMWC_OPT_ID", Jurisdiction.getUsername());
			
			pd.put("TMWC_UPDATE_DATE", Tools.date2Str(new Date()));
			withdrawaldetailService.edit2(pd);
		}
		if("4".equals(status)){
			pd.put("TMWC_REJECT_DATE", Tools.date2Str(new Date()));
			pd.put("TMWC_OPT_ID", Jurisdiction.getUsername());
			withdrawaldetailService.edit4(pd);
			PageData pd2 = new PageData();
			String userID=pd.getString("TMWC_MEMBER_ID");
			pd2=withdrawaldetailService.findAmountById(pd);
			//账户总剩余金额
			double b=(double) pd2.get("TM_YONGJIN");
			b=b+a;
			//已提现总金额
			double c=(double) pd2.get("TM_IS_YONGJIN");
			c=c-a;
			
			PageData pd3 = new PageData();
			pd3.put("TMWC_MEMBER_ID", userID);
			pd3.put("TM_YONGJIN", b);
			pd3.put("TM_IS_YONGJIN", c);
			
			
			withdrawaldetailService.edit3(pd3);
			
		}
		
		
		
		
		//withdrawaldetailService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表WithdrawalDetail");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = withdrawaldetailService.list(page);	//列出WithdrawalDetail列表
		mv.setViewName("withdrawal/withdrawaldetail/withdrawaldetail_list");
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
		mv.setViewName("withdrawal/withdrawaldetail/withdrawaldetail_edit");
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
		pd = withdrawaldetailService.findById(pd);	//根据ID读取
		mv.setViewName("withdrawal/withdrawaldetail/withdrawaldetail_edit");
		mv.addObject("msg", "edit");
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除WithdrawalDetail");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			withdrawaldetailService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出WithdrawalDetail到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("会员名称");	//1
		titles.add("提现金额");	//2
		titles.add("申请时间");	//3
		titles.add("审核时间");	//4
		titles.add("状态");	//5
		titles.add("备注");	//6
		titles.add("打款时间");	//7
		titles.add("提现账号名");	//8
		titles.add("操作员");	//9
		titles.add("手续费");	//10
		dataMap.put("titles", titles);
		List<PageData> varOList = withdrawaldetailService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).get("TMWC_MEMBER_ID").toString());	//1
			vpd.put("var2", varOList.get(i).get("TMWC_AMOUNT").toString());	//2
			vpd.put("var3", varOList.get(i).getString("TMWC_ADD_DATE"));	    //3
			vpd.put("var4", varOList.get(i).getString("TMWC_DEAL_DATE"));	    //4
			vpd.put("var5", varOList.get(i).get("TMWC_STATUS").toString());	//5
			vpd.put("var6", varOList.get(i).getString("TMWC_MEMO"));	    //6
			vpd.put("var7", varOList.get(i).getString("TMWC_UPDATE_DATE"));	    //7
			vpd.put("var8", varOList.get(i).get("TMWC_ACCOUNT_ID").toString());	//8
			vpd.put("var9", varOList.get(i).get("TMWC_OPT_ID").toString());	//9
			vpd.put("var10", varOList.get(i).get("TMWC_SERVICE_CHARGE").toString());	//10
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
