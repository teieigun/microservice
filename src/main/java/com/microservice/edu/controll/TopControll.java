package com.microservice.edu.controll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.LessonTblPojo;
import com.microservice.edu.pojo.SmallCategoryTblPojo;
import com.microservice.edu.service.TopPageService;


@Controller
public class TopControll {

	@Autowired
	TopPageService topPageService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@Transactional(readOnly = true)
	public String login(Model model) throws Exception {

//		getIndexInfo(model);

		return "/login";
	}

	@GetMapping(value = "/video",produces = {"text/plain;charset=UTF-8"})
	@PreAuthorize("hasAuthority('5')")//拥有p1权限才可以访问
	@Transactional(readOnly = true)
	public String index(Model model) throws Exception {

		getIndexInfo(model);

		return "/index";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String index2(Model model) throws Exception {

		//getIndexInfo(model);

		return "/login";
	}


	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String signin(Model model) throws Exception {

		//getIndexInfo(model);

		return "/login";
	}

    @RequestMapping(value = "/tech/video", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String searchVideoBySmallCtg(Model model, String bigCtgCode, String smallCtgCode) throws Exception {

        getIndexInfo(model);
        List<LessonTblPojo> listLessonTblPojo = topPageService.searchAllEnableVideoByCtg(bigCtgCode,smallCtgCode);

        model.addAttribute("listLessonTblPojo", listLessonTblPojo);

        return "/index";
    }


    private void getIndexInfo(Model model) throws Exception {
		List<List<SmallCategoryTblPojo>> listListSmallCategoryTblPojo = new ArrayList<List<SmallCategoryTblPojo>>();

		//轮播菜单
			List<BigCategoryTblPojo> bigCtgList = topPageService.getPageInfoLunBoBigCtg();
		if (bigCtgList != null && bigCtgList.size() > 0) {
			for (BigCategoryTblPojo bigCategoryTblPojo : bigCtgList) {
				listListSmallCategoryTblPojo.add(topPageService
						.getPageInfoLunBoSmallCtg(bigCategoryTblPojo.getCtgCode()));
			}
		}        //获取首页视频地址，图片
        List<LessonTblPojo> listLessonTblPojo = topPageService.searchAllEnableVideo();


        model.addAttribute("listLessonTblPojo", listLessonTblPojo);
		model.addAttribute("listListSmallCategoryTblPojo", listListSmallCategoryTblPojo);
		model.addAttribute("bigCtgList", bigCtgList);
	}


}
