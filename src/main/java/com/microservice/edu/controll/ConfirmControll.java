package com.microservice.edu.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.edu.form.RegistForm;
import com.microservice.edu.service.DeleteService;
import com.microservice.edu.service.RegistService;
import com.microservice.edu.service.UpdateService;

@Controller
public class ConfirmControll {
	
	@Autowired
	RegistService registServiceObj;
	
	@Autowired
	UpdateService updateServiceObj;
	
	@Autowired
	DeleteService deleteServiceObj;
	

	@RequestMapping(value = "/registComfirm", method = RequestMethod.POST)
	@Transactional
	public String list(Model model, RegistForm registForm) throws Exception {

		if ("1".equals(registForm.doflg)) {
			registServiceObj.insertStInfo(registForm);
			model.addAttribute("message", "登陆成功");
		} else if ("2".equals(registForm.doflg)) {
			updateServiceObj.updateStInfo(registForm);
			model.addAttribute("message", "更新成功");
		} else {
			deleteServiceObj.deleteStInfo(registForm);
			model.addAttribute("message", "删除成功");
		}

		return "/done";
	}
}
