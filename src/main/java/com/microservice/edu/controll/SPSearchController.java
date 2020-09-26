package com.microservice.edu.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.edu.pojo.SearchInfoPojo;
import com.microservice.edu.service.SPSearchService;

@RestController
@RequestMapping("/search")
public class SPSearchController {
	@Autowired
	SPSearchService spSearchService;

	@ResponseBody
	@RequestMapping("/getInfor")
	public SearchInfoPojo getSearchInfor() {
		return spSearchService.getSearchInfor();
	}
}
