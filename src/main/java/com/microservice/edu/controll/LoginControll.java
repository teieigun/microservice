package com.microservice.edu.controll;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.edu.form.LoginForm;
import com.microservice.edu.pojo.LoginPojo;
import com.microservice.edu.service.LoginService;

@Controller
public class LoginControll {
	
	
	 @Autowired
	 LoginService loginServiceObj;
	 
		@RequestMapping(value = "/top", method = RequestMethod.GET)
		@Transactional(readOnly = true)
		public String index(Model model,String userId,String passwd) throws Exception {

			String reString = "/login";
		return reString;
		}
	 
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@Transactional(readOnly = true)
	public String list(Model model,@ModelAttribute("form") @Valid LoginForm loginForm,BindingResult result) throws Exception {
		
		
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for(ObjectError error : result.getAllErrors()) {
			errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
		}

		String reString = "/login";
		
		List<LoginPojo> resultList =loginServiceObj.searchStudentInfo(loginForm.userId, loginForm.passwd);
		if(resultList!=null && resultList.size()>0) {
			reString = "/search";
		}
		return reString;
	}
}
