package com.microservice.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.edu.dao.StTableDao;
import com.microservice.edu.pojo.ListPojo;

@Service
public class ListService {
	/** DAO */
	@Autowired
	private StTableDao ListDaoObj;
	
	List<ListPojo> resultList = null;
	
	public List<ListPojo> searchStudentList(String studentId,String studentNm,String sex,String grade,String classNo) throws Exception {
		
		resultList=ListDaoObj.getLoginInfo(studentId,studentNm,sex,grade,classNo);
		
		return resultList;
	}
}
