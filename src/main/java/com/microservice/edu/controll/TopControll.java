package com.microservice.edu.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.edu.service.TopPageService;


@Controller
public class TopControll {


	@Autowired
	TopPageService topPageService;

	@RequestMapping(value = "/login", method={RequestMethod.GET,RequestMethod.POST})
	@Transactional(readOnly = true)
	public String login(Model model) throws Exception {

		return "/login";
	}

	@GetMapping(value = "/video",produces = {"text/plain;charset=UTF-8"})
	@PreAuthorize("hasAuthority('5')")//拥有p1权限才可以访问
	@Transactional(readOnly = true)
	public String index(Model model,String bigCtgCode,String smallCtgCode) throws Exception {

		topPageService.getIndexInfo(model,bigCtgCode,smallCtgCode);

		return "/index";
	}


	@RequestMapping(value = "/", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String index2(Model model) throws Exception {

		return "/login";
	}


	@RequestMapping(value = "/video/account", method = RequestMethod.GET)
	@Transactional(readOnly = true)
    public String account(){
        //提示具体用户名称登录成功
        return "/account";
    }
}
