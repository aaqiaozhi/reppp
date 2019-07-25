package com.fh.service.infDetails.details.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.service.infDetails.details.DetailsManager;
import com.fh.util.PageData;

@Service("detailsService")
public class DetailsService   implements DetailsManager {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	
	
	@Override
	public void save(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		dao.save("DetailsMapper.save", pd);
	}




	@Override
	public List<PageData> list(Page page) throws Exception {
		return (List<PageData>)dao.findForList("DetailsMapper.datalistPage", page);
	}




	@Override
	public void delete(PageData pd) throws Exception {
		dao.delete("DetailsMapper.delete", pd);
		}




	@Override
	public PageData findById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("DetailsMapper.findById", pd);
	}




	@Override
	public void edit(PageData pd) throws Exception {
		dao.update("DetailsMapper.edit", pd);
		
	}



     /***
      * 图片
      */
	@Override
	public void editPictures(PageData pd) throws Exception {
		dao.update("DetailsMapper.editPictures", pd);
	}

	
	
	
	
}
