package com.microservice.edu.controll;

import java.text.ParseException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.microservice.edu.form.PassForm;
import com.microservice.edu.pojo.UserPojo;
import com.microservice.edu.service.RegisterValidateService;


@Controller
public class RegisterController {

    @Resource
    private RegisterValidateService service;


    @RequestMapping(value = "/regist", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String regist(Model model) throws Exception {

        //getIndexInfo(model);

        return "/regist";
    }

    @RequestMapping(value="/emailRegist",method={RequestMethod.GET,RequestMethod.POST})
    public String load(Model model , String email,String validateCode,String action) throws ParseException{
        System.out.println("-----r----"+action);
        ModelAndView mav=new ModelAndView();
        //发邮箱激活
        service.processregister(email);
        model.addAttribute("msg","注册成功");
        return "/login";

    }

    @RequestMapping(value = "/passwd", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String passwd(Model model, String validateCode) throws Exception {
        UserPojo userPojo =service.getUserInfo(validateCode);
        model.addAttribute("email", userPojo.getEmail());
        model.addAttribute("validateCode", userPojo.getValidateCode());
        return "/passwd";
    }

    @RequestMapping(value = "/setPass", method = RequestMethod.POST)
    @Transactional
    public String setPass(Model model, PassForm form, BindingResult result) throws Exception {

        service.setNewPasswd(form.passwd,form.validateCode);

        return "/index";
    }

}