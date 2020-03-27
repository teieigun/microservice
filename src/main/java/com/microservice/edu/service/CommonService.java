package com.microservice.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.edu.dao.StCodeMstDao;
import com.microservice.edu.dao.StTableDao;
import com.microservice.edu.pojo.CodePojo;
import com.microservice.edu.pojo.ListPojo;

@Service
public class CommonService {
	/** DAO */
	@Autowired
	private StTableDao ListDaoObj;
	
	
	@Autowired
	private StCodeMstDao stCodeMstDao;
	
	List<ListPojo> resultList = null;
	
	public String getMaxStId() throws Exception {
		
		String maxStId = ListDaoObj.getMaxStudentId();
		
		return maxStId;
	}
	
	public List<CodePojo> getCodeList(String codeId) throws Exception {
		
		List<CodePojo> codeList = stCodeMstDao.getCodeList(codeId);
		
		return codeList;
	}
	
	public String getCodeNm(String id,String codeId) throws Exception {
		return stCodeMstDao.getCodeNm(id,codeId);
	}
}
