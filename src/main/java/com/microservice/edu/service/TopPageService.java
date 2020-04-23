package com.microservice.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.edu.dao.BigCategoryTblDao;
import com.microservice.edu.dao.LessonTblDao;
import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.LessonTblPojo;
import com.microservice.edu.pojo.SmallCategoryTblPojo;

@Service
public class TopPageService {



	@Autowired
	private BigCategoryTblDao bigCategoryTblDao;

	//谁都可以观看
	private static String ZERO_LEVEL ="9";

	@Autowired
	private LessonTblDao LessonTblDao;

	public List<BigCategoryTblPojo> getPageInfoLunBoBigCtg() throws Exception {

		List<BigCategoryTblPojo> resultList=bigCategoryTblDao.getAllBigCtg();

		return resultList;
	}


	public List<SmallCategoryTblPojo> getPageInfoLunBoSmallCtg(String bigCtgCode) throws Exception {

		List<SmallCategoryTblPojo> resultList=LessonTblDao.getSmallCtgByPk(bigCtgCode);

		return resultList;
	}


	public List<SmallCategoryTblPojo> searchVideoBySmallCtg(String bigCtgCode) throws Exception {

		List<SmallCategoryTblPojo> resultList=LessonTblDao.getSmallCtgByPk(bigCtgCode);

		return resultList;
	}

	public List<LessonTblPojo> searchAllEnableVideo() throws Exception {

		List<LessonTblPojo> resultList=LessonTblDao.getAllEnableVideo(ZERO_LEVEL);

		return resultList;
	}

	public List<LessonTblPojo> searchAllEnableVideoByCtg(String bigCtgCode,String smallCtgCode) throws Exception {

		List<LessonTblPojo> resultList=LessonTblDao.getAllEnableVideoByCtg(ZERO_LEVEL,bigCtgCode,smallCtgCode);

		return resultList;
	}


}
