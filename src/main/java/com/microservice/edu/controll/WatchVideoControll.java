package com.microservice.edu.controll;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.edu.pojo.LessonChapterPojo;
import com.microservice.edu.service.TopPageService;
import com.microservice.edu.service.WatchVideoService;

import web.SessionContext;

@Controller
public class WatchVideoControll {

	@Autowired
	TopPageService topPageService;

	@Autowired
	WatchVideoService watchVideoService;

	@RequestMapping(value = "/video/watch", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String goToVideoPage(Model model, String lessonId, String tagFlg, String questionId,
			HttpServletRequest request) throws Exception {

		System.out.println("LessonId:" + lessonId);

		List<LessonChapterPojo> listLessonChapterPojo = watchVideoService.getChapterList(lessonId);
		model.addAttribute("listLessonChapterPojo", listLessonChapterPojo);
		if (listLessonChapterPojo != null && listLessonChapterPojo.size() > 0) {
			model.addAttribute("LessonChapterPojoOne", listLessonChapterPojo.get(0));
		}
		if (tagFlg.isEmpty()) {
			model.addAttribute("tagFlg", 1);
		} else {
			model.addAttribute("tagFlg", tagFlg);
		}

		model.addAttribute("questionId", questionId);
		model.addAttribute("checkFlg", 1);
		model.addAttribute("profileImage", SessionContext.getAttribute(request, "profileImage"));
		model.addAttribute("questions", 20);

		return "/watchVideo";
	}

	@RequestMapping(value = "/video/changeChapter", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String changeChapter(Model model, String lessonId, String chapterNo,HttpServletRequest request) throws Exception {

		System.out.println("LessonId:" + lessonId);
		List<LessonChapterPojo> listLessonChapterPojo1 = watchVideoService.changeChapter(lessonId, chapterNo);
		if (listLessonChapterPojo1 != null && listLessonChapterPojo1.size() > 0) {
			model.addAttribute("LessonChapterPojoOne", listLessonChapterPojo1.get(0));
		}

		List<LessonChapterPojo> listLessonChapterPojo2 = watchVideoService.getChapterList(lessonId);
		model.addAttribute("listLessonChapterPojo", listLessonChapterPojo2);
		if (listLessonChapterPojo2 != null && listLessonChapterPojo2.size() > 0) {
			model.addAttribute("defautLessonId", listLessonChapterPojo2.get(0).lessonId);
			model.addAttribute("defautChapterNo", listLessonChapterPojo2.get(0).chapterNo);
		}
		model.addAttribute("profileImage", SessionContext.getAttribute(request, "profileImage"));
		model.addAttribute("checkFlg", 1);

		return "/watchVideo";
	}

}
