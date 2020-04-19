package com.microservice.edu.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PassForm {

	public String email;

	/** 密码 */
	@NotBlank (message = "用户密码不能为空")
	public String passwd;

	/** 确认密码 */
	@NotBlank (message = "密码不符")
	public String confirmPasswd;

	/** 验证码 */
	@NotBlank (message = "请重新注册")
	public String validateCode;

	public String getEmail() {
		return email;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getConfirmPasswd() {
		return confirmPasswd;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setConfirmPasswd(String confirmPasswd) {
		this.confirmPasswd = confirmPasswd;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
}
