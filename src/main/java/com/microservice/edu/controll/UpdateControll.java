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
import com.microservice.edu.pojo.StInfoPojo;
import com.microservice.edu.service.CommonService;
import com.microservice.edu.service.LoginService;
import com.microservice.edu.service.UpdateService;

@Controller
public class UpdateControll {

	@Autowired
	LoginService loginServiceObj;
	
	@Autowired
	UpdateService updateServiceObj;
	
	@Autowired
	CommonService commonServiceObj;

	@RequestMapping(value = "/updateLine", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String regist(Model model, String StId,String doflg) throws Exception {
		
		
		List<StInfoPojo> resultList = updateServiceObj.getUpdateInfo(StId);
		
		RegistForm  registForm = new RegistForm();
		if(resultList != null && resultList.size() > 0) {
			
			StInfoPojo pojo = resultList.get(0);
			registForm.setUserId(pojo.stId);
			registForm.setUserNm(pojo.stNm);
			registForm.setSex(pojo.stSex);
			registForm.setAge(pojo.stAge);
			registForm.setGrade(pojo.stGrade);
			registForm.setClassNo(pojo.stClass);
			registForm.setAddress(pojo.stAdd);
			registForm.setJianhu(pojo.stJianhu);
			registForm.setPhone(pojo.stJhPhone);
			registForm.setZuji(pojo.stZuji);
			registForm.setStBirthday(pojo.stBirthday);
			
		}
		
		List<CodePojo> codePojoList = commonServiceObj.getCodeList(StdConstants.CODE_SHENG);
		model.addAttribute("codePojoList", codePojoList);
		
		registForm.setDoflg(doflg);
		
		model.addAttribute("registForm", registForm);
		
		if("2".equals(doflg)) {
			model.addAttribute("buttonNm", "更新");
		}

		return "/regist";
	}

}
