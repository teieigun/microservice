package com.microservice.edu.service;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.microservice.edu.dao.UserBaseProfileDao;
import com.microservice.edu.form.ChangePwdForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.edu.dao.UserDao;
import com.microservice.edu.form.UploadForm;
import com.microservice.edu.pojo.UserBaseInfo;
import com.microservice.edu.util.ImageUpload;


@Service
public class ProfileService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserBaseProfileDao userBaseProfileDao;


	public UserBaseInfo getUserInfoInfo(String email) throws Exception {


		UserBaseInfo userBaseInfo=userDao.findUserInofByPk(email);

		return userBaseInfo;
	}

	public void imgUpload(UploadForm uploadForm,String userProfilePath){

		String filename = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
		Path uploadfile = Paths
				.get(userProfilePath);

		String imageString = uploadForm.getImageCode().replace("url(\"","");
		imageString=imageString.replace("\")","");
		imageString=imageString.replace(" ","+");

		imageString = imageString.replace("data:image/jpeg;base64,", "");
		imageString = imageString.replace("data:image/png;base64,", "");

		ImageUpload.writeImage(imageString,uploadfile);


	}
	public int updatePasswd(ChangePwdForm cpwdForm){

		int rows = userBaseProfileDao.updatePasswd(cpwdForm);

		return rows;
	}
}
