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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /////////////////////////
    @Transient
    public Date getLastActivateTime() {
        Calendar cl = Calendar.getInstance();
        cl.setTime(registerTime);
        cl.add(Calendar.DATE , 2);

        return cl.getTime();
    }

}