package com.microservice.edu.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

/**
 *
 */
@Data
public class CourseMasterPojo {
	public String courseId;
	public String courseName;
	public String courseImg;
	public String creater;
	public String createTime;
	public String del;

}
