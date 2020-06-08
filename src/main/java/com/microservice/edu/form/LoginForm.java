package com.microservice.edu.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class LoginForm {
	
	/** 用户名 */
	@NotBlank(message = "用户名不能为空")
	@Email(message = "请输入邮箱地址")
	public String userId;
	
	/** 密码 */
	@NotBlank(message = "请输入密码")
	@Pattern(message = "英数字和记号可以指定", regexp = "^[-_@*&!A-Za-z0-9]+$")
	@Size(message = "密码要8文字以上", min=8)
	public String passwd;

	/** 确认密码 */
	@NotBlank(message = "请输入密码")
	@Pattern(message = "英数字和记号可以指定", regexp = "^[-_@*&!A-Za-z0-9]+$")
	@Size(message = "密码要8文字以上", min=8)
	public String confirm;

	/** 验证码 */
	@NotBlank (message = "请重新注册")
	public String validateCode;


}
