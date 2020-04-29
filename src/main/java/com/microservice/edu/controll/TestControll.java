package com.microservice.edu.controll;

import com.microservice.edu.pojo.BookPojo;
import com.microservice.edu.service.TestService;
import com.microservice.edu.service.TopPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
public class TestControll {


	@Autowired
	TestService testService;

	@RequestMapping(value = "/book", method = RequestMethod.GET)
	@ResponseBody
	public List<BookPojo>  login(Model model) throws Exception {

		List<BookPojo> resultList = testService.getBookInfo();

		return resultList;
	}

}
