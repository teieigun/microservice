package com.microservice.edu.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PassForm {

	/** 密码 */
	@NotBlank (message = "用户密码不能为空")
	public String passwd;

	/** 确认密码 */
	@NotBlank (message = "密码不符")
	public String confirmPasswd;

	/** 验证码 */
	@NotBlank (message = "请重新注册")
	public String validateCode;

}
