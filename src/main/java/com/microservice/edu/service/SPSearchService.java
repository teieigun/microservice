package com.microservice.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.edu.dao.HotKeywordsDao;
import com.microservice.edu.dao.LessonTblDao;
import com.microservice.edu.pojo.CoursePojo;
import com.microservice.edu.pojo.HotKeyPojo;
import com.microservice.edu.pojo.SearchInfoData;
import com.microservice.edu.pojo.SearchInfoPojo;

@Service
public class SPSearchService {

	@Autowired
	private LessonTblDao lessonTblDao;

	@Autowired
	private HotKeywordsDao hotKeywordsDao;

	public SearchInfoPojo getSearchInfor() {
		SearchInfoPojo searchInfo = new SearchInfoPojo();
		boolean result = false;
		SearchInfoData data = new SearchInfoData();
		try {
			//HotKeywords情報を取得
			List<HotKeyPojo> hotKeyWords = hotKeywordsDao.getAllHotKey();
			//BaseCourse情報を取得
			List<CoursePojo> allCourseList = lessonTblDao.getAllCourses();
			data.setHotKeyWords(hotKeyWords);
			data.setAllCourseList(allCourseList);
			result = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			result = false;
		}
		searchInfo.setResult(result);
		searchInfo.setData(data);
		return searchInfo;
	}
}
