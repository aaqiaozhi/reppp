package com.fh.controller.pictures.managenimage;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.FileUpload;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.PathUtil;
import com.fh.util.Tools;
import com.fh.util.Watermark;
import com.fh.service.pictures.managenimage.ManagenImageManager;
import com.fh.service.product.productinfo.ProductInfoManager;

/** 
 * 说明：杰创图片管理
 * 创建人：JCKJ
 * 创建时间：2018-04-07
 */
@Controller
@RequestMapping(value="/managenimage")
public class ManagenImageController extends BaseController {
	
	String menuUrl = "managenimage/list.do"; //菜单地址(权限用)
	@Resource(name="managenimageService")
	private ManagenImageManager managenimageService;
	
	@Resource(name="productinfoService")
	private ProductInfoManager productinfoService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增ManagenImage");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("PICTURES_ID", this.get32UUID());
		pd.put("CREATETIME", Tools.date2Str(new Date()));//主键
		managenimageService.save(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"删除ManagenImage");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		managenimageService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改ManagenImage");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		managenimageService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表ManagenImage");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = managenimageService.list(page);	//列出ManagenImage列表
		mv.setViewName("pictures/managenimage/managenimage_list");
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
		
		List<PageData> list2=productinfoService.listcategory(pd);
		
		mv.setViewName("pictures/managenimage/managenimage_edit");
		mv.addObject("msg", "save");
		mv.addObject("list2", list2);
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
		pd = managenimageService.findById(pd);	//根据ID读取
		List<PageData> list2=productinfoService.listcategory(pd);
		mv.setViewName("pictures/managenimage/managenimage_edit");
		mv.addObject("msg", "edit");
		mv.addObject("list2", list2);
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除ManagenImage");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			managenimageService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, Jurisdiction.getUsername()+"导出ManagenImage到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("类型");	//1
		titles.add("标题");	//2
		titles.add("文件名");	//3
		titles.add("创建时间");	//4
		titles.add("备注");	//5
		dataMap.put("titles", titles);
		List<PageData> varOList = managenimageService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).get("TYPE").toString());	//1
			vpd.put("var2", varOList.get(i).getString("TITLE"));	    //2
			vpd.put("var3", varOList.get(i).getString("NAME"));	    //3
			vpd.put("var4", varOList.get(i).getString("CREATETIME"));	    //4
			vpd.put("var5", varOList.get(i).getString("BZ"));	    //5
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
	
	/**去图片新增页面
	 * @return
	 */ 
	//nextPage(${page.currentPage});
	@RequestMapping(value="/goPicture")
	public ModelAndView goDetailsPicturesAdd() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		mv.setViewName("pictures/managenimage/pt_add");
		mv.addObject("pd", pd);
		mv.addObject("PICTURES_ID", pd.getString("PICTURES_ID"));
		return mv;
	}
	
	/**新增
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/savePt")
	@ResponseBody
	public Object savePictures(@RequestParam(required=false) MultipartFile file,@RequestParam  String PICTURES_ID) throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		
		logBefore(logger, Jurisdiction.getUsername()+"新增图片");
		Map<String,String> map = new HashMap<String,String>();
		String  ffile = DateUtil.getDays(), fileName = "";
		PageData pd = new PageData();
		if(Jurisdiction.buttonJurisdiction(menuUrl, "add")){
			if (null != file && !file.isEmpty()) {
				//String filePath = PathUtil.getClasspath() + 		//文件上传路径
				String filePath=Const.PICTURE_PATH+Const.FILEPATH_IMG + ffile;
				
				
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//执行上传
			}else{
				logBefore(logger, Jurisdiction.getUsername()+"上传失败");
				System.out.println("上传失败");
			}
		     pd.put("PICTURES_ID", PICTURES_ID);
			
			
			//pd.put("PICTURES_ID", this.get32UUID());			//主键
			//pd.put("TITLE", "图片");								//标题
			//pd.put("NAME", fileName);							//文件名
							//路径
			//pd.put("CREATETIME", Tools.date2Str(new Date()));	//创建时间
		//	pd.put("MASTER_ID", "1");							//附属与
		//	pd.put("BZ", "图片管理处上传");						//备注
			Watermark.setWatemark(Const.PICTURE_PATH + Const.FILEPATH_IMG + ffile + "/" + fileName);//加水印
			String str=Const.FILEPATH_IMG+ffile + "/" + fileName;
			
			pd.put("PATH", str);
			managenimageService.editPictures(pd);
			
			
		
		}
		map.put("result", "ok");
		return AppUtil.returnObject(pd, map);
	}
	
}
