package com.microservice.edu.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.edu.constants.MicroServiceConstants;
import com.microservice.edu.dao.UserDao;
import com.microservice.edu.pojo.UserPojo;
//import com.app.tools.MD5Tool;
import com.microservice.edu.util.MD5Util;
import com.microservice.edu.util.SendEmail;
import com.microservice.edu.util.ServiceException;

/**
 *
 * @author Qixuan.Chen
 */
@Service
public class RegisterValidateService {

    @Autowired
    private UserDao userDao;

    /**
     * 处理注册
     */

    public void processregister(String email){
        UserPojo user=new UserPojo();

        user.setEmail(email);
        user.setRegisterTime(new Date());
        user.setStatus(0);
        user.setValidateCode(MD5Util.encode2hex(email));

        //检索临时用户表 查看寄存
        try {
            // 如果存在 更新状态和时间
            if(userDao.findbyPk(email).getEmail()!=null){
                userDao.resetUser(email);
            }else{
                userDao.saveProfile(user);
                userDao.saveBaseInfo(email);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ///邮件的内容
        StringBuffer sb=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        sb.append("<a href=\"http://localhost:8088/passwd?");
        sb.append("validateCode=");
        sb.append(user.getValidateCode());
        sb.append("\">http://localhost:8088/passwd?");
        sb.append("validateCode=");
        sb.append(user.getValidateCode());
        sb.append("</a>");

        //发送邮件
        SendEmail.send(email, sb.toString());
        System.out.println("发送邮件");

    }

    /**
     * 处理激活
     * @throws ParseException
     */
    ///传递激活码和email过来
    public UserPojo processActivate(String validateCode)throws ServiceException, ParseException{

    	UserPojo user=userDao.findByVcd(validateCode,MicroServiceConstants.USER_STATUS_NOPASS);
        //验证用户是否存在
        if(user!=null) {
            //验证用户激活状态
            if(user.getStatus()==0) {
                ///没激活
                Date currentTime = new Date();//获取当前时间
                //验证链接是否过期
                currentTime.before(user.getRegisterTime());
                if(currentTime.before(user.getLastActivateTime())) {
                        //激活成功， //并更新用户的激活状态
                        userDao.updateUserStatus(user.getEmail(),MicroServiceConstants.USER_STATUS_PASSING);
                        System.out.println("==sq==="+user.getStatus());
                } else { throw new ServiceException("激活码已过期！");
                }
            } else {
                throw new ServiceException("邮箱已激活，请登录！");
            }
        } else {
            throw new ServiceException("激活码不正确");
        }

        return user;
    }

    ///传递激活码和email过来
    public void setNewPasswd(String passwd,String validateCode)throws ServiceException, ParseException{
        userDao.setNewPass(passwd,validateCode);
    }

}