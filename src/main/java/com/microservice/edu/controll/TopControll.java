package com.microservice.edu.controll;

import java.util.ArrayList;
import java.util.List;

import com.microservice.edu.dao.JiaoChengTblDao;
import com.microservice.edu.pojo.JiaoChengTblExt1Pojo;
import com.microservice.edu.pojo.JiaoChengTblPojo;
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

		@RequestMapping(value = "/tech/index", method = RequestMethod.GET)
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
        List<JiaoChengTblExt1Pojo> listJiaoChengTblPojo = topPageService.searchAllEnableVideoByCtg(bigCtgCode,smallCtgCode);

        model.addAttribute("listJiaoChengTblPojo", listJiaoChengTblPojo);

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
        List<JiaoChengTblExt1Pojo> listJiaoChengTblPojo = topPageService.searchAllEnableVideo();


        model.addAttribute("listJiaoChengTblPojo", listJiaoChengTblPojo);
		model.addAttribute("listListSmallCategoryTblPojo", listListSmallCategoryTblPojo);
		model.addAttribute("bigCtgList", bigCtgList);
	}


}
