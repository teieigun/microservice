package com.microservice.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.edu.dao.StTableDao;
import com.microservice.edu.form.RegistForm;
import com.microservice.edu.pojo.StInfoPojo;

@Service
public class UpdateService {
	/** DAO */
	@Autowired
	private StTableDao stTableDao;
	
	List<StInfoPojo> resultList = null;
	
	public List<StInfoPojo> getUpdateInfo(String StId) {
		resultList =stTableDao.getStAllInfoById(StId);
		return resultList;
	}
	public void updateStInfo(RegistForm registForm) {
		stTableDao.updateStInfo(registForm);
	}
}
