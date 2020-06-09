package com.microservice.edu.controll;

import com.microservice.edu.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginControll {

    /**
     * 用户登录画面
     * */
    @RequestMapping(value = "/dologin", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional(readOnly = true)
    public ModelAndView dologin(@ModelAttribute @Validated LoginForm form, BindingResult result, ModelAndView mv) throws Exception {

        if (result.hasErrors()) {
            mv.addObject("errorMessage", "用户名或者密码有误");
        }

        mv.setViewName("/login");
        return mv;
    }

    /**
     * 用户登录画面
     * */
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional(readOnly = true)
    public String login(@ModelAttribute @Validated LoginForm form, BindingResult result, ModelAndView mv) throws Exception {

        return "/login";
    }
}
