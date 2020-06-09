package com.microservice.edu.controll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.microservice.edu.dao.LessonChapterDao;
import com.microservice.edu.dao.LessonTblDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.edu.constants.MicroServiceConstants;
import com.microservice.edu.pojo.LessonChapterPojo;
import com.microservice.edu.pojo.LessonChapterPojo;
import com.microservice.edu.pojo.LessonTblPojo;
import com.microservice.edu.pojo.UserBaseInfo;
import com.microservice.edu.service.ProfileService;
import com.microservice.edu.service.TopPageService;
import com.microservice.edu.service.WatchVideoService;
import com.microservice.edu.util.SecurityUtil;
import com.microservice.edu.util.SessionContext;


@Controller
public class WatchControll {

	@Autowired
	TopPageService topPageService;

	@Autowired
	WatchVideoService watchVideoService;

	@Autowired
	ProfileService profileService;

	@Autowired
	LessonChapterDao lessonChapterDao;

	@Autowired
	LessonTblDao lessonTblDao;


	@RequestMapping(value = "/watch", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String goToVideoPage(Model model, String lessonId, String chapterNo, String sectionNo, String tagFlg,
			String questionId,
			HttpServletRequest request) throws Exception {

		//课程ID大于90000的情况属于套餐，套餐内容显示处理
		if (Integer.valueOf(lessonId) >= MicroServiceConstants.COURSE_ID_FROM &&
				Integer.valueOf(lessonId) <= MicroServiceConstants.COURSE_ID_TO) {

			return "redirect:/showCourse?lessonId=" + lessonId;
		}

		String email = SecurityUtil.getUserDetails().getUsername();
		//未购买的情况下，视频再检索
		List<LessonChapterPojo> lessonChapterPojo = watchVideoService.getSectionList(email, lessonId);

		//章情報取得
		List<LessonChapterPojo> lessonChapterPojo1 =watchVideoService.getChapterList(email, lessonId);

		if (lessonChapterPojo == null || lessonChapterPojo.size() == 0) {
			//判断是否有试听
			if(lessonChapterDao.getTestChapter(lessonId)>0){
				lessonChapterPojo =watchVideoService.getSectionListByLessonId(lessonId);
				lessonChapterPojo1 = watchVideoService.getChapterListNotBuy(lessonId);
			}else{
				return "redirect:/watch/callme";
			}
		}
		//所有节信息存放入model
		Map<String, List<LessonChapterPojo>> chapterSectionMap = new HashMap<String, List<LessonChapterPojo>>();

		List<LessonChapterPojo> finalLessonChapterPojo = lessonChapterPojo;
		lessonChapterPojo1.forEach(chapter -> chapterSectionMap.put(chapter.chapterNo, finalLessonChapterPojo
				.stream().filter(section -> section.chapterNo.equals(chapter.chapterNo)).collect(Collectors.toList())));

		model.addAttribute("lessonChapterPojo", lessonChapterPojo1);
		model.addAttribute("chapterSectionMap", chapterSectionMap);


		if (lessonChapterPojo != null && lessonChapterPojo.size() > 0) {
			if ("0".equals(chapterNo)) {
				chapterNo = "1";
			}
			if ("0".equals(sectionNo)) {
				sectionNo = "1";
			}
			List<LessonChapterPojo> LessonChapterPojoOne = watchVideoService.getOneSection(lessonId, chapterNo,
					sectionNo);
			model.addAttribute("LessonChapterPojoOne", LessonChapterPojoOne.get(0));
		}
		if (tagFlg.isEmpty()) {
			model.addAttribute("tagFlg", 1);
		} else {
			model.addAttribute("tagFlg", tagFlg);
		}

		List<LessonTblPojo> listRecommend = topPageService.searchAllEnableVideoIsRcommend(email);

		model.addAttribute("listRecommend", listRecommend);
		model.addAttribute("questionId", questionId);
		model.addAttribute("checkFlg", 1);
		model.addAttribute("profileImage", SessionContext.getAttribute(request, "profileImage"));
		model.addAttribute("questions",
				watchVideoService.getCommentsRootCount(Integer.valueOf(lessonId), Integer.valueOf(chapterNo)));

		return "/watch";
	}

	/**
	 * 点击开始学习按钮开始播放
	 * **/
	@RequestMapping(value = "/watch/changeChapter", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String changeChapter(Model model, String lessonId, String chapterNo, String sectionNo,
			HttpServletRequest request)
			throws Exception {


		String email = SecurityUtil.getUserDetails().getUsername();

		//★★★★★★获取URL★★★★★★★★★★★★★★
		List<LessonChapterPojo> lessonChapterPojo1 = watchVideoService.changeChapter(lessonId, chapterNo,
				sectionNo,email);

		if (lessonChapterPojo1 != null && lessonChapterPojo1.size() > 0) {
			model.addAttribute("LessonChapterPojoOne", lessonChapterPojo1.get(0));
		}

		//所有节信息存放入model
		Map<String, List<LessonChapterPojo>> chapterSectionMap = new HashMap<String, List<LessonChapterPojo>>();

		//★★★★★★获取URL★★★★★★★★★★★★★★
		//判断是否有试听
		List<LessonChapterPojo> lessonChapterPojo = null;
		List<LessonChapterPojo> lessonChapterPojo2 = null;
		Integer buyCnt = lessonTblDao.isBuyLesson(email,lessonId);
		if(buyCnt <=0 && lessonChapterDao.getTestChapter(lessonId)>0){
			lessonChapterPojo = watchVideoService.getSectionListNotBuy(lessonId);
			lessonChapterPojo2 = watchVideoService.getChapterListNotBuy(lessonId);
		}
		if(buyCnt>0){
			lessonChapterPojo = watchVideoService.getSectionList(email, lessonId);
			//不会获取播放URL
			lessonChapterPojo2 = watchVideoService.getChapterList(email, lessonId);
		}

		model.addAttribute("lessonChapterPojo", lessonChapterPojo2);

		List<LessonChapterPojo> finalLessonChapterPojo = lessonChapterPojo;

		lessonChapterPojo2.forEach(chapter -> chapterSectionMap.put(chapter.chapterNo, finalLessonChapterPojo
				.stream().filter(section -> section.chapterNo.equals(chapter.chapterNo)).collect(Collectors.toList())));
		//所有节信息存放入model
		model.addAttribute("chapterSectionMap", chapterSectionMap);

		if (lessonChapterPojo2 != null && lessonChapterPojo2.size() > 0) {
			model.addAttribute("defautLessonId", lessonChapterPojo2.get(0).lessonId);
			model.addAttribute("defautChapterNo", lessonChapterPojo2.get(0).chapterNo);
		}

		List<LessonTblPojo> listRecommend = topPageService.searchAllEnableVideoIsRcommend(email);

		model.addAttribute("listRecommend", listRecommend);
		model.addAttribute("profileImage", SessionContext.getAttribute(request, "profileImage"));
		model.addAttribute("checkFlg", 1);
		model.addAttribute("questions",
				watchVideoService.getCommentsRootCount(Integer.valueOf(lessonId), Integer.valueOf(chapterNo)));
		return "/watch";
	}

	@RequestMapping(value = "/watch/callme", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String callme(Model model, String lessonId, String chapterNo, HttpServletRequest request) throws Exception {
		UserBaseInfo userBaseInfo = profileService.getUserInfoInfo(SessionContext.getUserName(request));
		model.addAttribute("profileImage", userBaseInfo.profile_image);
		return "/callme";
	}

}
