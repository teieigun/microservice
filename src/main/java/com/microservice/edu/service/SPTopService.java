package com.microservice.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.edu.constants.MicroServiceConstants;
import com.microservice.edu.dao.LessonTblDao;
import com.microservice.edu.dao.SwiperDao;
import com.microservice.edu.pojo.CoursePojo;
import com.microservice.edu.pojo.SwiperPojo;
import com.microservice.edu.pojo.TopInfoData;
import com.microservice.edu.pojo.TopInfoPojo;

@Service
public class SPTopService {
	@Autowired
	private SwiperDao swiperDao;

	@Autowired
	private LessonTblDao lessonTblDao;

	public TopInfoPojo getTopInfor() {
		TopInfoPojo topInfo = new TopInfoPojo();
		boolean result = false;
		// introduceTitle、baseCourseTitle内容を取得
		TopInfoData data = new TopInfoData();
		try {
			String introduceTitle = MicroServiceConstants.IntroduceTitle;
			String baseCourseTitle = MicroServiceConstants.BaseCourseTitle;
			//Swiper情報を取得
			List<SwiperPojo> swiperList = swiperDao.getAllSwiper();
			//BaseCourse情報を取得
			List<CoursePojo> baseCourseList = lessonTblDao.getBaseCourses();
			data.setIntroduceTitle(introduceTitle);
			data.setBaseCourseTitle(baseCourseTitle);
			data.setSwiperList(swiperList);
			data.setBaseCourseList(baseCourseList);
			result = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			result = false;
		}
		topInfo.setResult(result);
		topInfo.setData(data);
		return topInfo;
	}

}
