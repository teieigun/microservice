package com.microservice.edu.controll;

import com.microservice.edu.pojo.JiaoChengChapterPojo;
import com.microservice.edu.service.TopPageService;
import com.microservice.edu.service.WatchVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class WatchVideoControll {


    @Autowired
    TopPageService topPageService;

    @Autowired
    WatchVideoService watchVideoService;

    @RequestMapping(value = "/edu/watch", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String goToVideoPage(Model model,String jiaoChengId) throws Exception {

        System.out.println("jiaoChengId:"+jiaoChengId);

        List<JiaoChengChapterPojo> listJiaoChengChapterPojo =  watchVideoService.getChapterList(jiaoChengId);
        model.addAttribute("listJiaoChengChapterPojo", listJiaoChengChapterPojo);
        if(listJiaoChengChapterPojo !=null && listJiaoChengChapterPojo.size()>0){
            model.addAttribute("jiaoChengChapterPojoOne", listJiaoChengChapterPojo.get(0));
        }
        return "/watchVideo";
    }

    @RequestMapping(value = "/changeChapter", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String changeChapter(Model model,String jiaoChengId,String chapterNo) throws Exception {

        System.out.println("jiaoChengId:"+jiaoChengId);
        List<JiaoChengChapterPojo> listJiaoChengChapterPojo1 =  watchVideoService.changeChapter(jiaoChengId,chapterNo);
        if(listJiaoChengChapterPojo1!=null && listJiaoChengChapterPojo1.size()>0){
            model.addAttribute("jiaoChengChapterPojoOne", listJiaoChengChapterPojo1.get(0));
        }


        List<JiaoChengChapterPojo> listJiaoChengChapterPojo2 =  watchVideoService.getChapterList(jiaoChengId);
        model.addAttribute("listJiaoChengChapterPojo", listJiaoChengChapterPojo2);
        return "/watchVideo";
    }

}
