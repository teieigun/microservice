package com.microservice.edu.controll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.SmallCategoryTblPojo;
import com.microservice.edu.service.TopPageService;

@Controller
public class TopControll {

	@Autowired
	TopPageService topPageService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String index(Model model) throws Exception {

		getIndexInfo(model);

		return "/index";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String index2(Model model) throws Exception {

		getIndexInfo(model);

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
		}
        //获取首页视频地址，图片


		model.addAttribute("listListSmallCategoryTblPojo", listListSmallCategoryTblPojo);
		model.addAttribute("bigCtgList", bigCtgList);
	}

	@RequestMapping(value = "/video", method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public String searchVideoBySmallCtg(Model model, String bigCtg, String smallCtg) throws Exception {

		List<List<SmallCategoryTblPojo>> listListSmallCategoryTblPojo = new ArrayList<List<SmallCategoryTblPojo>>();

		List<BigCategoryTblPojo> bigCtgList = topPageService.getPageInfoLunBoBigCtg();
		if (bigCtgList != null && bigCtgList.size() > 0) {
			for (BigCategoryTblPojo bigCategoryTblPojo : bigCtgList) {
				listListSmallCategoryTblPojo.add(topPageService
						.getPageInfoLunBoSmallCtg(bigCategoryTblPojo.getCtgCode()));
			}
		}

		model.addAttribute("listListSmallCategoryTblPojo", listListSmallCategoryTblPojo);
		model.addAttribute("bigCtgList", bigCtgList);

		return "/index";
	}


}
