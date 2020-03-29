package com.microservice.edu.service;

import com.microservice.edu.dao.BigCategoryTblDao;
import com.microservice.edu.dao.JiaoChengChapterDao;
import com.microservice.edu.dao.JiaoChengTblDao;
import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.JiaoChengChapterPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchVideoService {

	@Autowired
	private BigCategoryTblDao bigCategoryTblDao;

	@Autowired
	private JiaoChengTblDao jiaoChengTblDao;

	@Autowired
	private JiaoChengChapterDao jiaoChengChapterDao;


	//谁都可以观看
	private static String ZERO_LEVEL ="0";


	public List<BigCategoryTblPojo> getPageInfoLunBoBigCtg() throws Exception {

		List<BigCategoryTblPojo> resultList=bigCategoryTblDao.getAllBigCtg();

		return resultList;
	}

	public List<JiaoChengChapterPojo> getChapterList(String JiaoChengId) throws Exception {

		List<JiaoChengChapterPojo> resultList = jiaoChengChapterDao.getChapterList(JiaoChengId);

		return resultList;

	}
	public List<JiaoChengChapterPojo> changeChapter(String JiaoChengId,String chapterNo) throws Exception {

		List<JiaoChengChapterPojo> resultList = jiaoChengChapterDao.getOneChapter(JiaoChengId,chapterNo);

		return resultList;

	}
}
