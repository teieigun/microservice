package com.microservice.edu.controll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.edu.constants.MicroServiceConstants;
import com.microservice.edu.pojo.LessonChapterPojo;
import com.microservice.edu.pojo.LessonChapterSectionPojo;
import com.microservice.edu.pojo.LessonTblPojo;
import com.microservice.edu.pojo.UserBaseInfo;
import com.microservice.edu.service.ProfileService;
import com.microservice.edu.service.TopPageService;
import com.microservice.edu.service.WatchVideoService;
import com.microservice.edu.util.SecurityUtil;
import com.microservice.edu.util.SessionContext;


@Controller
public class WatchVideoControll {

	@Autowired
	TopPageService topPageService;

	@Autowired
	WatchVideoService watchVideoService;

	@Autowired
	ProfileService profileService;

	@RequestMapping(value = "/video/watch", method = RequestMethod.GET)
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
		List<LessonChapterPojo> listLessonChapterPojo = watchVideoService.getChapterList(email, lessonId);
		if (listLessonChapterPojo == null || listLessonChapterPojo.size() == 0) {
			return "redirect:/video/callme";
		}

		//已购买的情况下，进入播放页面
		//所有章信息存放入model
		model.addAttribute("listLessonChapterPojo", listLessonChapterPojo);
		//所有节信息存放入model
		Map<String, List<LessonChapterSectionPojo>> chapterSectionMap = new HashMap<String, List<LessonChapterSectionPojo>>();
		List<LessonChapterSectionPojo> listLessonChapterSectionPojo = watchVideoService.getSectionList(email, lessonId);
		listLessonChapterPojo.forEach(chapter -> chapterSectionMap.put(chapter.chapterNo, listLessonChapterSectionPojo
				.stream().filter(section -> section.chapterNo.equals(chapter.chapterNo)).collect(Collectors.toList())));
		//所有节信息存放入model
		model.addAttribute("chapterSectionMap", chapterSectionMap);
		if (listLessonChapterPojo != null && listLessonChapterPojo.size() > 0) {
			if ("0".equals(chapterNo)) {
				chapterNo = "1";
			}
			if ("0".equals(sectionNo)) {
				sectionNo = "1";
			}
			List<LessonChapterSectionPojo> LessonChapterPojoOne = watchVideoService.getOneSection(lessonId, chapterNo,
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

		return "/watchVideo";
	}

	@RequestMapping(value = "/video/changeChapter", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String changeChapter(Model model, String lessonId, String chapterNo, String sectionNo,
			HttpServletRequest request)
			throws Exception {

		System.out.println("LessonId:" + lessonId);
		List<LessonChapterSectionPojo> listLessonChapterPojo1 = watchVideoService.changeChapter(lessonId, chapterNo,
				sectionNo);
		if (listLessonChapterPojo1 != null && listLessonChapterPojo1.size() > 0) {
			model.addAttribute("LessonChapterPojoOne", listLessonChapterPojo1.get(0));
		}
		String email = SecurityUtil.getUserDetails().getUsername();
		List<LessonChapterPojo> listLessonChapterPojo2 = watchVideoService.getChapterList(email, lessonId);
		model.addAttribute("listLessonChapterPojo", listLessonChapterPojo2);
		//所有节信息存放入model
		Map<String, List<LessonChapterSectionPojo>> chapterSectionMap = new HashMap<String, List<LessonChapterSectionPojo>>();
		List<LessonChapterSectionPojo> listLessonChapterSectionPojo = watchVideoService.getSectionList(email, lessonId);
		listLessonChapterPojo2.forEach(chapter -> chapterSectionMap.put(chapter.chapterNo, listLessonChapterSectionPojo
				.stream().filter(section -> section.chapterNo.equals(chapter.chapterNo)).collect(Collectors.toList())));
		//所有节信息存放入model
		model.addAttribute("chapterSectionMap", chapterSectionMap);

		if (listLessonChapterPojo2 != null && listLessonChapterPojo2.size() > 0) {
			model.addAttribute("defautLessonId", listLessonChapterPojo2.get(0).lessonId);
			model.addAttribute("defautChapterNo", listLessonChapterPojo2.get(0).chapterNo);
		}

		List<LessonTblPojo> listRecommend = topPageService.searchAllEnableVideoIsRcommend(email);

		model.addAttribute("listRecommend", listRecommend);
		model.addAttribute("profileImage", SessionContext.getAttribute(request, "profileImage"));
		model.addAttribute("checkFlg", 1);
		model.addAttribute("questions",
				watchVideoService.getCommentsRootCount(Integer.valueOf(lessonId), Integer.valueOf(chapterNo)));
		return "/watchVideo";
	}

	@RequestMapping(value = "/video/callme", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String callme(Model model, String lessonId, String chapterNo, HttpServletRequest request) throws Exception {
		UserBaseInfo userBaseInfo = profileService.getUserInfoInfo(SessionContext.getUserName(request));
		model.addAttribute("profileImage", userBaseInfo.profile_image);
		return "/callme";
	}

}
