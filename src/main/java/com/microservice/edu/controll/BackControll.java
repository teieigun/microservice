package com.microservice.edu.controll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.edu.constants.StdConstants;
import com.microservice.edu.form.RegistForm;
import com.microservice.edu.pojo.CodePojo;
import com.microservice.edu.service.CommonService;

@Controller
public class BackControll {
	
	@Autowired
	CommonService commonServiceObj;

	@RequestMapping(value = "/toSearch", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String regist(Model model, String StId,String doflg) throws Exception {
		
		String reString = "/search";
		return reString;
	}
	
	@RequestMapping(value = "/backRegist", method = RequestMethod.POST)
	@Transactional(readOnly = true)
	public String regist(Model model, RegistForm registForm) throws Exception {

		List<CodePojo> codePojoList = commonServiceObj.getCodeList(StdConstants.CODE_SHENG);
		model.addAttribute("codePojoList", codePojoList);
		model.addAttribute("registForm", registForm);
		String reString = "/regist";
		return reString;
	}

}
