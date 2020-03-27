package com.microservice.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.edu.dao.BigCategoryTblDao;
import com.microservice.edu.dao.JiaoChengTblDao;
import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.SmallCategoryTblPojo;

@Service
public class TopPageService {



	@Autowired
	private BigCategoryTblDao bigCategoryTblDao;

	@Autowired
	private JiaoChengTblDao jiaoChengTblDao;

	public List<BigCategoryTblPojo> getPageInfoLunBoBigCtg() throws Exception {

		List<BigCategoryTblPojo> resultList=bigCategoryTblDao.getAllBigCtg();

		return resultList;
	}


	public List<SmallCategoryTblPojo> getPageInfoLunBoSmallCtg(String bigCtgCode) throws Exception {

		List<SmallCategoryTblPojo> resultList=jiaoChengTblDao.getSmallCtgByPk(bigCtgCode);

		return resultList;
	}


	public List<SmallCategoryTblPojo> searchVideoBySmallCtg(String bigCtgCode) throws Exception {

		List<SmallCategoryTblPojo> resultList=jiaoChengTblDao.getSmallCtgByPk(bigCtgCode);

		return resultList;
	}
}
