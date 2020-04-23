package com.microservice.edu.controll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchControll {
	
	
	 @Autowired
	 ListService listServiceObj;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@Transactional(readOnly = true)
	public String list(Model model,String studentId,String studentNm,String sex,String grade,String classNo) throws Exception {

		// get value from screen
		String reString = "/search";
		
		//call service for student list
		List<ListPojo> resultList =listServiceObj.searchStudentList(studentId, studentNm, sex, grade,classNo);
		
		//throw data to screen
		model.addAttribute("searchResult",resultList);
		return reString;
	}
}
