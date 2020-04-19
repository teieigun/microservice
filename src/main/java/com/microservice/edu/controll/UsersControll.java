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
public class UsersControll {


    @Autowired
    TopPageService topPageService;

    @Autowired
    WatchVideoService watchVideoService;


    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String addUser(Model model,String jiaoChengId) throws Exception {


        return "/signup";
    }


}
