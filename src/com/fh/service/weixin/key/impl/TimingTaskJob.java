package com.fh.service.weixin.key.impl;

import java.util.Date;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.fh.controller.base.BaseController;
import com.fh.util.PageData;
import com.fh.util.Tools;


public class TimingTaskJob extends BaseController implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
//		parameter.put("APPID", appid);
//		parameter.put("APPSECRET", appsecret);
//		parameter.put("KEY_ID", KEY_ID);
				JobDataMap dataMap = context.getJobDetail().getJobDataMap();
				Map<String,Object> parameter = (Map<String,Object>)dataMap.get("parameterList");	//获取参数
				String appid = parameter.get("APPID").toString();
				String appsecret = parameter.get("APPSECRET").toString();
				String key_id = parameter.get("KEY_ID").toString();
//				TABLENAME = TABLENAME.equals("all")?"":TABLENAME;
//				
//				String map=dataMap.getString("parameterList");
//				Map<String,Object> parameter = (Map<String,Object>)dataMap.get("APPID");	//获取参数
//				Map<String,Object> parameter2 = (Map<String,Object>)dataMap.get("APPSECRET");
//				Map<String,Object> parameter3 = (Map<String,Object>)dataMap.get("KEY_ID");
//				String appid = parameter.get("APPID").toString();
//				String appsecret = parameter.get("APPSECRET").toString();
//				String key_id = parameter.get("KEY_ID").toString();
			
				//普通类从spring容器中拿出service
				WebApplicationContext webctx=ContextLoader.getCurrentWebApplicationContext();
				KeyService kService = (KeyService)webctx.getBean("keyService");
				String tokenString=kService.getAccess_token(appid, appsecret);
				String jsTokenString=kService.getJsapi_ticket(tokenString);
				
				PageData pd = new PageData();
				pd.put("KEY_ID", key_id);
				pd.put("ACCESS_TOKEN", tokenString);
				pd.put("JSAPI_TICKET", jsTokenString);
				pd.put("UPDATE_TIME", Tools.date2Str(new Date()));
				try {
					kService.editToken(pd);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}

}
