package com.fh.controller.timer.task;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fh.controller.base.BaseController;
import com.fh.service.order.mangeorder.MangeOrderManager;
import com.fh.util.PageData;

@Component
@Lazy(value=false)
public class orderJob extends BaseController{
	@Resource(name="mangeorderService")
	private MangeOrderManager mangeorderService;
	
	   @Scheduled(cron = "0 0 2 * * ?")//每隔2小时执行一次
	    public void orderJob() {
		   try {
			   PageData pd = new PageData();
			   List<PageData>  varList= mangeorderService.listOrder(pd);
			   for (PageData pageData : varList) {
				   PageData pd1 = new PageData();
				   pd1.put("TO_ID", pageData.get("TO_ID"));
				   mangeorderService.editOrderStatus(pd1);
			}
			  
		} catch (Exception e) {
			logBefore(logger, "order is working......定时器报错"+e);
		}
		   
	       
	    }


	    //@Scheduled(cron = "0 0 1 * * ?")//每天凌晨1点整
	    //@Scheduled(cron = "0 30 0 * * ?")//每天凌晨0点30分
	    //@Scheduled(cron = "0 */60 * * * ?")//1小时处理一次
	
	
}
