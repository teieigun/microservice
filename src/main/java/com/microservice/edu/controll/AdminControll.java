package com.microservice.edu.controll;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AdminControll {



	private String email;

	@GetMapping(value = "/admin",produces = {"text/plain;charset=UTF-8"})
	@PreAuthorize("hasAuthority('9')")//拥有p1权限才可以访问
	@Transactional(readOnly = true)
	public String admin(Model model, String bigCtgCode, String smallCtgCode, HttpServletRequest request) throws Exception {

		return "/admin";
	}

}
