package com.microservice.edu.form;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class PassForm {

	@NotBlank(message = "用户名不能为空")
	@Email(message = "请输入邮箱地址")
	public String email;

	/** 密码 */
	@NotBlank(message = "密码:请输入密码")
	@Pattern(message = "密码:只能输入英数字和记号", regexp = "^[-_@*&!A-Za-z0-9]+$")
	@Size(message = "密码:密码要8文字以上", min=8)
	public String passwd;

	/** 确认密码 */
	@NotBlank(message = "确认密码:请输入确认密码")
	@Pattern(message = "确认密码:只能输入英数字和记号", regexp = "^[-_@*&!A-Za-z0-9]+$")
	@Size(message = "确认密码:密码要8文字以上", min=8)
	public String confirmPasswd;

	/** 验证码 */
	public String validateCode;

	/**
	 * バリデーション：階数の文字数
	 */
	@AssertTrue(message = "确认密码:密码不一致")
	public boolean isValidPasswd() {

		if (!passwd.equals(confirmPasswd)) {
			return false;
		}
		return true;
	}
}
