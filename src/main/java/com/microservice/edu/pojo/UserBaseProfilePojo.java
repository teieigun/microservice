package com.microservice.edu.pojo;

import lombok.Data;

@Data
public class UserBaseProfilePojo {

    public String passwd;
    public String email;
    public String status;
    public String validateCode;
    public String registerTime;
    public String enable;
}
