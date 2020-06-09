package com.microservice.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.edu.dao.CategoryTblDao;
import com.microservice.edu.dao.CommentsRootDao;
import com.microservice.edu.dao.LessonChapterDao;
import com.microservice.edu.dao.LessonTblDao;
import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.LessonChapterPojo;
import com.microservice.edu.pojo.LessonChapterPojo;

@Service
public class WatchVideoService {

	@Autowired
	private CategoryTblDao bigCategoryTblDao;

	@Autowired
	private LessonTblDao LessonTblDao;

	@Autowired
	private LessonChapterDao LessonChapterDao;

	@Autowired
	private CommentsRootDao commentsRootDao;

	//谁都可以观看
	private static String ZERO_LEVEL = "0";

	public List<BigCategoryTblPojo> getPageInfoLunBoBigCtg() throws Exception {

		List<BigCategoryTblPojo> resultList = bigCategoryTblDao.getAllBigCtg();

		return resultList;
	}

	public List<LessonChapterPojo> getChapterList(String email, String LessonId) throws Exception {

		List<LessonChapterPojo> resultList = LessonChapterDao.getChapterList(email, LessonId);

		return resultList;

	}
	public List<LessonChapterPojo> getChapterListNotBuy(String LessonId) throws Exception {

		List<LessonChapterPojo> resultList = LessonChapterDao.getChapterListNotBuy(LessonId);

		return resultList;

	}

	public List<LessonChapterPojo> getSectionList(String email, String LessonId) throws Exception {

		List<LessonChapterPojo> resultList = LessonChapterDao.getSectionList(email, LessonId);

		return resultList;

	}
	public List<LessonChapterPojo> getSectionListNotBuy(String LessonId) throws Exception {

		List<LessonChapterPojo> resultList = LessonChapterDao.getSectionListByNotBuy(LessonId);

		return resultList;

	}

	public List<LessonChapterPojo> getSectionListByLessonId(String lessonId) throws Exception {

		List<LessonChapterPojo> resultList = LessonChapterDao.getSectionListByNotBuy(lessonId);

		return resultList;

	}

	public List<LessonChapterPojo> getOneSection(String LessonId, String chapterNo, String sectionNo)
			throws Exception {

		List<LessonChapterPojo> resultList = LessonChapterDao.getOneSection(LessonId, chapterNo, sectionNo);

		return resultList;

	}

	public List<LessonChapterPojo> changeChapter(String LessonId, String chapterNo, String sectionNo,String email)
			throws Exception {

		List<LessonChapterPojo> resultList = LessonChapterDao.getOneSection(LessonId, chapterNo, sectionNo,email);

		return resultList;

	}

	public int getCommentsRootCount(int lessonId, int chapterNo) {
		return commentsRootDao.getCommentsRootCount(lessonId, chapterNo);
	}

}
