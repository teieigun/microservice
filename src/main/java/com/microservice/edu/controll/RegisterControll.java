package com.microservice.edu.controll;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.microservice.edu.form.PassForm;
import com.microservice.edu.pojo.UserPojo;
import com.microservice.edu.service.RegisterValidateService;


@Controller
public class RegisterControll {

    @Resource
    private RegisterValidateService service;


    @RequestMapping(value = "/regist", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String regist(Model model) throws Exception {

        return "/regist";
    }

    /**
     * 用户注册
     * */
    @RequestMapping(value="/doRegist",method={RequestMethod.GET,RequestMethod.POST})
    public String load(Model model , String email,String validateCode,String action) throws ParseException{
        System.out.println("-----r----"+action);
        ModelAndView mav=new ModelAndView();
        //发邮箱激活
        service.processregister(email);
        model.addAttribute("msg","注册链接已经发送到您的邮箱，请确认！");
        return "/regist";

    }

    /**
     * 密码输入画面
     * */
    @RequestMapping(value = "/passwd", method = RequestMethod.GET)
    @Transactional
    public String passwd(Model model, String validateCode) throws Exception {
        UserPojo userPojo =service.processActivate(validateCode);
        model.addAttribute("email", userPojo.getEmail());
        model.addAttribute("validateCode", userPojo.getValidateCode());
        return "/passwd";
    }


    /**
     * 密码设定
     * */
    @RequestMapping(value = "/doPasswd", method = RequestMethod.POST)
    @Transactional
    public String setPass(Model model, @ModelAttribute("form") @Valid PassForm form, BindingResult result) throws Exception {

        String reString = "/passwd";

        if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for(ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return reString;
        }
        reString = "redirect:/login";
        service.setNewPasswd(form.passwd,form.validateCode);

        return reString;
    }

}