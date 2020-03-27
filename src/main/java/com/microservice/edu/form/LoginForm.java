package com.microservice.edu.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginForm {
	
	/** 用户名 */
	@NotBlank(message = "用户名不能为空")
	@Email(message = "请输入邮箱地址")
	public String userId;
	
	/** 密码 */
	@NotBlank (message = "用户密码不能为空")
	public String passwd;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
}
