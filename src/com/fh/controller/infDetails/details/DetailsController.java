package com.fh.controller.infDetails.details;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.infDetails.details.DetailsManager;

import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.FileUpload;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;
import com.fh.util.PathUtil;
import com.fh.util.Tools;
import com.fh.util.Watermark;

@Controller
@RequestMapping(value="/details")
public class DetailsController  extends BaseController{

	String menuUrl = "details/list.do"; //菜单地址(权限用)
	
	@Resource(name="detailsService")
	private DetailsManager detailsManager;
	
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("infDetails/details/detailsAdd");
		//PageData pData=dictionariesService.listSubDictById("代理等级");
		String id=pd.getString("TIC_ID");
		//List<Dictionaries> list=dictionariesService.listSubDictByParentId(id);
		//mv.addObject("msg", "save");
		mv.addObject("TIC_ID", id);
		//mv.addObject("levelList", list);
		//mv.addObject("stateList", stateList);
		return mv;
	}	
	
     
	
	@RequestMapping(value="/Add")
    @ResponseBody
	public Object Add()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		PageData pd1 = new PageData();
		pd = this.getPageData();
		Map<String,Object> map = new HashMap<String,Object>();
		List<PageData> pdList = new ArrayList<PageData>();
		//mv.setViewName("infDetails/details/detailsAdd");
		//PageData pData=dictionariesService.listSubDictById("代理等级");
		
		String TITLE = pd.getString("TITLE");					//标题
		String CONTENT = pd.getString("CONTENT");				//内容
		
		
	
		String id=pd.getString("TI_CATA_ID");
		
	
		pd1.put("TI_STATUS", '1');
		pd1.put("TI_CONTENT", CONTENT);
		pd1.put("TI_TITLE", TITLE);
		pd1.put("TI_CATA_ID", id);
		pd1.put("TI_ADD_DATE", Tools.date2Str(new Date()));	//创建时间

		
		pd1.put("TI_VIEW_COUNT", '1');
		detailsManager.save(pd1);
		//List<Dictionaries> list=dictionariesService.listSubDictByParentId(id);
		//mv.addObject("msg", "save");
		///mv.addObject("msg","success");
		//mv.setViewName("save_result");
		//mv.addObject("levelList", list);
		//mv.addObject("stateList", stateList);
		
		PageData pd2 = new PageData();
		pd2.put("stutas", "success");						//成功数
	
		pdList.add(pd2);
		map.put("list", pdList);
		return AppUtil.returnObject(pd2, map);
		
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
		
		List<PageData>	varList = detailsManager.list(page);			//列出InfPoints列表
	
		mv.setViewName("infDetails/details/details_list");
		
		mv.addObject("varList", varList);
		mv.addObject("QX",Jurisdiction.getHC());								//按钮权限
		return mv;
	}
	
	
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete(@RequestParam String TI_ID) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除InfPoints");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} 					//校验权限
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd.put("TI_ID", TI_ID);
		
		detailsManager.delete(pd);
		
		String errInfo = "success";
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
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
		String TI_ID = pd.getString("TI_ID");
		pd = detailsManager.findById(pd);							//根据ID读取		
		mv.addObject("pd", pd);													//放入视图容器
	
						
		mv.setViewName("infDetails/details/detailsEdit");
		
		return mv;
	}	
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	@ResponseBody
	public Object edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改InfPoints");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,String> map = new HashMap<String,String>();
		
		String CONTENT=pd.getString("TI_CONTENT");
		CONTENT=CONTENT.replaceAll("/jc-project-gm/plugins/ueditor/jsp", "http://wx.jckj365.com");
		pd.put("TI_CONTENT", CONTENT);
		detailsManager.edit(pd);
		String errInfo = "success";
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
		
	}
	
	/**去图片新增页面
	 * @return
	 */ 
	//nextPage(${page.currentPage});
	@RequestMapping(value="/goPicturesAdd")
	public ModelAndView goDetailsPicturesAdd() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		mv.setViewName("infDetails/details/detailsPictures_add");
		mv.addObject("pd", pd);
		mv.addObject("TI_ID", pd.getString("TI_ID"));
		return mv;
	}
	
	/**新增
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/savePictures")
	@ResponseBody
	public Object savePictures(@RequestParam(required=false) MultipartFile file,@RequestParam  String TI_ID) throws Exception{
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
		     pd.put("TI_ID", TI_ID);
			
			
			//pd.put("PICTURES_ID", this.get32UUID());			//主键
			//pd.put("TITLE", "图片");								//标题
			//pd.put("NAME", fileName);							//文件名
							//路径
			//pd.put("CREATETIME", Tools.date2Str(new Date()));	//创建时间
		//	pd.put("MASTER_ID", "1");							//附属与
		//	pd.put("BZ", "图片管理处上传");						//备注
			Watermark.setWatemark(Const.PICTURE_PATH + Const.FILEPATH_IMG + ffile + "/" + fileName);//加水印
			String str=Const.FILEPATH_IMG+ffile + "/" + fileName;
			PageData pd1 = new PageData();
			//pd1 = detailsManager.findById(pd);
			//String st=pd1.getString("TI_ICON");
			//if(st.isEmpty()){
				pd.put("TI_ICON", str);
				detailsManager.editPictures(pd);
//			}else{
//				st=st+","+str;
//				pd.put("TI_ICON", st);
//				detailsManager.editPictures(pd);
//			}
			
		
		}
		map.put("result", "ok");
		return AppUtil.returnObject(pd, map);
	}
	
	
	
}
