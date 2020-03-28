package com.microservice.edu.service;

import com.microservice.edu.dao.BigCategoryTblDao;
import com.microservice.edu.dao.JiaoChengTblDao;
import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.JiaoChengTblExt1Pojo;
import com.microservice.edu.pojo.SmallCategoryTblPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchVideoService {

	@Autowired
	private BigCategoryTblDao bigCategoryTblDao;

	//谁都可以观看
	private static String ZERO_LEVEL ="0";

	@Autowired
	private JiaoChengTblDao jiaoChengTblDao;

	public List<BigCategoryTblPojo> getPageInfoLunBoBigCtg() throws Exception {

		List<BigCategoryTblPojo> resultList=bigCategoryTblDao.getAllBigCtg();

		return resultList;
	}

}
