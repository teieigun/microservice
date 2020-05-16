package com.microservice.edu.service;


import com.microservice.edu.dao.UserDao;
import com.microservice.edu.form.UploadForm;
import com.microservice.edu.pojo.UserPojo;
import com.microservice.edu.util.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.SessionContext;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class ProfileService {

	@Autowired
	private UserDao userDao;

	public UserPojo getBookInfo(String email) throws Exception {


		UserPojo userPojo=userDao.findbyPk(email);

		return userPojo;
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
}
