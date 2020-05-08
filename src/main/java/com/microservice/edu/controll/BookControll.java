package com.microservice.edu.controll;

import com.microservice.edu.form.comments.CommentsRoot;
import com.microservice.edu.pojo.BookPojo;
import com.microservice.edu.service.CommentService;
import com.microservice.edu.service.TestService;
import com.microservice.edu.service.TopPageService;
import com.microservice.edu.service.WatchVideoService;
import com.microservice.edu.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import web.SessionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 测试前后端分离的测试类 前端 Vue.js  后端 Spring boot
 * */

@Controller
public class BookControll {

	@Autowired
	CommentService commentService;

	@Autowired
	WatchVideoService watchVideoService;

	@Autowired
	TestService testService;

	@RequestMapping(value = "/book", method = RequestMethod.GET)
	@ResponseBody
	public List<BookPojo>  login(Model model) throws Exception {

		List<BookPojo> resultList = testService.getBookInfo();

		return resultList;
	}

}
