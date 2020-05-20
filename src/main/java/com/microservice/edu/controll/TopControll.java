package com.microservice.edu.controll;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.edu.pojo.UserBaseInfo;
import com.microservice.edu.service.ProfileService;
import com.microservice.edu.service.TopPageService;

import com.microservice.edu.web.SessionContext;


@Controller
public class TopControll {


	@Autowired
	TopPageService topPageService;

	@Autowired
	ProfileService profileService;



	@RequestMapping(value = "/login", method={RequestMethod.GET,RequestMethod.POST})
	@Transactional(readOnly = true)
	public String login(Model model) throws Exception {
		return "/login";
	}

	@GetMapping(value = "/video",produces = {"text/plain;charset=UTF-8"})
	@PreAuthorize("hasAuthority('5')")//拥有p1权限才可以访问
	@Transactional(readOnly = true)
	public String index(Model model, String bigCtgCode, String smallCtgCode, HttpServletRequest request) throws Exception {

		String sessionId = request.getSession().getId();

		SessionContext.setAttribute(request, sessionId,getUserDetails());

		topPageService.getIndexInfo(model,bigCtgCode,smallCtgCode);

		UserBaseInfo userBaseInfo = profileService.getBookInfo(SessionContext.getUserName(request));

		SessionContext.setAttribute(request, "profileImage", userBaseInfo.profile_image);

		model.addAttribute("profileImage",userBaseInfo.profile_image);

		return "/index";
	}


	@RequestMapping(value = "/", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String index2(Model model) throws Exception {

		return "/login";
	}


	@RequestMapping(value = "/video/account", method = RequestMethod.GET)
	@Transactional(readOnly = true)
    public String account(HttpServletRequest request){
		System.out.println(SessionContext.getUserName(request));
        //提示具体用户名称登录成功
        return "/account";
    }

	//获取当前用户信息
	private UserDetails getUserDetails(){
		String username = null;
		//当前认证通过的用户身份
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//用户身份
		Object principal = authentication.getPrincipal();
		if(principal == null){
			username = "匿名";
		}
		if(principal instanceof org.springframework.security.core.userdetails.UserDetails){
			UserDetails userDetails = (UserDetails) principal;
			return userDetails;
		}
		return null;
	}
}
