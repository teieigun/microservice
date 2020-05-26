package com.microservice.edu.controll;

import javax.servlet.http.HttpServletRequest;

import com.microservice.edu.pojo.CourseMasterPojo;
import com.microservice.edu.util.SecurityUtil;
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

import java.security.Security;
import java.util.List;


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

	private String email;

	@RequestMapping(value = "/video", method={RequestMethod.GET,RequestMethod.POST})
	@Transactional(readOnly = true)
	public String index(Model model, String bigCtgCode, String smallCtgCode, String videoNm,HttpServletRequest request) throws Exception {

		String sessionId = request.getSession().getId();

		//用户账户取得
		UserDetails userDetails = SecurityUtil.getUserDetails();
		SessionContext.setAttribute(request, sessionId,SecurityUtil.getUserDetails());

		email = userDetails.getUsername();
		if(videoNm==null || videoNm.isEmpty()){
			topPageService.getIndexInfo(model,bigCtgCode,smallCtgCode,email);
		}else{
			topPageService.getIndexInfoUseLike(model,videoNm,email);
		}

		UserBaseInfo userBaseInfo = profileService.getUserInfoInfo(SessionContext.getUserName(request));

		SessionContext.setAttribute(request, "profileImage", userBaseInfo.profile_image);

		model.addAttribute("profileImage",userBaseInfo.profile_image);
		model.addAttribute("videoType","全部视频");

		return "/video";
	}


	@GetMapping(value = "/showCourse",produces = {"text/plain;charset=UTF-8"})
	@Transactional(readOnly = true)
	public String showCourse(Model model, String lessonId, HttpServletRequest request) throws Exception {

		String sessionId = request.getSession().getId();

		//用户账户取得
		UserDetails userDetails = SecurityUtil.getUserDetails();
		SessionContext.setAttribute(request, sessionId,SecurityUtil.getUserDetails());

		//套餐名取得

		List<CourseMasterPojo> listCourseMstPojo = topPageService.getCourseInfo(lessonId);

		email = userDetails.getUsername();

		topPageService.getBuyCourseVideo(model,null,null,email,lessonId);

		UserBaseInfo userBaseInfo = profileService.getUserInfoInfo(SessionContext.getUserName(request));

		SessionContext.setAttribute(request, "profileImage", userBaseInfo.profile_image);

		model.addAttribute("profileImage",userBaseInfo.profile_image);

		model.addAttribute("videoType",listCourseMstPojo.get(0).courseName);

		return "/video";
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

}
