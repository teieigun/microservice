package com.microservice.edu.controll;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
public class LoginController {

    @RequestMapping(value = "/login-success",produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess(){

        //提示具体用户名称登录成功
        return getUsername()+" 登录成功";
    }

    /**
     * 测试资源1
     * @return
     */
    @GetMapping(value = "/video/normal",produces = {"text/plain;charset=UTF-8"})
    @PreAuthorize("hasAuthority('leve1')")//拥有leve1权限才可以访问
    public String normal(){
        return getUsername()+" 访问资源1";
    }

    /**
     * 测试资源2
     * @return
     */
    @GetMapping(value = "/video/vip",produces = {"text/plain;charset=UTF-8"})
    @PreAuthorize("hasAuthority('leve2')")//拥有leve2权限才可以访问
    public String vip(){
        return getUsername()+" 访问资源2";
    }

    /**
     * 测试资源2
     * @return
     */
    @GetMapping(value = "/video/admin",produces = {"text/plain;charset=UTF-8"})
    @PreAuthorize("hasAuthority('leve3')")//拥有leve3权限才可以访问
    public String admin(){
        return getUsername()+" 访问资源2";
    }

    //获取当前用户信息
    private String getUsername(){
        String username = null;
        //当前认证通过的用户身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //用户身份
        Object principal = authentication.getPrincipal();
        if(principal == null){
            username = "匿名";
        }
        if(principal instanceof org.springframework.security.core.userdetails.UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();

        }else{
            username = principal.toString();
        }
        return username;
    }
}
