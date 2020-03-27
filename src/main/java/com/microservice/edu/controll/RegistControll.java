package com.microservice.edu.controll;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.edu.constants.StdConstants;
import com.microservice.edu.form.RegistForm;
import com.microservice.edu.pojo.CodePojo;
import com.microservice.edu.service.CommonService;
import com.microservice.edu.service.LoginService;
import com.microservice.edu.service.RegistService;

@Controller
public class RegistControll {

	@Autowired
	LoginService loginServiceObj;

	@Autowired
	RegistService registServiceObj;

	@Autowired
	CommonService commonServiceObj;

	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String regist(Model model, String doflg) throws Exception {

		RegistForm registForm = new RegistForm();
		registForm.setDoflg("1");

		model.addAttribute("registForm", registForm);

		List<CodePojo> codePojoList = commonServiceObj.getCodeList(StdConstants.CODE_SHENG);
		model.addAttribute("codePojoList", codePojoList);

		String reString = "/regist";
		return reString;
	}

	@RequestMapping(value = "/registInput", method = RequestMethod.POST)
	@Transactional(readOnly = true)
	public String registInput(Model model, @ModelAttribute("form") @Valid RegistForm registForm, BindingResult result)
			throws Exception {

		// get max stId
		if ("1".equals(registForm.doflg)) {
			String maxStId = registServiceObj.getMaxStId();
			String maxStIdPlusOne = String.valueOf(Integer.valueOf(maxStId) + 1);
			registForm.setUserId(maxStIdPlusOne);
		}

		model.addAttribute("registForm", registForm);

		model.addAttribute("isNan", registForm.getSex().equals("1"));

		model.addAttribute("shengNm", commonServiceObj.getCodeNm(StdConstants.CODE_SHENG, registForm.getZuji()));

		String reString = "/comfirm";
		return reString;
	}

}
