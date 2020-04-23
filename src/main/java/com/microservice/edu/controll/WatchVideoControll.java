package com.microservice.edu.controll;

import com.microservice.edu.pojo.LessonChapterPojo;
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
    public String goToVideoPage(Model model,String LessonId) throws Exception {

        System.out.println("LessonId:"+LessonId);

        List<LessonChapterPojo> listLessonChapterPojo =  watchVideoService.getChapterList(LessonId);
        model.addAttribute("listLessonChapterPojo", listLessonChapterPojo);
        if(listLessonChapterPojo !=null && listLessonChapterPojo.size()>0){
            model.addAttribute("LessonChapterPojoOne", listLessonChapterPojo.get(0));
        }
        return "/watchVideo";
    }

    @RequestMapping(value = "/changeChapter", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String changeChapter(Model model,String LessonId,String chapterNo) throws Exception {

        System.out.println("LessonId:"+LessonId);
        List<LessonChapterPojo> listLessonChapterPojo1 =  watchVideoService.changeChapter(LessonId,chapterNo);
        if(listLessonChapterPojo1!=null && listLessonChapterPojo1.size()>0){
            model.addAttribute("LessonChapterPojoOne", listLessonChapterPojo1.get(0));
        }


        List<LessonChapterPojo> listLessonChapterPojo2 =  watchVideoService.getChapterList(LessonId);
        model.addAttribute("listLessonChapterPojo", listLessonChapterPojo2);
        return "/watchVideo";
    }

}
