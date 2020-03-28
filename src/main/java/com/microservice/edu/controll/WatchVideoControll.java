package com.microservice.edu.controll;

import com.microservice.edu.service.TopPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WatchVideoControll {


    @Autowired
    TopPageService topPageService;

    @RequestMapping(value = "/goToWatch", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String goToVideoPage(Model model) throws Exception {

        return "/watchVideo";
    }

}
