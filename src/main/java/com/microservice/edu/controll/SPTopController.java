package com.microservice.edu.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.edu.pojo.TopInfoPojo;
import com.microservice.edu.service.SPTopService;

@RestController
@RequestMapping("/top")
public class SPTopController {
	@Autowired
	SPTopService spTopService;

	@ResponseBody
	@RequestMapping("/getInfor")
	public TopInfoPojo getTopInfor() {
		return spTopService.getTopInfor();
	}
}
