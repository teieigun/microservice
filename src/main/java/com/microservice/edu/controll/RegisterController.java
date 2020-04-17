package com.microservice.edu.controll;

import java.text.ParseException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.microservice.edu.service.RegisterValidateService;
import com.microservice.edu.util.ServiceException;

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

    @RequestMapping(value="/passwd",method={RequestMethod.GET})
    public String passwd(Model model ,String validateCode) throws ParseException{

//        ModelAndView mav=new ModelAndView();
//            try {
//                //调用激活方法
//                service.processActivate(validateCode);
//                mav.setViewName("register/activate_success");
//            } catch (ServiceException e) {
//                model.addAttribute("message", e.getMessage());
//                mav.setViewName("register/activate_failure");
//            }

        return "/passwd";
    }

}