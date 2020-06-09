package com.microservice.edu.pojo;

import java.beans.Transient;
import java.util.Calendar;
import java.util.Date;

import lombok.Data;

@Data
public class UserPojo {
    private String password;
    private String email;//注册账号
    private int status=0;//激活状态
    private String validateCode;//激活码
    private Date  registerTime;//注册时间


    /////////////////////////
    @Transient
    public Date getLastActivateTime() {
        Calendar cl = Calendar.getInstance();
        cl.setTime(registerTime);
        cl.add(Calendar.DATE , 2);

        return cl.getTime();
    }

}