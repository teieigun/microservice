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

	/** 确认密码 */
	@NotBlank (message = "密码不符")
	public String confirm;

	/** 验证码 */
	@NotBlank (message = "请重新注册")
	public String validateCode;


	
	
}
