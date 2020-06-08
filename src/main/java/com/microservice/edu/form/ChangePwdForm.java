package com.microservice.edu.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ChangePwdForm {

    @NotBlank(message = "用户名:用户名不能为空")
    @Email(message = "用户名:请输入邮箱地址")
    public String email;
    @NotBlank(message = "旧密码:请输入密码")
    @Pattern(message = "旧密码:只能输入英数字和记号", regexp = "^[-_@*&!A-Za-z0-9]+$")
    @Size(message = "旧密码:密码要8文字以上", min = 8)
    public String old_pwd;

    @NotBlank(message = "新密码:密码不能为空")
    @Pattern(message = "新密码:只能输入英数字和记号", regexp = "^[-_@*&!A-Za-z0-9]+$")
    @Size(message = "新密码:密码要8文字以上", min = 8)
    public String new_pwd;

    @NotBlank(message = "确认密码:密码不能为空")
    @Pattern(message = "确认密码:只能输入英数字和记号", regexp = "^[-_@*&!A-Za-z0-9]+$")
    @Size(message = "确认密码:密码要8文字以上", min = 8)
    public String confirm_pwd;

}
