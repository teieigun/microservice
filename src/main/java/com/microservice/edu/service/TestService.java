package com.microservice.edu.service;

import com.microservice.edu.dao.BookDao;
import com.microservice.edu.pojo.BookPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
