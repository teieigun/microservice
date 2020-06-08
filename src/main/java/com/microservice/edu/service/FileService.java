package com.microservice.edu.service;

import com.microservice.edu.dao.BigSmallDocumentsDao;
import com.microservice.edu.dao.CategoryTblDao;
import com.microservice.edu.dao.LessonTblDao;
import com.microservice.edu.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

	@Autowired
	private CategoryTblDao categoryTblDao;

	@Autowired
	private LessonTblDao lessonTblDao;

	@Autowired
	private BigSmallDocumentsDao bigSmallDocumentsDao;






	public List<BigCategoryTblPojo> getBigCtgCode() throws Exception {

		List<BigCategoryTblPojo> resultList=categoryTblDao.getBigCtgCode();

		return resultList;
	}

	public List<SmallCategoryTblPojo> getSmallCtgCode(String bigCtg) throws Exception {

		List<SmallCategoryTblPojo> resultList=categoryTblDao.getSmallCtgCode(bigCtg);

		return resultList;
	}

	public int saveDocumentInfo(BigSmallDocumentsPojo pojo) throws Exception {

		int count = bigSmallDocumentsDao.getDownLoadFileCount(pojo.bigCtgCode,pojo.smallCtgCode,pojo.documentName);
		if(count>0){
			int deleteCount = bigSmallDocumentsDao.deleteDocumentInfo(pojo.bigCtgCode,pojo.smallCtgCode,pojo.documentName);
		}

		int rs =bigSmallDocumentsDao.saveDocumentInfo(pojo);
		return rs;
	}

	public List<LessonTblPojo> getLessonListByCtg(String bigCtg,String smallCtg) throws Exception {

		List<LessonTblPojo> resultList=lessonTblDao.getLessonListByCtg(bigCtg,smallCtg);

		return resultList;
	}

	public List<BigSmallDocumentsPojo> getDownloadFile(String lessonId) throws Exception {

		List<BigSmallDocumentsPojo> resultList=bigSmallDocumentsDao.getDownloadFile(lessonId);

		return resultList;
	}

}
