package com.microservice.edu.service;

import com.microservice.edu.dao.BigCategoryTblDao;
import com.microservice.edu.dao.BookDao;
import com.microservice.edu.dao.LessonTblDao;
import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.BookPojo;
import com.microservice.edu.pojo.LessonTblPojo;
import com.microservice.edu.pojo.SmallCategoryTblPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

	@Autowired
	private BookDao bookDao;

	public List<BookPojo> getBookInfo() throws Exception {

		List<BookPojo> resultList=bookDao.getBook();

		return resultList;
	}

}
