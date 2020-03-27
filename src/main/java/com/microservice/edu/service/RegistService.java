package com.microservice.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.edu.dao.StTableDao;
import com.microservice.edu.form.RegistForm;
import com.microservice.edu.pojo.ListPojo;

@Service
public class RegistService {
	/** DAO */
	@Autowired
	private StTableDao stTableDao;

	List<ListPojo> resultList = null;
	
	public String getMaxStId() throws Exception {
		
		String maxStId = stTableDao.getMaxStudentId();
		
		return maxStId;
	}
	
	public void insertStInfo(RegistForm registForm) {
		stTableDao.insertStInfo(registForm);
	}

}
