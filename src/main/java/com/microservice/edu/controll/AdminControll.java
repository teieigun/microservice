package com.microservice.edu.controll;

import com.microservice.edu.pojo.CourseMasterPojo;
import com.microservice.edu.pojo.UserBaseInfo;
import com.microservice.edu.service.ProfileService;
import com.microservice.edu.service.TopPageService;
import com.microservice.edu.util.SecurityUtil;
import com.microservice.edu.web.SessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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
