package com.microservice.edu.controll;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import com.microservice.edu.dao.UserDao;
import com.microservice.edu.pojo.UserPojo;
import com.microservice.edu.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.edu.form.UploadForm;

import web.SessionContext;


@Controller
public class accountControll {

	@Autowired
	ProfileService profileService;

	@Value("${userProfilePath}")
	private String userProfilePath;

	@Autowired
	private UserDao userDao;


	@RequestMapping(value = "/account", method = RequestMethod.GET)
	@Transactional(readOnly = true)
    public String account(Model model,String ValCode,HttpServletRequest request){
		System.out.println(SessionContext.getUserName(request));

		model.addAttribute("filename", userProfilePath);
		model.addAttribute("ValCode", ValCode);
        //提示具体用户名称登录成功
        return "/account";
    }

	//获取当前用户信息
	private UserDetails getUserDetails(){
		String username = null;
		//当前认证通过的用户身份
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//用户身份
		Object principal = authentication.getPrincipal();
		if(principal == null){
			username = "匿名";
		}
		if(principal instanceof UserDetails){
			UserDetails userDetails = (UserDetails) principal;
			return userDetails;
		}
		return null;
	}

	@RequestMapping(path = "/video/upload", method = RequestMethod.POST)
	String upload(Model model, UploadForm uploadForm,HttpServletRequest request) {
		UserPojo userPojo =null;
		try {
			userPojo=userDao.findbyPk(SessionContext.getUserName(request));
			Path path = Paths.get(userProfilePath);
			if (!Files.exists(path)) {
				try {
					Files.createDirectory(path);
				} catch (NoSuchFileException ex) {
					System.err.println(ex);
				} catch (IOException ex) {
					System.err.println(ex);
				}
			}

			profileService.imgUpload(uploadForm,userProfilePath+userPojo.getValidateCode());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:/account?ValCode="+userPojo.getValidateCode();
	}


}
