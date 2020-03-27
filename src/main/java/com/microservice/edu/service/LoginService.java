package com.microservice.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.edu.dao.LoginDao;
import com.microservice.edu.pojo.LoginPojo;

@Service
public class LoginService {
	/** DAO */
	@Autowired
	private LoginDao loginDaoObj;
	
	List<LoginPojo> resultList = null;
	
	public List<LoginPojo> searchStudentInfo(String userId,String passwd) throws Exception {
		
		resultList=loginDaoObj.getLoginInfo(userId,passwd);
		
		return resultList;
	}
}
