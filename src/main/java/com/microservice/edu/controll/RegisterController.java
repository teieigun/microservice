package com.microservice.edu.controll;

import java.text.ParseException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value="/emailRegist",method={RequestMethod.GET,RequestMethod.POST})
    public String load(Model model , String email,String validateCode,String action) throws ParseException{
        System.out.println("-----r----"+action);
        ModelAndView mav=new ModelAndView();
        if("register".equals(action)) {

            //发邮箱激活
            service.processregister(email);
            model.addAttribute("msg","注册成功");
            return "/login";
        }
        else if("activate".equals(action)) {

            try {
                //调用激活方法
                service.processActivate(email , validateCode);
                mav.setViewName("register/activate_success");
            } catch (ServiceException e) {
                model.addAttribute("message", e.getMessage());
                mav.setViewName("register/activate_failure");
            }

        }
        return "/login";
    }

    @RequestMapping(value="/emailRegist2",method={RequestMethod.GET})
    public String load2(Model model , String email,String validateCode,String action) throws ParseException{
        System.out.println("-----r----"+action);
        ModelAndView mav=new ModelAndView();
        if("register".equals(action)) {

            //发邮箱激活
            service.processregister(email);
            model.addAttribute("msg","注册成功");
            return "/login";
        }
        else if("activate".equals(action)) {

            try {
                //调用激活方法
                service.processActivate(email , validateCode);
                mav.setViewName("register/activate_success");
            } catch (ServiceException e) {
                model.addAttribute("message", e.getMessage());
                mav.setViewName("register/activate_failure");
            }

        }
        return "/login";
    }
}