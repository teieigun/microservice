package com.microservice.edu.controll;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import web.SessionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
public class AccountController {

	@RequestMapping(value = "/video/account1", method = RequestMethod.GET)
	@Transactional(readOnly = true)
    public String account(HttpServletRequest request){

        //提示具体用户名称登录成功
        return "/index";
    }
}
