package com.microservice.edu.controll;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.microservice.edu.constants.MicroServiceConstants;
import com.microservice.edu.dao.CourseMasterDao;
import com.microservice.edu.form.LoginForm;
import com.microservice.edu.pojo.CourseMasterPojo;
import com.microservice.edu.pojo.UserBaseInfo;
import com.microservice.edu.service.ProfileService;
import com.microservice.edu.service.TopPageService;
import com.microservice.edu.util.SecurityUtil;
import com.microservice.edu.util.SessionContext;


@Controller
public class VideoControll {


	@Autowired
	TopPageService topPageService;

	@Autowired
	ProfileService profileService;

	@Autowired
	CourseMasterDao courseMasterDao;


	private String email;

	/**
	 * 输入【域名】，直接显示video画面
	 * */
	@RequestMapping(value = "/", method={RequestMethod.GET,RequestMethod.POST})
	@Transactional(readOnly = true)
	public String domain(@ModelAttribute @Validated LoginForm form, BindingResult result, ModelAndView mv) throws Exception {

		return "redirect:/index";
	}

	/**
	 * 输入【域名/index】，直接显示video画面
	 * */
	@RequestMapping(value = "/index", method={RequestMethod.GET,RequestMethod.POST})
	@Transactional(readOnly = true)
	public String index(Model model, String bigCtgCode, String smallCtgCode, String videoNm,HttpServletRequest request) throws Exception {

		String sessionId = request.getSession().getId();

		UserBaseInfo userBaseInfo =null;

		//用户账户取得
		UserDetails userDetails = SecurityUtil.getUserDetails();

		//用户未登录的情况,用匿名用户邮件代替
		if(userDetails==null || userDetails.getUsername().isEmpty()){
			email = MicroServiceConstants.NO_NAME_USER;
			//登录状态设定 未登录
			model.addAttribute(MicroServiceConstants.LOGIN_STATUS,MicroServiceConstants.LOGIN_STATUS_OFF);

		}else{
			email = userDetails.getUsername();
			userBaseInfo = profileService.getUserInfoInfo(email);
			//登录状态设定 已登录
			model.addAttribute(MicroServiceConstants.LOGIN_STATUS,MicroServiceConstants.LOGIN_STATUS_ON);
		}

		if(videoNm==null || videoNm.isEmpty()){
			topPageService.getIndexInfo(model,bigCtgCode,smallCtgCode,email);
		}else{
			topPageService.getIndexInfoUseLike(model,videoNm,email);
		}

		if(	userBaseInfo!=null) {
			SessionContext.setAttribute(request, "profileImage", userBaseInfo.profile_image);
			model.addAttribute("profileImage",userBaseInfo.profile_image);
		}else {
			SessionContext.setAttribute(request, "profileImage", "profile.png");
			model.addAttribute("profileImage","profile.png");
		}

		model.addAttribute("videoType","全部视频");

		return "/index";
	}


	@GetMapping(value = "/showCourse",produces = {"text/plain;charset=UTF-8"})
	@Transactional(readOnly = true)
	public String showCourse(Model model, String lessonId, HttpServletRequest request) throws Exception {

		String sessionId = request.getSession().getId();

		String email = SecurityUtil.getUserDetails().getUsername();

		Integer cnt = courseMasterDao.isBuyCourse(email,lessonId);
		if(cnt ==null || cnt<=0){
			return "redirect:/watch/callme";
		}

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

		return "/index";
	}


	@RequestMapping(value = "/video/account", method = RequestMethod.GET)
	@Transactional(readOnly = true)
    public String account(HttpServletRequest request){
		System.out.println(SessionContext.getUserName(request));
        //提示具体用户名称登录成功
        return "/account";
    }

}
