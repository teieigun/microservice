package com.microservice.edu.util;

import com.microservice.edu.constants.MicroServiceConstants;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtil {

    public static String getUserAccunt(){
        String email="";
        UserDetails userDetails = SecurityUtil.getUserDetails();
        if(userDetails!=null){
            email = userDetails.getUsername();
        }
        return email;
    }

    public static boolean isNoAccountUser(String userAccunt){
        boolean rs = false;
        if(MicroServiceConstants.NO_NAME_USER_EMAIL.equals(userAccunt)){
            rs = true;
        }
        return rs;
    }
}
