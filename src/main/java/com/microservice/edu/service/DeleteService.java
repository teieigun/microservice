package com.microservice.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.edu.dao.StTableDao;
import com.microservice.edu.form.RegistForm;

@Service
public class DeleteService {
	/** DAO */
	@Autowired
	private StTableDao stTableDao;
	
	public void deleteStInfo(RegistForm registForm) {
		stTableDao.deleteStInfo(registForm);
	}
}
