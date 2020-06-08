package com.microservice.edu.service;

import java.util.ArrayList;
import java.util.List;

import com.microservice.edu.dao.CourseMasterDao;
import com.microservice.edu.pojo.CourseMasterPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.microservice.edu.dao.CategoryTblDao;
import com.microservice.edu.dao.LessonTblDao;
import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.LessonTblPojo;
import com.microservice.edu.pojo.SmallCategoryTblPojo;

@Service
public class TopPageService {



	@Autowired
	private CategoryTblDao CategoryTblDao;

	//谁都可以观看
	private static String ZERO_LEVEL ="9";

	@Autowired
	private LessonTblDao LessonTblDao;

	@Autowired
	private CourseMasterDao courseMasterDao;


	public List<BigCategoryTblPojo> getPageInfoLunBoBigCtg() throws Exception {

		List<BigCategoryTblPojo> resultList=CategoryTblDao.getAllBigCtg();

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

	public List<LessonTblPojo> searchAllEnableVideo(String email,String bigCtgCode,String smallCtgCode) throws Exception {

		List<LessonTblPojo> resultList=LessonTblDao.getAllEnableVideo(email,bigCtgCode,smallCtgCode);

		return resultList;
	}

	public List<LessonTblPojo> searchAllEnableVideoUseLike(String email,String videoNm) throws Exception {

		List<LessonTblPojo> resultList=LessonTblDao.getAllEnableVideoUseLike(email,videoNm);

		return resultList;
	}

	public List<LessonTblPojo> searchAllEnableVideoByCtg(String bigCtgCode,String smallCtgCode) throws Exception {

		List<LessonTblPojo> resultList=LessonTblDao.getAllEnableVideoByCtg(ZERO_LEVEL,bigCtgCode,smallCtgCode);

		return resultList;
	}

	public List<LessonTblPojo> searchAllEnableVideoIsRcommend(String email) throws Exception {

		List<LessonTblPojo> resultList=LessonTblDao.getAllEnableVideoIsRcommend(email);

		return resultList;
	}



	public void getIndexInfo(Model model,String bigCtgCode,String smallCtgCode,String email) throws Exception {
		List<List<SmallCategoryTblPojo>> listListSmallCategoryTblPojo = new ArrayList<List<SmallCategoryTblPojo>>();

		//轮播菜单
			List<BigCategoryTblPojo> bigCtgList = getPageInfoLunBoBigCtg();
		if (bigCtgList != null && bigCtgList.size() > 0) {
			for (BigCategoryTblPojo bigCategoryTblPojo : bigCtgList) {
				listListSmallCategoryTblPojo.add(getPageInfoLunBoSmallCtg(bigCategoryTblPojo.getCtgCode()));
			}
		}        //获取首页视频地址，图片
        List<LessonTblPojo> listLessonTblPojo = searchAllEnableVideo(email,bigCtgCode,smallCtgCode);


        model.addAttribute("listLessonTblPojo", listLessonTblPojo);
		model.addAttribute("listListSmallCategoryTblPojo", listListSmallCategoryTblPojo);
		model.addAttribute("bigCtgList", bigCtgList);
	}

	public void getIndexInfoUseLike(Model model,String videoNm,String email) throws Exception {
		List<List<SmallCategoryTblPojo>> listListSmallCategoryTblPojo = new ArrayList<List<SmallCategoryTblPojo>>();

		//轮播菜单
		List<BigCategoryTblPojo> bigCtgList = getPageInfoLunBoBigCtg();
		if (bigCtgList != null && bigCtgList.size() > 0) {
			for (BigCategoryTblPojo bigCategoryTblPojo : bigCtgList) {
				listListSmallCategoryTblPojo.add(getPageInfoLunBoSmallCtg(bigCategoryTblPojo.getCtgCode()));
			}
		}        //获取首页视频地址，图片
		List<LessonTblPojo> listLessonTblPojo = searchAllEnableVideoUseLike(email,videoNm);


		model.addAttribute("listLessonTblPojo", listLessonTblPojo);
		model.addAttribute("listListSmallCategoryTblPojo", listListSmallCategoryTblPojo);
		model.addAttribute("bigCtgList", bigCtgList);
	}

	public void getBuyCourseVideo(Model model, String bigCtgCode, String smallCtgCode, String email,String courseId) throws Exception {

		List<List<SmallCategoryTblPojo>> listListSmallCategoryTblPojo = new ArrayList<List<SmallCategoryTblPojo>>();

		//轮播菜单
		List<BigCategoryTblPojo> bigCtgList = getPageInfoLunBoBigCtg();
		if (bigCtgList != null && bigCtgList.size() > 0) {
			for (BigCategoryTblPojo bigCategoryTblPojo : bigCtgList) {
				listListSmallCategoryTblPojo.add(getPageInfoLunBoSmallCtg(bigCategoryTblPojo.getCtgCode()));
			}
		}        //获取首页视频地址，图片
		List<LessonTblPojo> listLessonTblPojo = searchBuyCourseVideo(email,courseId);

		model.addAttribute("listLessonTblPojo", listLessonTblPojo);
		model.addAttribute("listListSmallCategoryTblPojo", listListSmallCategoryTblPojo);
		model.addAttribute("bigCtgList", bigCtgList);
	}

	public List<LessonTblPojo> searchBuyCourseVideo(String email,String courseId) throws Exception {

		List<LessonTblPojo> resultList=LessonTblDao.getBuyCourseVideo(email,courseId);

		return resultList;
	}

	public List<CourseMasterPojo> getCourseInfo(String courseId) throws Exception {

		List<CourseMasterPojo> resultList=courseMasterDao.getCourseInfo(courseId);

		return resultList;
	}

}
