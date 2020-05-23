package com.microservice.edu.service;

import com.microservice.edu.dao.BookDao;
import com.microservice.edu.dao.CategoryTblDao;
import com.microservice.edu.dao.LessonTblDao;
import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.BookPojo;
import com.microservice.edu.pojo.LessonTblPojo;
import com.microservice.edu.pojo.SmallCategoryTblPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

	@Autowired
	private CategoryTblDao categoryTblDao;

	@Autowired
	private LessonTblDao lessonTblDao;



	public List<BigCategoryTblPojo> getBigCtgCode() throws Exception {

		List<BigCategoryTblPojo> resultList=categoryTblDao.getBigCtgCode();

		return resultList;
	}

	public List<SmallCategoryTblPojo> getSmallCtgCode(String bigCtg) throws Exception {

		List<SmallCategoryTblPojo> resultList=categoryTblDao.getSmallCtgCode(bigCtg);

		return resultList;
	}

	public List<LessonTblPojo> getLessonListByCtg(String bigCtg,String smallCtg) throws Exception {

		List<LessonTblPojo> resultList=lessonTblDao.getLessonListByCtg(bigCtg,smallCtg);

		return resultList;
	}

}
